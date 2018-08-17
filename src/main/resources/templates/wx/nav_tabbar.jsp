<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-cmn-Hans">
<head>
<title>柯锐特</title>
<meta name="viewport" content="initial-scale=1, maximum-scale=1">
<meta http-equiv="Cache-Control"
	content="no-cache, no-store, must-revalidate" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Expires" content="0" />
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/weui.css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/example.css" />
</head>
<body>
	<div class="weui-flex" style="height: 32rem;">
		<div class="weui-flex__item">
			<div class="placeholder" style=""
				id="main_page"></div>
		</div>
	</div>
	<div class="weui-flex" style="height: 2rem;">
		<div class="weui-flex__item">
			<div class="placeholder">
				<div class="page__bd" style="height: 90%;">
					<div class="weui-tab">
						<div class="weui-tab__panel"></div>
						<div class="weui-tabbar">
							<a href="##" id="default"
								url="<%=request.getContextPath()%>/wx/wx_main.jsp"
								class="weui-tabbar__item weui-bar__item_on"> <span
								style="display: inline-block; position: relative;"> <img
									src="<%=request.getContextPath()%>/images/icon_tabbar.png"
									alt="" class="weui-tabbar__icon"> <span
									class="weui-badge"
									style="position: absolute; top: -2px; right: -13px;">8</span>
							</span>
								<p class="weui-tabbar__label">微信</p>
							</a> <a url="<%=request.getContextPath()%>/wx/adressList.jsp"
								href="##" class="weui-tabbar__item"> <img
								src="<%=request.getContextPath()%>/images/icon_tabbar.png"
								alt="" class="weui-tabbar__icon">
								<p class="weui-tabbar__label">通讯录</p>
							</a> <a href="##" url="<%=request.getContextPath()%>/wx/found.jsp" class="weui-tabbar__item"> <span
								style="display: inline-block; position: relative;"> <img
									src="<%=request.getContextPath()%>/images/icon_tabbar.png"
									alt="" class="weui-tabbar__icon"> <span
									class="weui-badge weui-badge_dot"
									style="position: absolute; top: 0; right: -6px;"></span>
							</span>
								<p class="weui-tabbar__label">发现</p>
							</a> <a href="##" url="<%=request.getContextPath()%>/myinfo.jsp" class="weui-tabbar__item"> <img
								src="<%=request.getContextPath()%>/images/icon_tabbar.png"
								alt="" class="weui-tabbar__icon">
								<p class="weui-tabbar__label">我</p>
							</a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

</body>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jweixin-1.0.0.js"></script>
<script src="<%=request.getContextPath()%>/js/zepto.min.js"></script>
<script src="<%=request.getContextPath()%>/js/example.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		var url_= $("#default").attr("url");
		htmlobj = $.ajax({
			url : url_,
			async : false
		});
		$("#main_page").html(htmlobj.responseText);
	})
	$(function() {
		$('.weui-tabbar__item').on(
				'click',
				function() {
					var url_=$(this).attr("url");
					$(this).addClass('weui-bar__item_on').siblings(
							'.weui-bar__item_on').removeClass(
							'weui-bar__item_on');
					htmlobj = $.ajax({
						url : url_,
						async : false
					});
					$("#main_page").html(htmlobj.responseText);
				});
	});
</script>
</html>
