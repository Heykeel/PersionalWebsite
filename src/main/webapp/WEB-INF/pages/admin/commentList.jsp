<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- 评论管理列表 -->
<div class="row">
	<div class="col-sm-12">
		<h1 class="page-header">评论列表</h1>
	</div>
</div>
<div class="row">
	<div class="col-sm-12">
		<div class="panel panel-default">
			<div class="panel-heading">列表明细</div>
			<div class="panel-body">
				<table class="table table-striped table-bordered table-hover"
					id="dataTables-example">
					<thead>
						<tr>
							<th>序号</th>
							<th>题目</th>
							<th>评论</th>
						</tr>
						<c:forEach items="${blogList}" var="blog" varStatus="status">
							<tr class="odd gradeX">
								<td>${status.index + 1}</td>
								<td><a href="${pageContext.request.contextPath}/blog/articles/${blog.id}.html">${blog.title}</a></td>
								<td><a href="${pageContext.request.contextPath}/admin/comment/comments/${blog.id}.html">查看</a>&nbsp;(${blog.replyHit})</td>
							</tr>
						</c:forEach>
					</thead>
				</table>
				<div style="text-align: center;">
					<div class="pagination">${pageCode}</div>
				</div>
			</div>
		</div>
	</div>
</div>
