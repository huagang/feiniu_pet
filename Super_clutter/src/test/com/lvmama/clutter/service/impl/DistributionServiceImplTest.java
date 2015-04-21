package com.lvmama.clutter.service.impl;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.NoSuchAlgorithmException;

import org.dom4j.io.SAXReader;
import org.junit.Test;

import com.lvmama.comm.utils.MD5;
import com.lvmama.distribution.client.WebServiceClient;
import com.lvmama.distribution.util.DistributionUtil;

public class DistributionServiceImplTest {
	static String wsdl = "http://180.169.51.94/clutter/services/distributionService?wsdl";
//	static String wsdl = "http://192.168.0.238:8035/clutter/services/distributionService?wsdl";
//	static String wsdl = "http://192.168.0.243/clutter/services/distributionService?wsdl";
//	static String wsdl = "http://192.168.0.249:8035/clutter/services/distributionService?wsdl";
//	static String wsdl = "http://localhost/Super_clutter/services/distributionService?wsdl";
	//线上地址
//	static String wsdl="http://114.80.83.165/clutter/services/distributionService?wsdl"; 
//	static String wsdl = "http://super.lvmama.com/clutter/services/distributionService?wsdl";
//	static String wsdl = "http://180.169.51.94/clutter/services/distributionService?wsdl";
	@Test
	public void testCreateOrder()throws Exception {
		String xmlStr = this.getXmlStr("createOrder.xml");
		Object[] obj = WebServiceClient.getClientInstance().execute(wsdl, new Object[]{xmlStr}, "createOrder");
		String result = obj[0].toString();
		System.out.println(result);
	}
	
	@Test
	public void testGetOrder() throws Exception {
		String xmlStr = this.getXmlStr("getOrder.xml");
		Object[] obj = WebServiceClient.getClientInstance().execute(wsdl, new Object[]{xmlStr}, "getOrder");
		String result = obj[0].toString();
		System.out.println(result);
	}

	@Test
	public void testGetProductInfo() throws Exception{
		String xmlStr = this.getXmlStr("getProductInfo.xml");
		Object[] obj = WebServiceClient.getClientInstance().execute(wsdl, new Object[]{xmlStr}, "getProductInfo");
		String result = obj[0].toString();
		System.out.println(result);
	}

	@Test
	public void testGetProductPrice() throws Exception{
		String xmlStr = this.getXmlStr("getProductPrice.xml");
		Object[] obj = WebServiceClient.getClientInstance().execute(wsdl, new Object[]{xmlStr}, "getProductPrice");
		String result = obj[0].toString();
		System.out.println(result);
		OutputStream out =  new FileOutputStream("d:/222.text");
		out.write(DistributionUtil.decode(result));
		out.flush();
	}

	@Test
	public void testProductInfoList() throws Exception{
		String xmlStr = this.getXmlStr("productInfoList.xml");
		Object[] obj = WebServiceClient.getClientInstance().execute(wsdl, new Object[]{xmlStr}, "productInfoList");
		String result = obj[0].toString();
		System.out.println(result);
	}

	@Test
	public void testProductPriceList() throws Exception{
		String xmlStr = this.getXmlStr("productPriceList.xml");
		Object[] obj = WebServiceClient.getClientInstance().execute(wsdl, new Object[] { xmlStr }, "productPriceList");
		String result = obj[0].toString();
		System.out.println(result);
	}

	@Test
	public void testResendCode() throws Exception{
		String xmlStr = this.getXmlStr("resendCode.xml");
		Object[] obj = WebServiceClient.getClientInstance().execute(wsdl, new Object[]{xmlStr}, "resendCode");
		String result = obj[0].toString();
		System.out.println(result);
	}

	@Test
	public void testUpdateOrderStatus()throws Exception {
		String xmlStr = this.getXmlStr("updateOrderStatus.xml");
		Object[] obj = WebServiceClient.getClientInstance().execute(wsdl, new Object[]{xmlStr}, "updateOrderStatus");
		String result = obj[0].toString();
		System.out.println(result);
	}
	
