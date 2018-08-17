<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>登陆</title>
<meta name="viewport" content="initial-scale=1, maximum-scale=1">
<link rel="shortcut icon" href="/favicon.ico">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">

<link rel="stylesheet"
	href="//g.alicdn.com/msui/sm/0.6.2/css/sm.min.css">
<link rel="stylesheet"
	href="//g.alicdn.com/msui/sm/0.6.2/css/sm-extend.min.css">
<link rel="stylesheet" href="http://localhost/zpwx/css/weui.css" />
<link rel="stylesheet" href="http://localhost/zpwx/css/example.css" />
</head>
<body>
	<div class="content">
		<div class="content-padded grid-demo">
			<div class="row" style="height: 2rem; background-color: green;">
				<div class="col-100">100%</div>
			</div>
			<div class="row" style="height: 60rem; background-color: red;">
				<div class="col-100">100%</div>
			</div>
			<div class="row" style="height: 48rem; background-color: red;">
				<div class="col-100">
					<nav class="bar bar-tab"> <a id="rrrr" class="tab-item external active"
						href="#"> <span class="icon icon-home"></span> <span
						class="tab-label">常用</span>
					</a> <a class="tab-item external" href="#"> <span
						class="icon icon-me"></span> <span class="tab-label">项目情况</span> <span
						class="badge">2</span>
					</a> <a class="tab-item external" href="#"> <span
						class="icon icon-star"></span> <span class="tab-label">蜗牛在行动</span>
					</a> <a class="tab-item external" href="#"> <span
						class="icon icon-cart"></span> <span class="tab-label">蜗牛历史</span>
					</a> <a class="tab-item external"
						href="${pageContext.request.contextPath}/myinfo.jsp"> <span
						class="icon icon-settings"></span> <span class="tab-label">我</span>
					</a> </nav>
				</div>
			</div>
		</div>
	</div>
	<script type='text/javascript'
		src='//g.alicdn.com/sj/lib/zepto/zepto.min.js' charset='utf-8'></script>
	<script type='text/javascript'
		src='//g.alicdn.com/msui/sm/0.6.2/js/sm.min.js' charset='utf-8'></script>
	<script type='text/javascript'
		src='//g.alicdn.com/msui/sm/0.6.2/js/sm-extend.min.js' charset='utf-8'></script>

</body>
<script type="text/javascript">
	$(document).ready(
			function() {
				$('.tab-item external').click(
						function() {
							alert(111111111111);
							
						});
			});
// 	$(document).ready(function() {
// 		$('.tab-item external').click(function() {
// 			alert(11111);
// 			htmlobj = $.ajax({
// 				url : "/jquery/test1.txt",
// 				async : false
// 			});
// 			$("#myDiv").html(htmlobj.responseText);
// 		});
// 	});
</script>
</html>