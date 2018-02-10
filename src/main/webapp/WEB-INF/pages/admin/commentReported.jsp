<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!-- 评论审核列表 -->
<div class="row">
	<div class="col-sm-12">
		<h1 class="page-header">评论审核</h1>
	</div>
</div>
<div class="row">
	<div class="col-sm-12">
		<div class="panel panel-default">
			<div class="panel-heading">被举报评论明细</div>
			<div class="panel-body">
				<table class="table table-striped table-bordered table-hover"
					id="dataTables-example">
					<thead>
						<tr>
							<th>序号</th>
							<th>IP</th>
							<th>内容</th>
							<th>发布时间</th>
							<th>操作</th>
						</tr>
						<c:forEach items="${commentList}" var="comment" varStatus="status">
							<tr class="odd gradeX">
								<td>${status.index + 1}</td>
								<td>${comment.visitorIp}</td>
								<td>${comment.content}</td>
								<td><fmt:formatDate value="${comment.commentDate}" type="date" pattern="yyyy-MM-dd HH:mm" /></td>
								<td><a href="javascript:void(0);" onclick="deleteComment(${comment.id})">删除</a>&nbsp;/&nbsp;<a href="javascript:void(0);" onclick="ignoreReport(${comment.id})">忽略</a></td>
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
	/* 删除评论 */
	function deleteComment(id){
		$.post("${pageContext.request.contextPath}/admin/comment/deletecomment.do", {
			"id" : id,
		}, function(result) {
			if (result.success) {
				alert("删除成功！");
				window.location.reload();
			}
		}, "json")
	}
	
	/* 忽略评论举报 */
	function ignoreReport(id) {
		$.post("${pageContext.request.contextPath}/admin/comment/reproted.do", {
			"id": id,
			"flag":0
		},function(result) {
			if (result.success) {
				alert("已经忽略举报！");
				window.location.reload();
			} 
		},"json");
	}
</script>
