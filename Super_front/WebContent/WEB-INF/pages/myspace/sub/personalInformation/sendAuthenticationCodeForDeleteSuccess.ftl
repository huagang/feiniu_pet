<!DOCTYPE html>
<#include "/WEB-INF/pages/myspace/base/doctype.ftl"/>
<head>
	<meta charset="utf-8">
	<title>解绑邮箱-驴妈妈旅游网</title>
	<#include "/WEB-INF/pages/myspace/base/lv-meta.ftl"/>
	<#include "/common/coremetricsHead.ftl">
</head>
<body id="page-userinfo">
		<#include "/WEB-INF/pages/myspace/base/header.ftl"/>
		<div class="lv-nav wrap">
			<p>
				<a href="http://www.lvmama.com/myspace/index.do">我的驴妈妈</a>
				&gt;
				<a href="http://www.lvmama.com/myspace/userinfo.do">我的信息</a>
				&gt;
				<a class="current">解绑邮箱</a>
			</p>
		</div>
		<div class="wrap ui-content lv-bd">
			<#include "/WEB-INF/pages/myspace/base/lv-nav.ftl"/>
			<div class="lv-content">
				<div class="ui-box mod-edit email-edit">
					<div class="ui-box-title"><h3>解绑邮箱</h3></div>
					<div class="ui-box-container">
    					<div class="edit-box clearfix email-edit-box">
        					<p class="tips-info"><span class="tips-ico03"></span>解绑邮箱后，邮箱地址将不能用来登录。</p>
        					
        					<div class="set-step set-step2 clearfix">
        						<ul class="hor">
							    <li class="s-step1"><span class="s-num">1</span>验证邮箱</li>
							    <li class="s-step2"><i class="s-arrow"></i><span class="s-num">2</span>邮箱解绑成功</li>
								</ul>
							</div>
        					
							<div class="msg-success">
								<h3>您好，我们已发送验证邮件至您的邮箱：<a href="<@s.property value="mailHost" />"><@s.property value="@com.lvmama.comm.utils.StringUtil@hiddenEmail(email)" /></a></h3>
                                <input type="hidden" id="email" name="email" value='<@s.property value="email" />'/>
								<p class="mt10"><a target="_blank" href="<@s.property value="mailHost" />" class="ui-btn ui-btn4"><i>&nbsp;登录邮箱&nbsp;</i></a>　没收到邮件? 
								<a href="javascript:;" onclick="resend()">再次发送</a></p>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	<#include "/WEB-INF/pages/myspace/base/lv-footer.ftl"/>
<script type="text/javascript">
	 
	 function resend() {
	   	$.getJSON("http://login.lvmama.com/nsso/ajax/sendAuthenticationCode.do?mobileOrEMail="+$("#email").val()+"&userId="+${user.id}+"&validateType=EMAIL_DELETE_AUTHENTICATE&jsoncallback=?",
		function(json){
			if (json.success) {
                               alert('重新发送成功');
			} else {
				alert('验证码发送失败，请重新尝试');
			}
		});	
	}
</script>
	<script>
		cmCreatePageviewTag("解绑邮箱", "D1003", null, null);
	</script>
</body>
</html>