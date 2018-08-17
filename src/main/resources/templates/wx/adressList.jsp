<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>通讯录</title>
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
    <div class="weui-cells__title">通讯录</div>
	<div class="weui-cells">
            <a class="weui-cell weui-cell_access" href="javascript:;">
                <div class="weui-cell__hd"><img src="http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTIdKsz6IqgWrCuxILV8tRFwbYx8QOvXCZib7Jfk6u7xTOgpTCtJ9BT1Fvq1ic9EicKw9p0Tcn8a3HWSw/132" alt="" style="width:20px;margin-right:5px;display:block"></div>
                <div class="weui-cell__bd">
                    <p>郭海航</p>
                </div>
                <div class="weui-cell__ft">java软件工程师</div>
            </a>
            <a class="weui-cell weui-cell_access" href="javascript:;">
                <div class="weui-cell__hd"><img src="http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTLJxibPuRib5xAKE6j48sGr9IiaUkCicsbAf5Lx8IhzxjjOnzkpxTHkG9KMsTgUurepm4aC28bQ7VJ2SA/132" alt="" style="width:20px;margin-right:5px;display:block"></div>
                <div class="weui-cell__bd">
                    <p>张瑜</p>
                </div>
                <div class="weui-cell__ft">大客户经理</div>
            </a>
            <a class="weui-cell weui-cell_access" href="javascript:;">
                <div class="weui-cell__hd"><img src="http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTKaxUhVibvCsn6RMXyAUhQhCFBMpKWjALYjicwdWRJlqEtkeQbMWpceY0TK47C0lOxVmE3w9WyyqAYQ/132" alt="" style="width:20px;margin-right:5px;display:block"></div>
                <div class="weui-cell__bd">
                    <p>彭飞</p>
                </div>
                <div class="weui-cell__ft">运营总监</div>
            </a>
        </div>
</body>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jweixin-1.0.0.js"></script>
<script src="<%=request.getContextPath()%>/js/zepto.min.js"></script>
<script src="<%=request.getContextPath()%>/js/example.js"></script>
<script type="text/javascript">
	
</script>
</html>