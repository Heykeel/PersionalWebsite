<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<!-- 嵌入外部CSS -->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/bootstrap.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/metisMenu.min.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/sb-admin-2.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/morris.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/font-awesome/css/font-awesome.min.css" rel="stylesheet">
<!-- 自定义CSS -->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/mystyle.css" rel="stylesheet">
	
<!-- 嵌入外部JavaScript -->
<script src="${pageContext.request.contextPath}/static/js/jquery-3.2.1.min.js"></script>       <!-- jQuery -->
<script src="${pageContext.request.contextPath}/static/js/bootstrap.js"></script>              <!-- Bootstrap Core JavaScript -->
<script src="${pageContext.request.contextPath}/static/js/metisMenu.min.js"></script>          <!-- Metis Menu Plugin JavaScript -->
<script src="${pageContext.request.contextPath}/static/js/morris-data.js"></script>            <!-- Morris Charts JavaScript -->
<script src="${pageContext.request.contextPath}/static/js/morris.min.js"></script>
<script src="${pageContext.request.contextPath}/static/js/raphael.min.js"></script>
<script src="${pageContext.request.contextPath}/static/js/sb-admin-2.js"></script>             <!-- Custom Theme JavaScript -->
<script src="${pageContext.request.contextPath}/static/js/Chart.bundle.js"></script>           <!-- 图表 Chart.js -->
<script src="${pageContext.request.contextPath}/static/js/utils.js"></script>

<!-- 自定义JavaScript -->
<script src="${pageContext.request.contextPath}/static/js/myjavascript.js"></script>

<title>HomePage</title>
</head>
<body>
	<!-- 导航栏 -->
	<nav class="navbar navbar-inverse navbar-fixed-top">
		<div class="container col-sm-10 col-sm-offset-1" style="padding: 0px">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#navbar" aria-expanded="false"
					aria-controls="navbar">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="${pageContext.request.contextPath}/index.html">LIJING BLOG</a>
			</div>
			<div id="navbar" class="navbar-collapse collapse">
				<ul class="nav navbar-nav">
					<li><a href="${pageContext.request.contextPath}/index.html">博客首页</a></li>
					<li><a href="${pageContext.request.contextPath}/aboutme.html">关于博主</a></li>
					<li><a href="#" data-toggle="modal" data-target="#loginModal">后台管理</a></li>
				</ul>
			</div>
			<!--/.nav-collapse -->
		</div>
	</nav>
	
	<!-- 嵌入登陆表单 -->
	<jsp:include page="loginForm.jsp"/>
	
	<!-- 网页主体内容 -->
	<div class="row">
		<div class="col-sm-2 col-sm-offset-1">
			<jsp:include page="left.jsp" />
		</div>
		<div class="col-sm-8">
			<jsp:include page="${commonPage}" />
		</div>
	</div>
	
	<!-- 版权和备案说明 -->
	<div class="row">
		<div class="copyright" align="center" style="padding-top: 50px;">
			<p style="margin-bottom: 0px;">Copyright © 2017 Heykeel 版权所有</p>
			<p>
				<a href="http://www.miitbeian.gov.cn/">渝ICP备17007596号</a>
			</p>
		</div>
	</div>
</body>
</html>