	@Test
	public void testGetProductOnline()throws Exception {
		String xmlStr = this.getXmlStr("getProductOnLine.xml");
		Object[] obj = WebServiceClient.getClientInstance().execute(wsdl, new Object[]{xmlStr}, "getProductOnLine");
		String result = obj[0].toString();
		System.out.println(result);
	}
	/**
	 * 订单状态审核
	 * @throws Exception
	 */
	@Test
	public void testGetOrderApprove() throws Exception {
		String xmlStr = this.getXmlStr("getOrderApprove.xml");
		Object[] obj = WebServiceClient.getClientInstance().execute(wsdl, new Object[] { xmlStr }, "getOrderApprove");
		String result = obj[0].toString();
		System.out.println(result);
	}
	/**
	 * 订单验证
	 */
	@Test
	public void testValidateOrder() throws Exception {
		String xmlStr = this.getXmlStr("validateOrder.xml");
		Object[] obj = WebServiceClient.getClientInstance().execute(wsdl, new Object[] { xmlStr }, "validateOrder");
		String result = obj[0].toString();
		System.out.println(result);
	}
	/**
	 * 状态验证回调
	 * @throws Exception
	 */
	@Test
	public void orderApproveCallBack() throws Exception {
		String xmlStr = this.getXmlStr("orderApproveCallBack.xml");
		Object[] obj = WebServiceClient.getClientInstance().execute(wsdl, new Object[] { xmlStr }, "orderApproveCallBack");
		String result = obj[0].toString();
		System.out.println(result);
	}
	
	
	String getXmlStr(String fileName) throws Exception{
		InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("com/lvmama/clutter/service/impl/"+fileName);
		SAXReader reader = new SAXReader();
		org.dom4j.Document document = reader.read(inputStream);
		return document.asXML();
	}
	@Test
	public void testdecode(){
		//String str="PG9yZGVyTGlzdD48b3JkZXI+PG9yZGVySWQ+MTMwNjYzNDwvb3JkZXJJZD48c3RhdHVzPnRydWU8L3N0YXR1cz48d2FpdFBheW1lbnQ+MjAxMy0wMS0wNCAxNTowMjwvd2FpdFBheW1lbnQ+PC9vcmRlcj48L29yZGVyTGlzdD4=";
				//"PG9yZGVyTGlzdD48b3JkZXI+PG9yZGVySWQ+MTMwNjYzNDwvb3JkZXJJZD48c3RhdHVzPnRydWU8L3N0YXR1cz48d2FpdFBheW1lbnQ+MjAxMy0wMS0wNCAxNTowMjwvd2FpdFBheW1lbnQ+PC9vcmRlcj48L29yZGVyTGlzdD4=";
		String str="PHByb2R1Y3RMaXN0Pjxwcm9kdWN0Pjxwcm9kdWN0SWQ+MjMwMDwvcHJvZHVjdElkPjxwbGFjZUlkPjEwMDcyMzwvcGxhY2VJZD48cHJvZHVjdE5hbWU+55yL5LiA5LiL5qCH562+fjwvcHJvZHVjdE5hbWU+PHByb2R1Y3RUeXBlPlRJQ0tFVDwvcHJvZHVjdFR5cGU+PHBheW1lbnRUeXBlPm9ubGluZTwvcGF5bWVudFR5cGU+PHByb2R1Y3RPbkxpbmU+dHJ1ZTwvcHJvZHVjdE9uTGluZT48b25saW5lVGltZT4yMDExLTAxLTAxPC9vbmxpbmVUaW1lPjxvZmZsaW5lVGltZT4yMDEzLTEyLTMxPC9vZmZsaW5lVGltZT48cGxhY2VGcm9tPjwvcGxhY2VGcm9tPjxwbGFjZVRvPuS4iua1t+mHkeiMguWkp+WOpjwvcGxhY2VUbz48ZGF5cz4wPC9kYXlzPjxjYXJkTm8+ZmFsc2U8L2NhcmRObz48cmVjb21tZW5kPjwhW0NEQVRBW+ebruWJjeWbveWGheacgOWkp+eahOinguWFieWOhQrmrKPotY/lppnkuI3lj6/oqIDnmoTmnJ3pnJ7kuI7okL3ml6UK6KeC6LWP5LiW55WM5Li+5LiW5peg5Y+M55qE6YWS5bqX5Lit5bqtCuS4juiRl+WQjeeahOWklua7qemjjuaZr+WMuumalOaxn+ebuOacm11dPjwvcmVjb21tZW5kPjxwb3N0PjwhW0NEQVRBWzEuOeaciDEzfjMw5pel5pyf6Ze05q+P5ZGo5LiAfuWRqOS6lOmXqOelqDfmipjvvIzor7foh6rooYzoh7Pmma/ljLrliY3lj7DotK3kubAKMi4yMDExLjEyLjMx5pmv5Yy66Zeo5biC5Lu36Zeo56Wo5Y2K5Lu377yM5aaC6ZyA6LSt56Wo6K+36Iez5pmv5Yy65ZSu56Wo5aSEXV0+PC9wb3N0Pjxjb3N0SW5jbHVkZT48IVtDREFUQVvph5HojILlpKfljqY4OOWxguinguWFieWOheaIkOS6uumXqOelqDHlvKBdXT48L2Nvc3RJbmNsdWRlPjxjb3N0Tm90SW5jbHVkZT48IVtDREFUQVtdXT48L2Nvc3ROb3RJbmNsdWRlPjxyZWNvbW1lbmRQcm9qZWN0PjwhW0NEQVRBW+aXoF1dPjwvcmVjb21tZW5kUHJvamVjdD48c2hvcHBpbmdFeHBsYW5hdGlvbj48IVtDREFUQVtdXT48L3Nob3BwaW5nRXhwbGFuYXRpb24+PHJlZnVuZG1lbnRFeHBsYW5hdGlvbj48IVtDREFUQVvml6BdXT48L3JlZnVuZG1lbnRFeHBsYW5hdGlvbj48Ym9va2luZ0luZm9ybWF0aW9uPjwhW0NEQVRBW+WPluelqOWcsOWdgO+8muS4iua1t+a1puS4nOS4lue6quWkp+mBkzg45Y+36YeR6IyC5aSn5Y6mQjHlsYLop4LlhYnljoXllK7npajlpITvvIzlh63kuoznu7TnoIHnn63kv6HmjaLnpajlhaXlm63vvIznn63kv6HkuI3og73liKDpmaTjgIHkuI3og73ovazlj5ENCuW8gOWbreaXtumXtO+8mjg6MzAtMjE6MzBdXT48L2Jvb2tpbmdJbmZvcm1hdGlvbj48cmVtaW5kPjwhW0NEQVRBW+eJueWIq+aPkOmGku+8mgrvvIgx77yJMC4457Gz5Lul5LiL5YS/56ul77yI6ZyA5a626ZW/6Zmq5ZCM77yJ44CB546w5b255Yab5Lq65Yet5Yab5a6Y6K+B44CB56a75LyR5bmy6YOo5Yet56a75LyR6K+B5YWN56Wo44CCCu+8iDLvvIkwLjjnsbMtMS4z57Gz5Y+v5YmN5b6A5pmv5Yy66LSt5Lmw5YS/56ul5LyY5oOg56Wo44CCXV0+PC9yZW1pbmQ+PHNlcnZpY2VHdWFyYW50ZWU+PCFbQ0RBVEFb5aaC5oKo5Zyo6am05aaI5aaI55qE6K6i6LSt5Lu35qC86auY5LqO5pmv54K555qE5b2T5aSp6Zeo5biC5Lu377yM5oiR5Lus5peg5p2h5Lu26YCA6L+Y5beu5Lu344CCIA0K6am05aaI5aaI572R56uZ5LiK55qE5pmv54K55Lu35qC85q2j5bi45oOF5Ya15LiL6YO95L2O5LqO5pmv54K56Zeo5biC5Lu377yM5LyY5oOg5pyA5L2O6L6+5YiwMeaKmOOAgiANCuWcqOeJueauiuaDheWGteS4i++8jOaZr+eCueS8muWBmuS4tOaXtuS/g+mUgO+8jOmXqOW4guS7t+agvOWBmuWHuuWkp+W5heiwg+aVtO+8jOayoeacieWPiuaXtumAmuefpeWIsOaIkeS7rO+8jOWvvOiHtOmptOWmiOWmiOe9keermeeahOS7t+agvOWPr+iDveS8mumrmOS6juW9k+aXtueahOmXqOW4guS7t+OAguWcqOi/meenjeaDheWGteS4i++8jOaIkeS7rOmDkemHjeaJv+ivuu+8jOaXoOadoeS7tumAgOi/mOW3ruS7t+e7meeUqOaIt+OAguaCqOWPqumcgOiHtOeUteaIkeS7rOWuouacjTEwMTAgNjA2MO+8jOaIkeS7rOWcqOWSjOaZr+eCueaguOWunuS7t+agvOWPmOWKqOaDheWGteaXoOivr+WQjuWwhuaXoOadoeS7tumAgOi/mOaCqOWkmuS7mOeahOW3ruS7t+OAgl1dPjwvc2VydmljZUd1YXJhbnRlZT48dmlzaXRQb2ludD48IVtDREFUQVtdXT48L3Zpc2l0UG9pbnQ+PHRyYWZmaWNJbmZvPjwhW0NEQVRBW11dPjwvdHJhZmZpY0luZm8+PHZpc2E+PCFbQ0RBVEFbXV0+PC92aXNhPjxwcm9kdWN0Q2hhcmFjdGVyaXN0aWM+PCFbQ0RBVEFbPHAgYWxpZ249ImNlbnRlciI+PGVtYmVkIHNyYz0iaHR0cDovL3d3dy5qaW5tYW84OC5jb20vaW1hZ2VzL2ptLnN3ZiIgdHlwZT0iYXBwbGljYXRpb24veC1zaG9ja3dhdmUtZmxhc2giIHdpZHRoPSI1ODIiIGhlaWdodD0iMTc3IiBxdWFsaXR5PSJoaWdoIiAvPjwvcD4KPHA+PHNwYW4gc3R5bGU9ImZvbnQtc2l6ZToxNHB4OyI+4peP6YeR6IyC5aSn5Y6m6KeC5YWJ5Y6F55qE546755KD5bmV5aKZ6KeG6YeO5byA6ZiU77yM5Yet5qCP6L+c55y677yM6buE5rWm5rGf5Lik5bK455qE6YO95biC6aOO5YWJ5Lul5Y+K6ZW/5rGf5Y+j55qE5aOu5Li95pmv6Imy5bC95pS255y85bqV44CCPC9zcGFuPjxiciAvPgo8c3BhbiBzdHlsZT0iZm9udC1zaXplOjE0cHg7Ij7il4/lnKjov5nph4zvvIzkvaDlj6/ku6XmrKPotY/lppnkuI3lj6/oqIDnmoTmnJ3pnJ7kuI7okL3ml6XvvJvotY/lrozlpJbmma/vvIzmgqjov5jlj6/ku6Xku47kuIrlvoDkuIvop4LotY/kuJbnlYzkuL7kuJbml6Dlj4znmoTphZLlupfkuK3luq3vvIzlroPnm7TlvoQyN+exs++8jDI46YGT546v5buK5om25omL5Zyo6ZyT6Jm554Gv55qE54Wn5bCE5LiL77yM6YeR5YWJ6Zeq6Zeq44CCPC9zcGFuPjxiciAvPgo8c3BhbiBzdHlsZT0iZm9udC1zaXplOjE0cHg7Ij7il4/lnKg4OOWxguinguWFieWOheeci+WknOaZr+abtOaYr+S4gOenjeS6q+WPl++8jOiiq+iqieS4uuS4h+WbveW7uuetkeWNmuiniOS8mueahOWklua7qea1geWFiea6ouW9qe+8jOe+juS4jeiDnOaUtuOAgui/nOWkhOmrmOaetumBk+i3r+WSjOWkp+ahpeS4iueahOeBr+eBq+S8vOW9qee7g+eOr+e7le+8jOS4juaYn+aciOS6iei+ie+8jOatpOaDheatpOaZr+S7pOaCqOeKueWmgue9rui6q+iTrOiOseS7meWig+OAgjwvc3Bhbj48L3A+CjxwIGFsaWduPSJjZW50ZXIiPjxlbWJlZCBzcmM9Imh0dHA6Ly93d3cuamlubWFvODguY29tL2ltYWdlcy9nZy5zd2YiIHR5cGU9ImFwcGxpY2F0aW9uL3gtc2hvY2t3YXZlLWZsYXNoIiB3aWR0aD0iNTg0IiBoZWlnaHQ9IjIyNCIgcXVhbGl0eT0iaGlnaCIgLz48L3A+CjxwIGFsaWduPSJjZW50ZXIiPjxzcGFuIHN0eWxlPSJjb2xvcjojNjY2NjY2O2ZvbnQtc2l6ZToxNnB4OyI+44CQ54K56K+E5LiT6aKY44CRPC9zcGFuPjxhIHN0eWxlPSJmb250LXdlaWdodDpib2xkO3RleHQtZGVjb3JhdGlvbjp1bmRlcmxpbmU7IiBocmVmPSJodHRwOi8vd3d3Lmx2bWFtYS5jb20vY29tbWVudC96dC9qaWFuemh1LyIgdGFyZ2V0PSJfYmxhbmsiPjxzcGFuIHN0eWxlPSJjb2xvcjojY2MzMzAwO2ZvbnQtc2l6ZToxNnB4OyI+5Luw5pyb5LiK5rW35rup5LmL5Y2B5aSn54m56Imy5bu6562RIOW/heWOu++8gTwvc3Bhbj48L2E+PC9wPgo8aDM+PGEgaHJlZj0iaHR0cDovL3d3dy5sdm1hbWEuY29tL2Rlc3QvamlubWFvZGFzaGEvY29tbWVudCIgdGFyZ2V0PSJfYmxhbmsiPuS4iua1t+mHkeiMguWkp+WOpjwvYT48L2gzPgo8ZGl2IHN0eWxlPSJwYWRkaW5nOjBweDttYXJnaW46MHB4OyI+CjxkaXYgY2xhc3M9ImNvbnRfbCI+CjxoND48YSBocmVmPSJodHRwOi8vd3d3Lmx2bWFtYS5jb20vY29tbWVudC83MTE5ODEiIHRhcmdldD0iX2JsYW5rIj7ph5HojILlpKfljqbmmI7kv6HniYcg6LS05b+D55qE5oOK5ZacPC9hPjwvaDQ+CjxwPjxzcGFuIHN0eWxlPSJjb2xvcjojY2M2NjAwOyI+572R5Y+LImVuZ2luZTQzNiLor7TvvJo8L3NwYW4+PGJyIC8+CuacgOWAvOW+l+S4gOaPkOeahOaYr+WcqOmHkeiMguWkp+WOpjg45bGC55qE6KeC5YWJ5Y6F55yL5Yiw5a+E5piO5L+h54mH55qE77yM6ICM5LiU6L+Y55uW5LqG6YeR6IyC5aSn5Y6m55qE57qq5b+156ug77yM6LWe4oCmPGEgaHJlZj0iaHR0cDovL3d3dy5sdm1hbWEuY29tL2Rlc3QvamlubWFvZGFzaGEvY29tbWVudCIgdGFyZ2V0PSJfYmxhbmsiPlvlhbEyNTXmnaHngrnor4RdPC9hPjwvcD4KPGg1PjxhIGhyZWY9Imh0dHA6Ly93d3cubHZtYW1hLmNvbS9jb21tZW50L2NvbW1lbnQhc2hvd0NtdFBsYWNlQWRkLmRvP3BsYWNlSWQ9MTAwNzIzIiB0YXJnZXQ9Il9ibGFuayI+5oiR5Y676L+HIOaIkeimgeWPkeiogCZndDsmZ3Q7PC9hPjwvaDU+CjxkaXYgY2xhc3M9InNjb3JlIj48c3Bhbj7nvZHlj4vng63ojZDvvJo8L3NwYW4+IDwvZGl2Pgo8ZGl2IGNsYXNzPSJzY29yZSI+PHNwYW4+57K+5b2p55yL54K577yaPC9zcGFuPiA8c3Bhbj48YSBocmVmPSJodHRwOi8vd3d3Lmx2bWFtYS5jb20vZGVzdC9qaW5tYW9kYXNoYSIgdGFyZ2V0PSJfYmxhbmsiPjg45bGC6KeC5YWJ5Y6F55qE6ZyH5pK8PC9hPjwvc3Bhbj48L2Rpdj4KPC9kaXY+CjxkaXYgY2xhc3M9ImNvbnRfciI+PGEgdGl0bGU9IuS4iua1t+mHkeiMguWkp+WOpiIgaHJlZj0iaHR0cDovL3d3dy5sdm1hbWEuY29tL2Rlc3QvamlubWFvZGFzaGEvY29tbWVudCIgdGFyZ2V0PSJfYmxhbmsiPjxpbWcgYWx0PSLkuIrmtbfph5HojILlpKfljqYiIHNyYz0iaHR0cDovL3d3dy5sdm1hbWEuY29tL2NvbW1lbnQvenQvamlhbnpodS9pbWcvc2hfam1kc18yMDV4MTgwLmpwZyIgaGVpZ2h0PSIxODAiIHdpZHRoPSIyMDUiIC8+PC9hPjxhIHRpdGxlPSLkuIrmtbfph5HojILlpKfljqYiIGhyZWY9Imh0dHA6Ly93d3cubHZtYW1hLmNvbS9kZXN0L2ppbm1hb2Rhc2hhL2NvbW1lbnQiIHRhcmdldD0iX2JsYW5rIj48aW1nIHN0eWxlPSJtYXJnaW4tYm90dG9tOjEwcHg7IiBhbHQ9IuS4iua1t+mHkeiMguWkp+WOpiIgc3JjPSJodHRwOi8vd3d3Lmx2bWFtYS5jb20vY29tbWVudC96dC9qaWFuemh1L2ltZy9zaF9qbTAxXzg1eDg1LmpwZyIgaGVpZ2h0PSI4NSIgd2lkdGg9Ijg1IiAvPjwvYT48YSB0aXRsZT0i5LiK5rW36YeR6IyC5aSn5Y6mIiBocmVmPSJodHRwOi8vd3d3Lmx2bWFtYS5jb20vZGVzdC9qaW5tYW9kYXNoYS9jb21tZW50IiB0YXJnZXQ9Il9ibGFuayI+PGltZyBhbHQ9IuS4iua1t+mHkeiMguWkp+WOpiIgc3JjPSJodHRwOi8vd3d3Lmx2bWFtYS5jb20vY29tbWVudC96dC9qaWFuemh1L2ltZy9zaF9qbTAyXzg1eDg1LmpwZyIgaGVpZ2h0PSI4NSIgd2lkdGg9Ijg1IiAvPjwvYT48L2Rpdj4KPC9kaXY+CjxzcGFuIHN0eWxlPSJmb250LWZhbWlseTrlvq7ova/pm4Xpu5E7Ij48c3BhbiBzdHlsZT0iZm9udC1mYW1pbHk6TWljcm9zb2Z0IFlhSGVpOyI+PHN0cm9uZz48c3BhbiBzdHlsZT0iZm9udC1mYW1pbHk6TWljcm9zb2Z0IFlhSGVpO2ZvbnQtc2l6ZToxNnB4OyI+PHNwYW4gc3R5bGU9IndpZG93czoyO3RleHQtdHJhbnNmb3JtOm5vbmU7dGV4dC1pbmRlbnQ6MHB4O2JvcmRlci1jb2xsYXBzZTpzZXBhcmF0ZTtmb250Om1lZGl1bSAnVGltZXMgTmV3IFJvbWFuJzt3aGl0ZS1zcGFjZTpub3JtYWw7b3JwaGFuczoyO2xldHRlci1zcGFjaW5nOm5vcm1hbDtjb2xvcjojMDAwMDAwO3dvcmQtc3BhY2luZzowcHg7IiBjbGFzcz0iQXBwbGUtc3R5bGUtc3BhbiI+PHNwYW4gc3R5bGU9ImxpbmUtaGVpZ2h0OjE4cHg7Zm9udC1mYW1pbHk6VGFob21hLOWui+S9kyxzYW5zLXNlcmlmO2NvbG9yOiMxMTExMTE7Zm9udC1zaXplOjEycHg7IiBjbGFzcz0iQXBwbGUtc3R5bGUtc3BhbiI+PHNwYW4gc3R5bGU9ImZvbnQtZmFtaWx5Ok1pY3Jvc29mdCBZYUhlaTsiPjxzcGFuIHN0eWxlPSJ3aWRvd3M6Mjt0ZXh0LXRyYW5zZm9ybTpub25lO3RleHQtaW5kZW50OjBweDtib3JkZXItY29sbGFwc2U6c2VwYXJhdGU7Zm9udDptZWRpdW0gJ1RpbWVzIE5ldyBSb21hbic7d2hpdGUtc3BhY2U6bm9ybWFsO29ycGhhbnM6MjtsZXR0ZXItc3BhY2luZzpub3JtYWw7Y29sb3I6IzAwMDAwMDt3b3JkLXNwYWNpbmc6MHB4OyIgY2xhc3M9IkFwcGxlLXN0eWxlLXNwYW4iPjxzcGFuIHN0eWxlPSJsaW5lLWhlaWdodDoxOHB4O2ZvbnQtZmFtaWx5OidzYW5zIHNlcmlmJyx0YWhvbWEsdmVyZGFuYSxoZWx2ZXRpY2E7Zm9udC1zaXplOjEycHg7IiBjbGFzcz0iQXBwbGUtc3R5bGUtc3BhbiI+PHNwYW4gc3R5bGU9IndpZG93czoyO3RleHQtdHJhbnNmb3JtOm5vbmU7dGV4dC1pbmRlbnQ6MHB4O2JvcmRlci1jb2xsYXBzZTpzZXBhcmF0ZTtmb250Om1lZGl1bSAnVGltZXMgTmV3IFJvbWFuJzt3aGl0ZS1zcGFjZTpub3JtYWw7b3JwaGFuczoyO2xldHRlci1zcGFjaW5nOm5vcm1hbDtjb2xvcjojMDAwMDAwO3dvcmQtc3BhY2luZzowcHg7IiBjbGFzcz0iQXBwbGUtc3R5bGUtc3BhbiI+PHNwYW4gc3R5bGU9ImxpbmUtaGVpZ2h0OjE4cHg7Zm9udC1mYW1pbHk6J3NhbnMgc2VyaWYnLHRhaG9tYSx2ZXJkYW5hLGhlbHZldGljYTtmb250LXNpemU6MTJweDsiIGNsYXNzPSJBcHBsZS1zdHlsZS1zcGFuIj48c3BhbiBzdHlsZT0id2lkb3dzOjI7dGV4dC10cmFuc2Zvcm06bm9uZTt0ZXh0LWluZGVudDowcHg7Ym9yZGVyLWNvbGxhcHNlOnNlcGFyYXRlO2ZvbnQ6bWVkaXVtICdUaW1lcyBOZXcgUm9tYW4nO3doaXRlLXNwYWNlOm5vcm1hbDtvcnBoYW5zOjI7bGV0dGVyLXNwYWNpbmc6bm9ybWFsO2NvbG9yOiMwMDAwMDA7d29yZC1zcGFjaW5nOjBweDsiIGNsYXNzPSJBcHBsZS1zdHlsZS1zcGFuIj48c3BhbiBzdHlsZT0ibGluZS1oZWlnaHQ6MThweDtmb250LWZhbWlseTonc2FucyBzZXJpZicsdGFob21hLHZlcmRhbmEsaGVsdmV0aWNhO2ZvbnQtc2l6ZToxMnB4OyIgY2xhc3M9IkFwcGxlLXN0eWxlLXNwYW4iPjxpbWcgYWx0PSIiIHNyYz0iaHR0cDovL3BpYy5sdm1hbWEuY29tL3BpY3Mvc3VwZXIvMjAxMS8wNC9FSzlZRi5qcGciIGJvcmRlcj0iMCIgLz48L3NwYW4+PC9zcGFuPjwvc3Bhbj48L3NwYW4+PC9zcGFuPjwvc3Bhbj48L3NwYW4+PC9zcGFuPjwvc3Bhbj48L3NwYW4+PC9zdHJvbmc+PC9zcGFuPjwvc3Bhbj48YnIgLz4KPHA+PHN0cm9uZz48c3BhbiBzdHlsZT0iZm9udC1zaXplOjE0cHg7Ij7jgJDkuqTpgJrkv6Hmga/jgJHvvJo8L3NwYW4+PC9zdHJvbmc+PHNwYW4gc3R5bGU9ImNvbG9yOiNmZjAwMDA7Zm9udC1zaXplOjE0cHg7Ij7vvIjlpoLlj5HnjrDplJnor6/miJbmnInmm7Tlpb3lu7rorq7vvIzmrKLov47oh7TnlLUxMDEwIDYwNjDvvIk8YnIgLz4KPC9zcGFuPjxzcGFuIHN0eWxlPSJjb2xvcjojZmYwMDAwO2ZvbnQtc2l6ZToxNHB4OyI+5YWs5YWx5Lqk6YCa77yaPGJyIC8+Cjwvc3Bhbj48L3A+CjxwPjxzcGFuIHN0eWxlPSJmb250LXNpemU6MTRweDsiPuWFrOS6pO+8mjgx44CBODLjgIE4NzDjgIE4NzHjgIE4NzLjgIHpmqfpgZMzIOe6v+OAgTTnur/jgIE157q/44CBNue6v+etiTwvc3Bhbj48c3BhbiBzdHlsZT0iZm9udC1zaXplOjE0cHg7Ij7lnLDpk4HvvJrluILlhoXkuZjlnZDlnLDpk4Hkuozlj7fnur/pmYblrrblmLTnq5nkuIvljbPlj688L3NwYW4+PC9wPgoKPHAgc3R5bGU9Im1hcmdpbjo1cHggMHB4OyI+PHN0cm9uZz48aW1nIHNyYz0iaHR0cDovL3BpYy5sdm1hbWEuY29tL3BpY3Mvc3VwZXIvMjAxMS8wNC9UN0I0TS5qcGciIHN0eWxlPSJkaXNwbGF5OmlubGluZTsiIGFsdD0iIiBib3JkZXI9IjAiIC8+PC9zdHJvbmc+PC9wPgo8c3Ryb25nPiA8cCBzdHlsZT0ibWFyZ2luOjVweCAwcHg7Ij48c3Ryb25nIHN0eWxlPSJmb250LXdlaWdodDpub3JtYWw7IiB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMTk5OS94aHRtbCI+PGEgdGFyZ2V0PSJfYmxhbmsiIGhyZWY9Imh0dHA6Ly93d3cubHZtYW1hLmNvbS9wcm9kdWN0LzI0NTY0Ij48c3BhbiBzdHlsZT0iZm9udC1zaXplOjE0cHg7Ij7igJznq6Xlv4PlkIzkuZDigJ3ph5HojILlpKfljqY4OOWxguinguWFieWOheS6suWtkOa4uOWll+elqO+8iDLlpKcx5bCP5ZGo5YWt5LiT5Lqr77yJPC9zcGFuPjwvYT48YnIgLz4KPGEgdGFyZ2V0PSJfYmxhbmsiIGhyZWY9Imh0dHA6Ly93d3cubHZtYW1hLmNvbS9wcm9kdWN0LzQyMTUzIj48c3BhbiBzdHlsZT0iZm9udC1zaXplOjE0cHg7Ij7kuIrmtbfph5HojILlpKfljqY4OOWxguinguWFieWOheOAgeWFq+mfs+ebkuePjeWTgemZiOWIl+mmhuaIkOS6uuiBlOelqDwvc3Bhbj48L2E+PC9zdHJvbmc+PGEgdGFyZ2V0PSJfYmxhbmsiIGhyZWY9Imh0dHA6Ly93d3cubHZtYW1hLmNvbS9wcm9kdWN0LzQyMTUzIj48L2E+PHN0cm9uZz48c3Ryb25nPjxhIGhyZWY9Imh0dHA6Ly93d3cubHZtYW1hLmNvbS9wcm9kdWN0LzI3NjcyIiB0YXJnZXQ9Il9ibGFuayI+PGltZyBzcmM9Imh0dHA6Ly9waWMubHZtYW1hLmNvbS9waWNzL3N1cGVyLzIwMTEvMDUvSjhEUFcuZ2lmIiBzdHlsZT0iZGlzcGxheTppbmxpbmU7IiBhbHQ9IiIgYm9yZGVyPSIwIiAvPjwvYT48L3N0cm9uZz48L3N0cm9uZz48L3A+Cjwvc3Ryb25nPl1dPjwvcHJvZHVjdENoYXJhY3RlcmlzdGljPjx0cmF2ZWxFeHBsYW5hdGlvbj48L3RyYXZlbEV4cGxhbmF0aW9uPjxwcm9kdWN0QnJhbmNoPjxicmFuY2g+PGJyYW5jaElkPjIzMDA8L2JyYW5jaElkPjxicmFuY2hOYW1lPuaIkOS6uumXqOelqDwvYnJhbmNoTmFtZT48YnJhbmNoT25MaW5lPnRydWU8L2JyYW5jaE9uTGluZT48bWluaW11bT4xPC9taW5pbXVtPjxtYXhpbXVtPjEwMDwvbWF4aW11bT48YWR1bHRRdWFudGl0eT4xPC9hZHVsdFF1YW50aXR5PjxjaGlsZFF1YW50aXR5PjA8L2NoaWxkUXVhbnRpdHk+PC9icmFuY2g+PC9wcm9kdWN0QnJhbmNoPjxpbWFnZXM+PHNtYWxsPmh0dHA6Ly9waWMubHZtYW1hLmNvbS8yMDB4MTAwL3BpY3Mvc3VwZXIvMjAxMS8wMi81SE1BSy5qcGc8L3NtYWxsPjxtaWRkbGU+PC9taWRkbGU+PGxhcmdlPmh0dHA6Ly9waWMubHZtYW1hLmNvbS81ODB4MjkwL3BpY3Mvc3VwZXIvMjAxMS8wMi81SE1BSy5qcGc8L2xhcmdlPjwvaW1hZ2VzPjwvcHJvZHVjdD48cHJvZHVjdD48cHJvZHVjdElkPjEwNjwvcHJvZHVjdElkPjxwbGFjZUlkPjEwMTI2ODwvcGxhY2VJZD48cHJvZHVjdE5hbWU+5qGQ5bqQ5Z6C5LqR6YCa5aSp5rKz5pmv5Yy65oiQ5Lq66Zeo56WoPC9wcm9kdWN0TmFtZT48cHJvZHVjdFR5cGU+VElDS0VUPC9wcm9kdWN0VHlwZT48cGF5bWVudFR5cGU+b25saW5lPC9wYXltZW50VHlwZT48cHJvZHVjdE9uTGluZT50cnVlPC9wcm9kdWN0T25MaW5lPjxvbmxpbmVUaW1lPjIwMTEtMDEtMDE8L29ubGluZVRpbWU+PG9mZmxpbmVUaW1lPjIwMTMtMTItMzE8L29mZmxpbmVUaW1lPjxwbGFjZUZyb20+PC9wbGFjZUZyb20+PHBsYWNlVG8+5qGQ5bqQ5Z6C5LqR6YCa5aSp5rKz5pmv5Yy6PC9wbGFjZVRvPjxkYXlzPjA8L2RheXM+PGNhcmRObz5mYWxzZTwvY2FyZE5vPjxyZWNvbW1lbmQ+PCFbQ0RBVEFb5Zu95YaF6aaW5o6o55qE5Y+k5pqX5rKz5o6i6Zmp77yM5Li65Zu95YaF6aKG5YWI77ybIArmmpfmsrPlh7rlj6PlpITnmoTpq5jlsbHmu5HpgZPvvJsgCuWNg+exs+aal+ays+S5mOiIn+inguaZr++8mwrpgJrlpKnmsrPkuKTlsrjpo47lhYnml6DpmZDvvJtdXT48L3JlY29tbWVuZD48cG9zdD48IVtDREFUQVsxLjTnsbPku6XkuIvlhL/nq6Xkuqvlj5flhL/nq6Xpl6jnpajku7fmoLxdXT48L3Bvc3Q+PGNvc3RJbmNsdWRlPjwhW0NEQVRBWzEu5Z6C5LqR6YCa5aSp5rKz5pmv5Yy65oiQ5Lq66Zeo56WoMeW8oF1dPjwvY29zdEluY2x1ZGU+PGNvc3ROb3RJbmNsdWRlPjwhW0NEQVRBW11dPjwvY29zdE5vdEluY2x1ZGU+PHJlY29tbWVuZFByb2plY3Q+PCFbQ0RBVEFb5pegXV0+PC9yZWNvbW1lbmRQcm9qZWN0PjxzaG9wcGluZ0V4cGxhbmF0aW9uPjwhW0NEQVRBW+aXoF1dPjwvc2hvcHBpbmdFeHBsYW5hdGlvbj48cmVmdW5kbWVudEV4cGxhbmF0aW9uPjwhW0NEQVRBW+aXoF1dPjwvcmVmdW5kbWVudEV4cGxhbmF0aW9uPjxib29raW5nSW5mb3JtYXRpb24+PCFbQ0RBVEFb5Y+W56Wo5Zyw5Z2A77ya5rWZ5rGf55yB5qGQ5bqQ5Y6/55G255Cz6ZWH5Lic55Cz5p2R5pmv5Yy66Zeo5Y+j6K6h6LCD5aSE77yM5Yi35LqM57u056CB55+t5L+h5o2i56Wo5YWl5Zut77yM55+t5L+h5LiN6IO95Yig6Zmk44CB5LiN6IO96L2s5Y+RCuW8gOWbreaXtumXtO+8mjg6MDAtMTY6MzBdXT48L2Jvb2tpbmdJbmZvcm1hdGlvbj48cmVtaW5kPjwhW0NEQVRBW+OAkOWwj+W4luWjq+OAke+8mg0KMS4g5bu66K6u6Ieq5aSH5omL55S177yM5L6/5LqO5Zyo6buR5pqX5aSE5pu05aW96KeC6LWP5Zyw5LiL6ZW/5rKz44CCDQoyLiDmjqjojZDkvY/lrr/igJzplKbmsZ/kuYvmmJ/igJ3moZDlupDlupfvvIzkvY3kuo4wNeecgemBk+aXge+8jOi3neemu+aZr+WMuuS7hTMw5YiG6ZKf6L2m56iL44CCDQrjgJDkuqTpgJrkv6Hmga/jgJHvvJog77yI5aaC5Y+R546w6ZSZ6K+v5oiW5pyJ5pu05aW95bu66K6u77yM5qyi6L+O6Ie055S1MTAxMCA2MDYw77yJDQroh6rpqb7ot6/kuabvvJoNCuS4iua1t+WHuuWPke+8muayquadreKAlOKAlOadreW3nue7leWfjuKAlOKAlOadreaWsOaZr+KAlOKAlOahkOW6kOWHuuWPo+S4i+KAlOKAlOaZr+WMuu+8iOWFqOeoi+e6pjMuNeWwj+aXtu+8iQ0K5p2t5bee5Ye65Y+R77ya5p2t5bee57uV5Z+O4oCU4oCU5p2t5paw5pmv4oCU4oCU5qGQ5bqQ5Ye65Y+j5LiL4oCU4oCU5pmv5Yy677yI5YWo56iL57qmMS415bCP5pe277yJDQrljZfkuqzlh7rlj5HvvJrmna3lroHigJTigJTmna3lt57nu5Xln47igJTigJTmna3mlrDmma/igJTigJTmoZDlupDlh7rlj6PkuIvigJTigJTmma/ljLrvvIjlhajnqIvnuqY0LjXlsI/ml7bvvIkNCuWFrOWFseS6pOmAmg0K5LiK5rW35Ye65Y+R77yaDQrkuIrmtbfmsb3ovabljZfnq5nmr4/lpKk054+t5Yiw5qGQ5bqQ5a6i6L+Q5Lit5b+D5ZCO77yM6L2s6L2m5Yiw5YiG5rC05rGf5aSn5qGl5rG96L2m56uZ77yM6L2s5Lit5be06L2m5Y2z5Y+v44CCIA0K5p2t5bee5Ye65Y+RDQrmna3lt57msb3ovabljZfnq5nigJTigJTmoZDlupDlrqLov5DkuK3lv4PlkI7vvIzovazovabliLDliIbmsLTmsZ/lpKfmoaXmsb3ovabnq5nvvIzovazkuK3lt7TovabljbPlj6/jgIJdXT48L3JlbWluZD48c2VydmljZUd1YXJhbnRlZT48IVtDREFUQVvlpoLmgqjlnKjpqbTlpojlpojnmoTorqLotK3ku7fmoLzpq5jkuo7mma/ngrnnmoTlvZPlpKnpl6jluILku7fvvIzmiJHku6zml6DmnaHku7bpgIDov5jlt67ku7fjgIIgDQrpqbTlpojlpojnvZHnq5nkuIrnmoTmma/ngrnku7fmoLzmraPluLjmg4XlhrXkuIvpg73kvY7kuo7mma/ngrnpl6jluILku7fvvIzkvJjmg6DmnIDkvY7ovr7liLAx5oqY44CCIA0K5Zyo54m55q6K5oOF5Ya15LiL77yM5pmv54K55Lya5YGa5Li05pe25L+D6ZSA77yM6Zeo5biC5Lu35qC85YGa5Ye65aSn5bmF6LCD5pW077yM5rKh5pyJ5Y+K5pe26YCa55+l5Yiw5oiR5Lus77yM5a+86Ie06am05aaI5aaI572R56uZ55qE5Lu35qC85Y+v6IO95Lya6auY5LqO5b2T5pe255qE6Zeo5biC5Lu344CC5Zyo6L+Z56eN5oOF5Ya15LiL77yM5oiR5Lus6YOR6YeN5om/6K+677yM5peg5p2h5Lu26YCA6L+Y5beu5Lu357uZ55So5oi344CC5oKo5Y+q6ZyA6Ie055S15oiR5Lus5a6i5pyNMTAxMCA2MDYw77yM5oiR5Lus5Zyo5ZKM5pmv54K55qC45a6e5Lu35qC85Y+Y5Yqo5oOF5Ya15peg6K+v5ZCO5bCG5peg5p2h5Lu26YCA6L+Y5oKo5aSa5LuY55qE5beu5Lu344CCXV0+PC9zZXJ2aWNlR3VhcmFudGVlPjx2aXNpdFBvaW50PjwhW0NEQVRBW11dPjwvdmlzaXRQb2ludD48dHJhZmZpY0luZm8+PCFbQ0RBVEFbXV0+PC90cmFmZmljSW5mbz48dmlzYT48IVtDREFUQVtdXT48L3Zpc2E+PHByb2R1Y3RDaGFyYWN0ZXJpc3RpYz48IVtDREFUQVs8c3Ryb25nPjxzcGFuIHN0eWxlPSJmb250LXNpemU6MTZweDsiPjxzcGFuIHN0eWxlPSJmb250LXNpemU6MThweDsiPjxzcGFuIHN0eWxlPSJjb2xvcjojZTU2NjAwOyI+6ZW/5rKz5rOb6IifPC9zcGFuPjwvc3Bhbj48L3NwYW4+PGJyIC8+Cjwvc3Ryb25nPjxwPjxzcGFuIHN0eWxlPSJmb250LXNpemU6MTZweDsiPuaZr+WMuuaguOW/g+WcsOauteaYr+S4gOadoeWyqea6tuWcsOS4i+aal+ays++8jOWFqOmVvzQuNeWFrOmHjO+8jOS6uuensOKAnOWNgemHjOmVv+ays+KAneOAguawtOWfn+S4jua0nueptOmdouenrzEyLjjkuIflubPmlrnnsbPjgILlhbbkuK0xMDAw57Gz5rKz6YGT5Y+v6YCa5ri46Ii544CC5LiA5piv6L275p2+5LyR6Zey55qEMTAwMOexs+a6tua0nuiNoeiIn+a4uOiniOOAguS4jeS7heWPr+S7peaEn+WPl+WIsOeUseS6juWcsOWjs+WPmOWKqOWPiueBq+WxseeIhuWPkeetieWOn+WboOiAjOW9ouaIkOeahOWFuOWei+eahOWWgOaWr+eJueWyqea6tuWcsOiyjO+8jOiAjOS4lOi/mOWPr+S7pemihueVpeeOsOS7o+enkeaKgOe7meaIkeS7rOW4puadpeeahOKAnOawtOS4iueUteair+KAneeahOmjjumqmuKAlOKAlOeUseWNh+iIueijhee9ruWwhui/nuS6uuW4puiIueS7juS4gOe6p+awtOmBk+WNh+iHszjnsbPpq5jlpITnmoTkuoznuqfmsLTpgZPjgII8L3NwYW4+PC9wPgo8cD48c3BhbiBzdHlsZT0iZm9udC1zaXplOjE2cHg7Ij48aW1nIHNyYz0iaHR0cDovL3BpYy5sdm1hbWEuY29tLzMyMHgyNDAvcGljcy9zdXBlci8yMDExLzEwLzBHVU1KLmpwZyIgYWx0PSIiIGJvcmRlcj0iMCIgLz48aW1nIHNyYz0iaHR0cDovL3BpYy5sdm1hbWEuY29tLzMyMHgyNDAvcGljcy9zdXBlci8yMDExLzEwL1gwSDNQLmpwZyIgYWx0PSIiIGJvcmRlcj0iMCIgLz48YnIgLz4KPC9zcGFuPjwvcD4KPHN0cm9uZz48c3BhbiBzdHlsZT0iZm9udC1zaXplOjE2cHg7Ij48c3BhbiBzdHlsZT0iY29sb3I6I2U1NjYwMDsiPuaal+ays+aOoumZqTwvc3Bhbj48L3NwYW4+PGJyIC8+Cjwvc3Ryb25nPjxwPjxzcGFuIHN0eWxlPSJmb250LXNpemU6MTZweDsiPuWbveWGhemmluaOqOeahOWPpOaal+ays+aOoumZqeOAgjE1MDDnsbPnmoTmtJ7nqbTmjqLpmanvvIzkuLrli4fmlaLogIXmj5DkvpvnibnoibLml4XmuLjmnI3liqHvvIzmgqjlj6/ku6XlnKjkuJPogYzmjqLpmanlr7zmuLjnmoTmjIflr7zkuIvvvIzlj6booYzmm7TmjaLooYzoo4XvvIzlkITluKbkuJPnlKjnhafmmI7orr7lpIfvvIzlnKjmgqDmgqDplb/msrPkuK3vvIzmtonmsrPlt53vvIzmlIDnn7PltJbvvIzpl6/ni63osLfvvIzov5vooYzotqPlkbPml6DnqbfnmoTplb/msrPmjqLpmanvvIzlr7vop4Xlj6TkurrnmoTotrPov7nvvIzmjqLntKLlpKfoh6rnhLbnmoTlpaXnp5jjgII8L3NwYW4+PC9wPgo8cD48aW1nIHNyYz0iaHR0cDovL3BpYy5sdm1hbWEuY29tLzMyMHgyNDAvcGljcy9zdXBlci8yMDExLzEwL0pRTFhVLmpwZyIgYWx0PSIiIGJvcmRlcj0iMCIgLz48aW1nIHNyYz0iaHR0cDovL3BpYy5sdm1hbWEuY29tLzMyMHgyNDAvcGljcy9zdXBlci8yMDExLzEwL05FSEpELmpwZyIgYWx0PSIiIGJvcmRlcj0iMCIgLz48L3A+CjxiciAvPl1dPjwvcHJvZHVjdENoYXJhY3RlcmlzdGljPjx0cmF2ZWxFeHBsYW5hdGlvbj48L3RyYXZlbEV4cGxhbmF0aW9uPjxwcm9kdWN0QnJhbmNoPjxicmFuY2g+PGJyYW5jaElkPjEwNjwvYnJhbmNoSWQ+PGJyYW5jaE5hbWU+5oiQ5Lq66Zeo56WoPC9icmFuY2hOYW1lPjxicmFuY2hPbkxpbmU+dHJ1ZTwvYnJhbmNoT25MaW5lPjxtaW5pbXVtPjE8L21pbmltdW0+PG1heGltdW0+MTAwPC9tYXhpbXVtPjxhZHVsdFF1YW50aXR5PjE8L2FkdWx0UXVhbnRpdHk+PGNoaWxkUXVhbnRpdHk+MDwvY2hpbGRRdWFudGl0eT48L2JyYW5jaD48L3Byb2R1Y3RCcmFuY2g+PGltYWdlcz48c21hbGw+aHR0cDovL3BpYy5sdm1hbWEuY29tLzIwMHgxMDAvcGljcy9zdXBlci8yMDExLzAyLzVXWFFNLmpwZzwvc21hbGw+PG1pZGRsZT48L21pZGRsZT48bGFyZ2U+aHR0cDovL3BpYy5sdm1hbWEuY29tLzU4MHgyOTAvcGljcy9zdXBlci8yMDExLzAyLzVXWFFNLmpwZzwvbGFyZ2U+PC9pbWFnZXM+PC9wcm9kdWN0PjwvcHJvZHVjdExpc3Q+";
		System.out.println(new String(DistributionUtil.decode(str)));
	}

	public static void main(String[] args) {
		try {
			System.out.println(MD5.encode("be921c54cbc34ed6ac1a5b0572fbf1073816025816334055554445544412013-07-25测试13121026269360429199204241517ID_CARD测试13121026269360429199204241517ID_CARD"));
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void test() throws Exception{/*
		String url="http://localhost/clutter/jingdong/testPush.do";
		String xml = this.getXmlStr("orderApproveCallBack.xml");
		HttpClient http=new HttpClient();
		http.sendStream(xml, url);
	*/}
}