package edu.yale.its.tp.cas.ticket;

import java.security.SecureRandom;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Represents a cache of login tickets.  These are one-time-use tickets
 * provided on the login page that must be posted back to CAS in order
 * for a login to be successful.  This fixes a bug in IE and Safari where
 * going back through the history causes login credentials to be reposted
 * to CAS.
 *
 * This class is almost identical to ServiceTicketCache, except it is
 * made to only hold LoginTickets.
 */
public class LoginTicketCache extends OTUTicketCache {

  //*********************************************************************
  // Constants

  /** Length of random ticket identifiers. */
  private static final int TICKET_ID_LENGTH = 20;


  //*********************************************************************
  // Private state

  /** The actual cache of tickets (ticketId -> Ticket map) */
  private Map ticketCache;

  /** Monotonically increasing serial number for tickets. */
  private static int serial = 0;

  //*********************************************************************
  // Constructor

  /**
   * Constructs a new ServiceTicketCache that is intended to store
   * cookies of the given specific ticket type.
   */
  public LoginTicketCache(int tolerance) {
    super(tolerance);
    this.ticketCache = Collections.synchronizedMap(new HashMap());
  }


  //*********************************************************************
  // Cache-management logic

  public synchronized String addTicket() throws TicketException {
    return addTicket(new LoginTicket());
  }

  /** Generates and returns a new, unique ticket ID */
  protected String newTicketId() {
    // determine appropriate ticketId prefix
    String prefix = "LT";

    // produce the random identifier
    byte[] b = new byte[TICKET_ID_LENGTH];
    SecureRandom sr = new SecureRandom();
    sr.nextBytes(b);
    String ticketId = prefix + "-" + (serial++) + "-" + Util.toPrintable(b);

    // make sure the identifier isn't already used
    if (ticketCache.get(ticketId) != null)
      return newTicketId();			// tail-recurse
    else
      return ticketId;
  }

  /** Stores the given ticket, associating it with the given identifier. */
  protected void storeTicket(String ticketId, Ticket t)
      throws TicketException {
    // make sure the ticket is valid and new
    if (ticketCache.get(ticketId) != null)
      throw new DuplicateTicketException();

    // if it's okay, then store it
    ticketCache.put(ticketId, t);
  }

  /** Retrieves the ticket with the given identifier. */
  protected Ticket retrieveTicket(String ticketId) {
    Object o = ticketCache.get(ticketId);
    if (o == null)
      return null;
    else
      return (Ticket) o;
  }

  /** Removes the ticket from the cache. */
  public void deleteTicket(String ticketId) {
     ticketCache.remove(ticketId);
  }

  /** Returns the current ticket serial number (for monitoring) */
  public int getSerialNumber() {
    return this.serial;
  }

  /** Returns the current number of tickets in the ticket cache. */
  public int getCacheSize() {
    return ticketCache.size();
  }
}
