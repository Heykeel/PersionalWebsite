<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML>

<!-- 博客修改界面 -->
<div class="row">
	<div class="col-sm-12">
		<h2 class="page-header">修改博客</h2>
	</div>
</div>

<div class="row">
	<div class="col-sm-12">
		<div class="panel panel-default">
			<div class="panel-heading">发布内容</div>
			<div class="panel-body">
				<div class="row">
					<div class="col-sm-12">
						<div class="form-group">
							<label>博客标题</label> <input class="form-control" id="title"
								name="title" value="${blog.title}">
						</div>
						<div class="form-group">
							<label>博客类别</label> <select id="blogTypeId" name="blogType.id"
								class="form-control">
								<option value="">请选择博客类别...</option>
								<c:forEach items="${blogTypeList}" var="blogType">
									<c:choose>
										<c:when test="${blogType.typeName==blog.blogType.typeName}">
											<option value="${blogType.id}" selected="selected">${blogType.typeName}</option>
										</c:when>
										<c:otherwise>
											<option value="${blogType.id}">${blogType.typeName}</option>
										</c:otherwise>
									</c:choose>

								</c:forEach>
							</select>
						</div>
						<div class="form-group">
							<label>博客内容</label>
							<script id="editor" type="text/plain" style="width: 100%; height: 500px;"></script>
						</div>
						<div class="form-group">
							<label>关键字</label> <input class="form-control" id="keyWord"
								name="keyWord" value="${blog.keyWord}">
							<p class="help-block">每个关键字请用空格分开！</p>
						</div>
						<button type="button" class="btn btn-default"
							onclick="publishBlog()">博客发布</button>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">
	var ue = UE.getEditor('editor'); //初始化对象  
	function publishBlog() {
		var id = "${blog.id}";
		var title = $("#title").val();
		var blogTypeId = $("#blogTypeId").find("option:selected").val();
		var content = UE.getEditor('editor').getContent();
		var summary = UE.getEditor('editor').getContentTxt().substr(0, 30);
		var keyWord = $("#keyWord").val();

		if (title == null || title == '') {
			alert("请输入标题！");
		} else if (blogTypeId == null || blogTypeId == '') {
			alert("请选择博客类型！");
		} else if (content == null || content == '') {
			alert("请编辑博客内容！");
		} else {
			$.post("${pageContext.request.contextPath}/admin/blog/save.do", {
				"id":id,
				"title" : title,
				"blogType.id" : blogTypeId,
				"clickHit":"${blog.clickHit}",
				"replyHit":"${blog.replyHit}",
				"content" : content,
				"summary" : summary,
				"keyWord" : keyWord
			}, function(result) {
				if (result.success) {
					alert("博客修改成功！");
					clearValues();
				} else {
					alert("博客修改失败！");
				}
			}, "json")
		}

		function clearValues() {
			$("#title").val("");
			$("#blogTypeId > option:first").attr("selected", true);
			UE.getEditor("editor").setContent("");
			$("#keyWord").val("");
		}
	}
</script>