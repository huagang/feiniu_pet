<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>客服审核</title>
<link href="http://pic.lvmama.com/min/index.php?f=/styles/v5/modules/tip.css" rel="stylesheet" type="text/css">
<link href="http://pic.lvmama.com/min/index.php?f=/styles/v5/modules/button.css" rel="stylesheet" type="text/css">
<link href="http://pic.lvmama.com/styles/fx/b2b_fx.css" rel="stylesheet" type="text/css"> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<style type="text/css">
	#tip   {position:absolute;color:#333;display:none;}
	#tip s   {position:absolute;top:40px;left:-20px;display:block;width:0px;height:0px;font-size:0px;line-height:0px;border-color:transparent #BBA transparent transparent;border-style:dashed solid dashed dashed;border-width:10px;}
	#tip s i   {position:absolute;top:-10px;left:-8px;display:block;width:0px;height:0px;font-size:0px;line-height:0px;border-color:transparent #fff transparent transparent;border-style:dashed solid dashed dashed;border-width:10px;}
	#tip .t_box   {position:relative;background-color:#CCC;filter:alpha(opacity=50);-moz-opacity:0.5;bottom:-3px;right:-3px;}
	#tip .t_box div  {position:relative;background-color:#FFF;border:1px solid #ACA899;background:#FFF;padding:1px;top:-3px;left:-3px;}
	
</style>
</head>
<body>

<form action="/meterial/upload.do" method="post" id="upd"  enctype="multipart/form-data">
				<div class="iframe_leftBox">
					<h3>
						我的资料<small>请谨慎修改资料信息，资料提供越多，审核通过率越大。</small>
					</h3>
					<c:if test='${mateList!=null && userDetail.materialStatus=="REJECT"}'>
						<div class="tiptext tip-warning" style="height:auto;">
						*请谨慎修改资料信息，资料提供越多，审核通过率越大。<br/>
						<span class="tip-icon tip-icon-warning" style="margin:0 5px 0 18px"></span>${userDetail.failReason }<br/>
							<c:forEach items="${mateList}" var="material">
								<c:if test='${material.materialStatus=="REJECT" }'>
									<span class="tip-icon tip-icon-warning" style="margin:0 5px 0 18px"></span>${material.failReason }<br/>
								</c:if>
							</c:forEach>
						</div>
					</c:if>
					<ul class="uploadBox">
						<li class="longLi">
							<h4>法定代表人身份证</h4>
							<div class="upLoadPic">
								<h5>身份证正面</h5>
								<div class="uploadPicBox">
									<c:if test="${not empty COMPANY_CARD_FRONT.materialUrll}">
									<img src="http://pic.lvmama.com/pics/${ COMPANY_CARD_FRONT.materialUrll}" width="100" height="70" isHave="true" style="cursor: pointer;"/>
									</c:if>
								</div>
								<div class="BtnGroup_see">
									<input type="button" id="COMPANY_CARD_FRONT" name="COMPANY_CARD_FRONT" value="上传" class="uploadBtn"/>
								</div>
							</div>
							<div class="upLoadPic">
								<h5>身份证反面</h5>
								<div class="uploadPicBox">
									<c:if test="${not empty COMPANY_CARD_CONTRARY.materialUrll}">
										<img src="http://pic.lvmama.com/pics/${ COMPANY_CARD_CONTRARY.materialUrll}" width="100" height="70" style="cursor: pointer;"/>
									</c:if>
								</div>
								<div class="BtnGroup_see">
									<input type="button" id="COMPANY_CARD_CONTRARY"	name="COMPANY_CARD_CONTRARY" value="上传" class="uploadBtn" />
								</div>
							</div>
						</li>
					</ul>
					<input type="hidden" name="userId" id="userId" value="${SESSION_TNT_USER.userId}"/>
				</div>
			</form>


<script type="text/javascript">
$(function(){ 
	$(document).ready(function(){
		init("COMPANY_CARD_FRONT");
		init("COMPANY_CARD_CONTRARY");
	});
	
	$('.uploadPicBox').find("img").mouseover(function(){
		 var $tip=$('<div id="tip"><div class="t_box"><div><s><i></i></s><img src="'+this.src+'" /></div></div></div>');
	      $('body').append($tip);
	      $('#tip').show('fast');
	   }).mouseout(function(){
	      $('#tip').remove();
	   }).mousemove(function(e){
	      $('#tip').css({"top":(e.pageY-60)+"px","left":(e.pageX+30)+"px"})
	   });
});

function init(buttonId){
	ajaxUpload=new AjaxUpload($("#"+buttonId),{
		action:'/meterial/file/upload.do',
		autoSubmit:true,
		name:'file',
		hoverClass:"",
		focusClass:"",
		parentDiv:"main_r",
		responseType: "json",
		onSubmit:function(file,ext){
			if (ext && /^(jpg|png|jpeg|gif)$/.test(ext)){
				var data={"type":buttonId,"userId":$("#userId").val()};
				this.setData(data);
				this.disable();
				return true;
			}else{
				alert("文件格式错误");
				return false;
			}
		},
		onComplete:function(file,data){
			if(data.success){
				$("#"+buttonId).attr("value","重 传");
				var img = $("#"+buttonId).parents().siblings('.uploadPicBox').find("img");
				img.attr("src","http://pic.lvmama.com/pics/"+data.fileName);
			}else{
				alert(data.errorText);
			}
			this.enable();
		}
	});
}
</script>
</body>
</html>