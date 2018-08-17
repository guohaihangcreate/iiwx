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
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/weui.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/example.css" />
</head>
<body>
    <div class="page__hd">
        <h1 class="page__title">Grid</h1>
        <p class="page__desc">九宫格</p>
    </div>
    <div class="weui-grids">
        <a href="javascript:;" class="weui-grid">
            <div class="weui-grid__icon">
                <img src="<%=request.getContextPath()%>/images/icon_tabbar.png" alt="">
            </div>
            <p class="weui-grid__label">薪酬发放查询</p>
        </a>
        <a href="javascript:;" class="weui-grid">
            <div class="weui-grid__icon">
                <img src="<%=request.getContextPath()%>/images/icon_tabbar.png" alt="">
            </div>
            <p class="weui-grid__label">考勤查询</p>
        </a>
        <a href="javascript:;" class="weui-grid">
            <div class="weui-grid__icon">
                <img src="<%=request.getContextPath()%>/images/icon_tabbar.png" alt="">
            </div>
            <p class="weui-grid__label">最新IT咨询</p>
        </a>
        <a href="javascript:;" class="weui-grid">
            <div class="weui-grid__icon">
                <img src="<%=request.getContextPath()%>/images/icon_tabbar.png" alt="">
            </div>
            <p class="weui-grid__label">趣味拓展活动</p>
        </a>
        <a href="javascript:;" class="weui-grid">
            <div class="weui-grid__icon">
                <img src="<%=request.getContextPath()%>/images/icon_tabbar.png" alt="">
            </div>
            <p class="weui-grid__label">视频面试</p>
        </a>
        <a href="javascript:;" class="weui-grid">
            <div class="weui-grid__icon">
                <img src="<%=request.getContextPath()%>/images/icon_tabbar.png" alt="">
            </div>
            <p class="weui-grid__label">绩效考核</p>
        </a>
        <a href="javascript:;" class="weui-grid">
            <div class="weui-grid__icon">
                <img src="<%=request.getContextPath()%>/images/icon_tabbar.png" alt="">
            </div>
            <p class="weui-grid__label">各项目动态</p>
        </a>
        <a href="javascript:;" class="weui-grid">
            <div class="weui-grid__icon">
                <img src="<%=request.getContextPath()%>/images/icon_tabbar.png" alt="">
            </div>
            <p class="weui-grid__label">优秀个人表彰</p>
        </a>
        <a href="javascript:;" class="weui-grid">
            <div class="weui-grid__icon">
                <img src="<%=request.getContextPath()%>/images/icon_tabbar.png" alt="">
            </div>
            <p class="weui-grid__label">通知公告</p>
        </a>
    </div>
</div>
</body>
<script type="text/javascript">
	$(function() {

	});

	function changeImg() {
		var imgSrc = $("#imgObj");
		var src = imgSrc.attr("src");
		imgSrc.attr("src", chgUrl(src));
	}

	// 时间戳
	// 为了使每次生成图片不一致，即不让浏览器读缓存，所以需要加上时间戳
	function chgUrl(url) {
		var timestamp = (new Date()).valueOf();
		url = url.substring(0, 20);
		if ((url.indexOf("&") >= 0)) {
			url = url + "×tamp=" + timestamp;
		} else {
			url = url + "?timestamp=" + timestamp;
		}
		return url;
	}
</script>
</html>