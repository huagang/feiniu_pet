package edu.yale.its.tp.cas.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.SecureRandom;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.yale.its.tp.cas.ticket.GrantorCache;
import edu.yale.its.tp.cas.ticket.ProxyGrantingTicket;
import edu.yale.its.tp.cas.ticket.ServiceTicket;
import edu.yale.its.tp.cas.ticket.ServiceTicketCache;
import edu.yale.its.tp.cas.ticket.TicketException;
import edu.yale.its.tp.cas.ticket.Util;
import edu.yale.its.tp.cas.util.SecureURL;

/**
 * Handles ST validation and PGT acquisition.
 */
public class ServiceValidate extends HttpServlet {

	private static final long serialVersionUID = -6001513175747104120L;
	// *********************************************************************
	// Constants

	// failure codes
	private static final String INVALID_REQUEST = "INVALID_REQUEST";
	private static final String INVALID_TICKET = "INVALID_TICKET";
	private static final String INVALID_SERVICE = "INVALID_SERVICE";
	private static final String INTERNAL_ERROR = "INTERNAL_ERROR";

	// PGT IOU length
	private static final int PGT_IOU_LENGTH = 50;

	// *********************************************************************
	// Internal state

	protected ServiceTicketCache stCache;
	protected GrantorCache pgtCache;
	private static int serial = 0;
	private ServletContext app;

	// *********************************************************************
	// Initialization

	public void init(ServletConfig config) throws ServletException {
		// retrieve the context and the cache
		app = config.getServletContext();
		stCache = (ServiceTicketCache) app.getAttribute("stCache");
		pgtCache = (GrantorCache) app.getAttribute("pgtCache");
	}

	// *********************************************************************
	// Request handling

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		PrintWriter out = null;
		try {
			out = response.getWriter();
			if (request.getParameter("service") == null
					|| request.getParameter("ticket") == null) {
				validationFailure(out, INVALID_REQUEST,
						"'service' and 'ticket' parameters are both required");
			} else {
				String ticket = request.getParameter("ticket");
				String service = request.getParameter("service");
				String renew = request.getParameter("renew");
				ServiceTicket st = (ServiceTicket) stCache.getTicket(ticket);
				if (st == null) {
					validationFailure(out, INVALID_TICKET, "ticket '" + ticket
							+ "' not recognized");
				} else if (service.indexOf("lvmama") == -1) {
					validationFailure(out, INVALID_SERVICE, "ticket '" + ticket
							+ "' does not match supplied service");
				} else if ("true".equals(renew) && !st.isFromNewLogin()) {
					validationFailure(out, INVALID_TICKET,
							"ticket not backed by initial CAS login, as requested");
				} else {
					String pgtIOU = null;
					if (request.getParameter("pgtUrl") != null) {
						pgtIOU = sendPgt(st, request.getParameter("pgtUrl"));
					}
					validationSuccess(out, st, pgtIOU);
				}
			}
		} catch (TicketException ex) {
			try {
				if (null != out) {
				validationFailure(out, INTERNAL_ERROR, "Unexpected exception");
				}
				// to do: log?
			} catch (IOException ignoredEx) {
				// ignore
			}
		}
	}

	// *********************************************************************
	// Response-management methods

	/** Sends a validation failure message to the given PrintWriter. */
	protected static void validationFailure(PrintWriter out, String code,
			String errorMessage) throws IOException {
		out.println("<cas:serviceResponse xmlns:cas='http://www.yale.edu/tp/cas'>");
		out.println("  <cas:authenticationFailure code='" + code + "'>");
		out.println("    " + errorMessage);
		out.println("  </cas:authenticationFailure>");
		out.println("</cas:serviceResponse>");
	}

	/** Sends a validation success message to the given PrintWriter. */
	protected void validationSuccess(PrintWriter out, ServiceTicket st,
			String pgtIOU) {
		out.println("<cas:serviceResponse xmlns:cas='http://www.yale.edu/tp/cas'>");
		out.println("  <cas:authenticationSuccess>");
		out.println("    <cas:user>" + st.getUsername() + "</cas:user>");
		if (pgtIOU != null && !pgtIOU.equals("")) {
			out.println("    <cas:proxyGrantingTicket>" + pgtIOU
					+ "</cas:proxyGrantingTicket>");
		}
		out.println("  </cas:authenticationSuccess>");
		out.println("</cas:serviceResponse>");
	}

	/** Creates and sends a new PGT, returning a unique IOU for this PGT. */
	private String sendPgt(ServiceTicket st, String callbackUrl)
			throws TicketException {
		// first, create the PGT and save it to the cache
		ProxyGrantingTicket pgt = new ProxyGrantingTicket(st, callbackUrl);
		String pgtToken = pgtCache.addTicket(pgt);

		// now, create an IOU (with a serial and a random component)
		byte[] b = new byte[PGT_IOU_LENGTH];
		SecureRandom sr = new SecureRandom();
		sr.nextBytes(b);
		String pgtIou = "PGTIOU-" + (serial++) + "-" + Util.toPrintable(b);

		// now, send this PGT/IOU pair to our callback URL
		boolean sent = callbackWithPgt(callbackUrl, pgtToken, pgtIou);

		// return the IOU if appropriate
		if (sent)
			return pgtIou;
		else
			return null;
	}

	/**
	 * Contacts the URL with a PGT and an IOU, but only if the URL's server's
	 * certificate appears appropriate for the URL. Returns <tt>true</tt> on
	 * success, <tt>false</tt> on failure of any kind.
	 */
	private boolean callbackWithPgt(String callbackUrl, String pgtId,
			String iouId) {
		try {
			String target = null;
			if (callbackUrl.indexOf('?') == -1)
				target = callbackUrl + "?pgtIou=" + iouId + "&pgtId=" + pgtId;
			else
				target = callbackUrl + "&pgtIou=" + iouId + "&pgtId=" + pgtId;
			SecureURL.retrieve(target);

			// we succeeded!
			return true;

		} catch (IOException ex) {
			app.log("PGT callback failed: " + ex.toString());
			return false;
		}
	}

	/**
	 * Returns true if the DN is appropriate for the expected server, false
	 * otherwise.
	 */
	public static boolean validateDn(String dn, String expectedServer) {
		// check the CN literally against 'expectedServer'
		// (we can add other, more lenient checks later)
		int cnIndex = dn.indexOf("CN=") + "CN=".length();
		if (cnIndex == -1)
			return false;
		int commaIndex = dn.substring(cnIndex).indexOf(',') + cnIndex;
		String dnCn;
		if (commaIndex <= cnIndex)
			dnCn = dn.substring(cnIndex);
		else
			dnCn = dn.substring(cnIndex, commaIndex);
		return (dnCn.equals(expectedServer));
	}

}
