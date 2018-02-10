<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!-- 博客正文抬头 -->
<div class="page-header"></div>
<div class="well">
	<div class="blog-header">
        <h1 class="blog-title">${blog.title}</h1>
    </div>
    <div id="tag">
    	<div class="container">
			<div class="row">
				<div class="col-sm-8" style="padding: 0px;">
					<i class="fa fa-tags"></i>
					<span>${blog.keyWord}</span>
				</div>
				<div class="col-sm-4" style="padding: 0px;text-align: right; margin-bottom: 10px;">
					<font color="#999"><fmt:formatDate value="${blog.releaseDate}" type="date" pattern="yyyy-MM-dd HH:mm"/></font> &nbsp;&nbsp;
					<font color="#336699">阅读<font color="#999">(${blog.clickHit})</font>&nbsp;&nbsp;</font>
					<font color="#336699">评论<font color="#999">(${blog.replyHit})</font></font>
				</div>
			</div>
			<div class="row">
				<hr style="height: 1px; border-top: 1px solid #A1A1A1;margin-top: 0px;" />
			</div>
			<div class="row">
				<p>${blog.content}</p>
			</div>
			<div class="row">
				<div id="paperLink" style="margin-top: 7px;text-align: left">${pageCode}</div>
			</div>
		</div>
    </div>
</div>

<!-- 评论部分 -->
<div id="commentList">
	<div class="well">
		<div class="commentTitle">
			<span class="fa fa-comment-o" style="font-weight: bold;">&nbsp;&nbsp;查看评论</span>
		</div>
		<ul>
			<c:choose>
				<c:when test="${commentList.size()==0}"><span style="padding-left: 20px;">暂无评论</span></c:when>
				<c:otherwise>
					<c:forEach items="${commentList}" var="comment" varStatus="status">
						<div class="commentInfo">
							<span style="padding-left: 20px;">${commentList.size()-status.index}楼</span>
							<span>${comment.visitorIp}</span>
							<span><fmt:formatDate value="${comment.commentDate}" type="date" pattern="yyyy-MM-dd HH:mm" /></span>	
							<span><a href="javascript:void(0);" onclick="reportComment(${comment.id})">举报</a></span>
						</div>
						<div class="comment">
							<div class="container">
								<div class="row">
									<div class="col-sm-1" style="padding-left: 0px;padding-right: 0px; left: 2%;">
										<img src="${pageContext.request.contextPath}/static/image/comment.jpg">
									</div>
									<div class="col-sm-11" style="padding-left: 0px;padding-right: 30px;">
										<p>${comment.content}</p>
									</div>
								</div>
							</div>
						</div>
					</c:forEach>
				</c:otherwise>
			</c:choose>
		</ul>
	</div>
	
	<!-- 添加评论 -->
	<div class="well" style="padding-bottom: 15px;">
		<div class="commentTitle">
			<span class="fa fa-edit" style="font-weight: bold;">&nbsp;&nbsp;发表评论</span>
		</div>
		<div style="padding-top: 5px">
			<textarea style="width: 100%" rows="3" id="content" name="content" placeholder="来说两句吧..."></textarea>
		</div>
		<div style="padding-top: 5px">
			<button class="btn btn-primary btn-xs" type="button" onclick="submitData()">发表评论</button>
		</div>
	</div>
</div>

<script type="text/javascript">
	/* 发表评论 */
	function submitData() {
		var content = $("#content").val();
		$.post("${pageContext.request.contextPath}/comment/save.do", {
			"content": content,
			"blogId":"${blog.id}"
		},function(result) {
			if (result.success) {
				alert("评论成功！");
				window.location.reload();
			} 
		},"json");
	}
	
	/* 举报评论 */
	function reportComment(id) {
		$.post("${pageContext.request.contextPath}/comment/reproted.do", {
			"id": id,
			"flag":1
		},function(result) {
			if (result.success) {
				alert("举报成功！");
				window.location.reload();
			} 
		},"json");
	}
</script>




