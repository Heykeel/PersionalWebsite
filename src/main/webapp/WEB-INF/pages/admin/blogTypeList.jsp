<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- 博客类型列表 -->
<div class="row">
	<div class="col-sm-12">
		<h1 class="page-header">类别列表</h1>
	</div>
</div>
<div class="row">
	<div class="col-sm-12">
		<div class="panel panel-default">
			<div class="panel-heading">列表明细</div>
			<div class="panel-body">
				<table class="table table-striped table-bordered table-hover" id="dataTables-example" style="margin-bottom: 10px;">
					<thead>
						<tr>
							<th>序号</th>
							<th>类别</th>
							<th>操作</th>
						</tr>
						<c:forEach items="${blogTypeList}" var="blogType" varStatus="status">
							<tr class="odd gradeX">
								<td>${status.index + 1}</td>
								<td>${blogType.typeName}</td>
								<td><a href="${pageContext.request.contextPath}/blog/bloglist/${blogType.id}.html">查看</a>&nbsp;(${blogType.blogCount})</td>
							</tr>
						</c:forEach>
					</thead>
				</table>
				<button type="button" class="btn btn-default btn-xs" data-toggle="modal" data-target="#addTypeModal">新增类别</button>
				<!-- 增添博客类别表单 -->
				<jsp:include page="blogTypeForm.jsp"/>
				<div style="text-align: center;">
					<div class="pagination">${pageCode}</div>
				</div>
			</div>
		</div>
	</div>
</div>
