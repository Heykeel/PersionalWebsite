<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!-- 博客管理列表 -->
<div class="row">
	<div class="col-sm-12">
		<h1 class="page-header">博客列表</h1>
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
							<th>摘要</th>
							<th>发布时间</th>
							<th>类型</th>
							<th>操作</th>
						</tr>
						<c:forEach items="${blogList}" var="blog" varStatus="status">
							<tr class="odd gradeX">
								<td>${status.index + 1}</td>
								<td><a href="${pageContext.request.contextPath}/blog/articles/${blog.id}.html">${blog.title}</a></td>
								<td>${blog.summary}</td>
								<td><fmt:formatDate value="${blog.releaseDate }"
										type="date" pattern="yyyy-MM-dd HH:mm" /></td>
								<td>${blog.blogType.typeName}</td>
								<td><a
									href="${pageContext.request.contextPath}/admin/blog/modifyBlog.do?id=${blog.id}">修改</a>&nbsp;/&nbsp;<a
									href="javascript:void(0);" onclick="deleteBlog(${blog.id})">删除</a></td>
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

<script>
	/* 删除博客 */
	function deleteBlog(blogId){
		$.post("${pageContext.request.contextPath}/admin/blog/deleteBlog.do", {
			"id" : blogId,
		}, function(result) {
			if (result.success) {
				alert("删除成功！");
				window.location.reload();
			}
		}, "json")
	}
</script>