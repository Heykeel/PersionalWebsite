<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!-- 博客列表 -->
<div class="page-header"></div>
<c:forEach items="${blogList}" var="blog">
	<div class="well" style="padding: 0px 19px;">
		<h3><a href="${pageContext.request.contextPath}/blog/articles/${blog.id}.html">${blog.title }</a></h3>
		<p>${blog.summary}&nbsp;&nbsp;...</p>
		<div id="tag">
    		<div class="container">
				<div class="row">
					<div class="col-sm-8" style="padding: 0px;">
						<i class="fa fa-tags"></i>
						<span>${blog.keyWord}</span>
					</div>
					<div class="col-sm-4" style="padding: 0px;text-align: right; margin-bottom: 10px">
						<font color="#999"><fmt:formatDate value="${blog.releaseDate}" type="date" pattern="yyyy-MM-dd HH:mm"/></font> &nbsp;&nbsp;
						<font color="#336699">阅读<font color="#999">(${blog.clickHit})</font>&nbsp;&nbsp;</font>
						<font color="#336699">评论<font color="#999">(${blog.replyHit})</font>&nbsp;&nbsp;</font>
					</div>
				</div>
			</div>
		</div>
	</div>
</c:forEach>

<!-- 翻页标签 -->
<div style="text-align: center;">
		<div class="pagination">${pageCode}</div>
</div>