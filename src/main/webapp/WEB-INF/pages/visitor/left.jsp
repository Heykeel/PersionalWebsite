<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!-- 博主头像 -->
<div class="page-header"></div>
<div class="panel panel-default">
	<div class="panel-heading">
		<h3 class="panel-title">
			<i class="fa fa-user"></i>&nbsp;&nbsp;博主头像
		</h3>
	</div>
	<div class="panel-body">
		<div id="ud-image">
			<img src="${pageContext.request.contextPath}/static/image/myhead.png">
		</div>
	</div>
</div>

<!-- 博客分类 -->
<div class="panel panel-default">
	<div class="panel-heading">
		<h3 class="panel-title">
			<i class="fa fa-list-ul"></i>&nbsp;&nbsp;博客分类
		</h3>
	</div>
	<div class="panel-body" style="padding-bottom: 0px;">
		<ul class="list-group">
		<c:forEach items="${blogTypeList}" var="blogType">
			<li class="list-group-item"><a href="${pageContext.request.contextPath}/blog/bloglist/${blogType.id}.html">${blogType.typeName}</a>&nbsp;&nbsp;(${blogType.blogCount})</li>
		</c:forEach>
		</ul>
	</div>
</div>

<!-- 文章存档 -->
<div class="panel panel-default">
	<div class="panel-heading">
		<h3 class="panel-title">
			<i class="fa fa-calendar"></i>&nbsp;&nbsp;文章存档
		</h3>
	</div>
	<div class="panel-body" style="padding-bottom: 0px;">
		<ul class="list-group">
			<c:forEach items="${dateList}" var="dateTime">
				<li class="list-group-item"><a href="${pageContext.request.contextPath}/blog/datelist/${dateTime.releaseDateStr}.html">${dateTime.releaseDateStrCN}</a>&nbsp;&nbsp;(${dateTime.dateCount})</li>
			</c:forEach>
		</ul>
	</div>
</div>

<!-- 友情链接 -->
<div class="panel panel-default">
	<div class="panel-heading">
		<h3 class="panel-title">
			<i class="fa fa-link"></i>&nbsp;&nbsp;友情链接
		</h3>
	</div>
	<div class="panel-body" style="padding-bottom: 0px;">
		<ul class="list-group">
			<li><a href="http://www.cqupt.edu.cn" class="alert-link">重庆邮电大学</a></li>
			<li><a href="http://www.gucas.ac.cn/" class="alert-link">中国科学院大学</a></li>
		</ul>
	</div>
</div>