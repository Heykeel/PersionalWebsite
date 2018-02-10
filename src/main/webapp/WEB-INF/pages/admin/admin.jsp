<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<!-- 嵌入外部CSS -->
<link rel="stylesheet" 	href="${pageContext.request.contextPath}/static/css/bootstrap.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/metisMenu.min.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/sb-admin-2.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/morris.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/font-awesome/css/font-awesome.min.css" rel="stylesheet">

<!-- 嵌入外部JavaScript -->
<script src="${pageContext.request.contextPath}/static/js/jquery-3.2.1.min.js"></script>   <!-- jQuery -->
<script src="${pageContext.request.contextPath}/static/js/bootstrap.js"></script>          <!-- Bootstrap Core JavaScript -->
<script src="${pageContext.request.contextPath}/static/js/metisMenu.min.js"></script>      <!-- Metis Menu Plugin JavaScript -->
<script src="${pageContext.request.contextPath}/static/js/morris-data.js"></script>        <!-- Morris Charts JavaScript -->
<script src="${pageContext.request.contextPath}/static/js/morris.min.js"></script>
<script src="${pageContext.request.contextPath}/static/js/raphael.min.js"></script>
<script src="${pageContext.request.contextPath}/static/js/sb-admin-2.js"></script>         <!-- Custom Theme JavaScript -->

<!-- ueditor -->
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/static/ueditor1.4.3.3/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/static/ueditor1.4.3.3/ueditor.all.min.js"></script>
<!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
<!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/static/ueditor1.4.3.3/lang/zh-cn/zh-cn.js"></script>
	

<title>后台管理</title>
</head>
<body>
	<!-- 导航栏 -->
	<div id="wrapper">
		<!-- Navigation -->
		<nav class="navbar navbar-default navbar-static-top" role="navigation"
			style="margin-bottom: 0">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target=".navbar-collapse">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="index.html">PW Admin v1.1.5</a>
			</div>
			<!-- /.navbar-header -->

			<div class="navbar-default sidebar" role="navigation">
				<div class="sidebar-nav navbar-collapse">
					<ul class="nav" id="side-menu">
						<li class="sidebar-search">
							<div class="input-group custom-search-form">
								<input type="text" class="form-control" placeholder="Search...">
								<span class="input-group-btn">
									<button class="btn btn-default" type="button">
										<i class="fa fa-search"></i>
									</button>
								</span>
							</div> <!-- /input-group -->
						</li>
						<!-- 第一行 -->
						<li><a href="javascript:void(0);" onclick="window.location.reload();"><i
								class="fa fa-dashboard fa-fw"></i> 刷新页面</a></li>
						<li>
						<!-- 第二行 -->
						<li><a href=""><i class="fa fa-list-ul fa-fw"></i>
								博客管理<span class="fa arrow"></span></a>
							<ul class="nav nav-second-level">
								<li><a href="${pageContext.request.contextPath}/admin/blog/index.html">博客发布</a></li>
								<li><a href="${pageContext.request.contextPath}/admin/blog/bloglist.html">博客列表</a></li>
								<li><a href="${pageContext.request.contextPath}/admin/blogtype/blogtypelist.html">博客类别</a></li>
							</ul> <!-- /.nav-second-level -->
						</li>
						<!-- 第三行 -->
						<li><a href="#"><i class="fa fa-comments fa-fw"></i>评论管理<span class="fa arrow"></span></a>
							<ul class="nav nav-second-level">
								<li><a href="${pageContext.request.contextPath}/admin/comment/commentlist.html">评论列表</a></li>
								<li><a href="${pageContext.request.contextPath}/admin/comment/checkcomment.html">评论审核</a></li>
							</ul> <!-- /.nav-second-level -->
						</li>
						<!-- 第四行 -->
						<li><a href="#"><i class="fa fa-cog fa-fw"></i>系统设置<span class="fa arrow"></span></a>
							<ul class="nav nav-second-level">
								<li><a href="#">修改密码</a></li>
								<li><a href="${pageContext.request.contextPath}/loginout.html">注销登录</a></li>
							</ul> <!-- /.nav-second-level -->
						</li>
					</ul>
				</div>
			</div>
		</nav>
		<!-- 网页主体内容 -->
		<div id="page-wrapper">
			<jsp:include page="${commonAdminPage}" />
		</div>

	</div>
	
	<!-- 版权和备案说明 -->
	<div class="copyright" align="center" style="padding-top: 50px;">
		<p style="margin-bottom: 0px;">Copyright © 2017 Heykeel 版权所有</p>
		<p>
			<a href="http://www.miitbeian.gov.cn/">渝ICP备17007596号</a>
		</p>
	</div>
</body>

</html>