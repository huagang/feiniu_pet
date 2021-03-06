<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>发票列表</title>
		<script type="text/javascript" src="<%=request.getContextPath()%>/js/base/jquery-1.4.4.min.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/js/ui/jquery-ui-1.8.5.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/js/base/remoteUrlLoad.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/js/base/jquery.datepick-zh-CN.js"></script>		
		<link rel="stylesheet" href="<%=request.getContextPath()%>/themes/base/jquery.ui.all.css" />
		<script type="text/javascript" src="<%=request.getContextPath()%>/js/base/jquery.jsonSuggest-2.min.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/js/base/lvmama_dialog.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/js/ord/ord_invoice.js"></script>
		<script type="text/javascript"> 
			var current_flag="red_manager";//js当中使用 
		</script>
		<link rel="stylesheet" href="<%=request.getContextPath()%>/themes/cc.css" />
		<style type="text/css">
			input.date{width:80px;}
			.jsonSuggest li a img {
				float:left;
				margin-right:5px;
			}
			.jsonSuggest li a small {
				display:block;
				text-align:right;
			}
			.jsonSuggest { font-size:0.8em; }
			.checks{margin-right:5px;}

			.item_box{width:520px; text-align:left; overflow:hidden; zoom:1;}
			.item_box span{float:left; vertical-align:middle;}
			.repeat_item{display:inline-block; text-align:center;}
			.repeat_item_hasSub{display:inline-block; width:220px; text-align:center; overflow:hidden; zoom:1;}
			.col_repeat  i{float:left; font-style:normal;}
			.width100{ width:70px; overflow:hidden;}
			.width120{ width:120px;overflow:hidden;}
			.border_l{border-left:1px solid #333;}
		</style>
	</head>
	<body>
		<div  class="table_box" style="width:99%">
			<div class="mrtit3" style="padding:10px;">
				<form action="<%=request.getContextPath()%>/ord/redInvoiceList.do" method="post" id="searchForm">
				<table width="100%">
					<tr>
						<td width="160">订单号：</td><td><input type="text" name="orderId" value="${orderId}"/></td>
						<td width="100">发票单号:</td><td><input type="text" name="invoiceNo" value="${invoiceNo}"/></td>						
						<td width="80">发票ID:</td><td><input type="text" name="invoiceId" value="${invoiceId}"/></td>
					</tr>					
					<tr>
						<td colspan="5">&nbsp;</td><td><input type="submit" value="查询 " class="right-button08"/></td>
						
					</tr>
				</table>
				</form>
			</div>
			<table cellspacing="1" cellpadding="4" border="0"
							bgcolor="#666666" width="100%" class="newfont06"
							style="font-size: 12; text-align: center;">
			<tr bgcolor="#eeeeee">
				<td width="5%">序号</td>		
				<td width="5%">发票单号</td>
				<td width="5%">状态</td>
				<td width="5%">配送方式</td>
				<td width="9%">快递单号</td>
				<td width="5%">是否有地址</td>	
				<td width="10%">备注</td>
				<td colspan="6" class="col_repeat">
				   <div class="item_box">
					<span class="repeat_item width100">订单号</span>
					<span class="repeat_item width100 border_l">可开票金额</span>
					<span class="repeat_item_hasSub">
						<i class="repeat_item width100 border_l">保险金额</i>
						<i class="repeat_item width120 border_l">保险产品名称</i>
					</span>				
					<span class="repeat_item width100 border_l">申请时间</span>
					<span class="repeat_item width100 border_l">游玩时间</span>
				   </div>	
				</td>				
				<td width="8%">发票金额</td>						
				<td width="8%">操作</td>
			</tr>
			
			<s:iterator value="pagination.records" var="invoice" status="st">
			  <tr bgcolor="#ffffff" id="tr_${invoice.invoiceId}">
				<td style="text-align: left"><input type="checkbox" class="checks" name="invoiceId" value="${invoice.invoiceId}"/>${invoice.invoiceId}</td>
				
				<td>${invoice.invoiceNo}</td>
				<td status="${invoice.status}" id="status_${invoice.invoiceId}">${invoice.zhStatus}</td>
				<td>${invoice.zhDeliveryType}</td>
				<td>${invoice.expressNo}</td>
				<td><s:if test="#invoice.deliveryType!='SELF'"><s:if test="#invoice.hasNotNullDelivery()">是</s:if></s:if></td>
				<td>${invoice.memo}</td>
				<td colspan="6" class="col_repeat">
				<s:iterator value="#invoice.orderList" var="order">
				    <div class="item_box">
						<span class="repeat_item width100">${order.orderId}</span><!-- 1-->
						<span class="repeat_item width100 border_l">
						    <em style="color: #f00; font-weight: bold"> 
								<s:if test="order.paymentStatus=='UNPAY' && order.paymentTarget =='TOLVMAMA'">
									0
								</s:if> 
								<s:elseif test="order.paymentTarget =='TOSUPPLIER'">  
									0
								</s:elseif> 
								<s:else>
									<fmt:formatNumber value="${order.orderPayFloat}" pattern="#.##" />
								</s:else> 
						   </em>
						</span><!-- 2-->
						
						<span class="repeat_item_hasSub">
						   <s:iterator value="#order.ordOrderItemProds" id="orderItemProd">
		                   <s:if test="#orderItemProd.productType=='OTHER' && #orderItemProd.subProductType=='INSURANCE'">
		                       <i class="repeat_item width100 border_l">
		                           ${orderItemProd.amountYuan}
			                   </i>
				               <i class="repeat_item width120 border_l">
						          ${orderItemProd.productName}
						       </i> 
						   </s:if>
						   </s:iterator>&nbsp;
						</span><!-- 3.4 -->
						
					    <span class="repeat_item width100 border_l">
					     <s:date name="#invoice.createTime" format="yyyy-MM-dd"/>
					   </span><!--5 -->
					   <span class="repeat_item width100 border_l"><s:date name="#order.visitTime" format="yyyy-MM-dd"/></span><!-- 6 -->
					</div>
					</s:iterator>
			   </td><!-- 6条 -->		
				<td>${invoice.amountYuan}</td>											
				<td>
					<a href="javascript:showInvoiceDetailDiv(${invoice.invoiceId})">查看</a>		
					<s:if test="#invoice.status=='APPROVE'">
						<a href="javascript:void(0);" result="${invoice.invoiceId}" t="list" class="cancel">取消</a>
						<a href="javascript:void(0);" result="${invoice.invoiceId}" class="closeRed" >关闭</a>
					</s:if>			
					<s:if test="#invoice.status=='BILLED'">
					    <a href="javascript:void(0);" result="${invoice.invoiceId}" t="list" class="confirmRed">红冲</a>
					    <a href="javascript:void(0);" result="${invoice.invoiceId}" class="closeRed" >关闭</a>
					</s:if>								
				</td>
			</tr>
			</s:iterator>
			</table>
			
			<table width="90%" border="0" align="center">
				<s:include value="/WEB-INF/pages/back/base/pag.jsp" />
			</table>
		</div>		
		<div id="bg" class="bg" style="display: none;"></div>
		<div class="orderpop" id="invoiceDetailDiv" href="<%=request.getContextPath()%>/ord/invoiceDetail.do" style="display:none"></div>
	   <script type="text/javascript">
	    
	      function getHeight(){
	         $(".col_repeat span").each(function(){
	            $(this).css("height",$(this).parents(".item_box").height());
	         })
	         $(".repeat_item_hasSub i").each(function(){
	            $(this).css("height",$(this).parents(".item_box").height());
	         })
	       } 
	       getHeight();
	       $(window).resize(function(){
	    	   getHeight();
	       })
	    </script>
	
	</body>
</html>