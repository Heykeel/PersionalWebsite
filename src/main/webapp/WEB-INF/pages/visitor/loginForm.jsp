<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!-- 登陆表单 -->
	<!-- 模态框（Modal） -->
	<div class="modal fade" id="loginModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	    <div class="modal-dialog">
	        <div class="modal-content modal-sm">
	        	<div class="modal-header" style="padding-bottom: 0px">
	        	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					<h3 class="text-center text-primary" style="margin: 10px">登&nbsp;&nbsp;录</h3>
				</div>
				<div class="modal-body" style="padding-top:5px; padding-bottom: 0px;">
					<div class="form-group" style="margin-bottom:0px; line-height: 8px; height: 10px;">
							<font id="error" face="微软雅黑" style="font-size: 8px;" ></font>
					</div>
					<div class="form-group">
						<input type="text" class="form-control input-sm" id="username" name="username" placeholder="用户名">
					</div>
					<div class="form-group">
						<input type="password" class="form-control input-sm" id="password" name="password" placeholder="登录密码">	
					</div>	
					<div class="form-group" style="margin-bottom: 10px;">
						<button class="btn btn-primary btn-sm btn-block" onclick="login()" style="margin-bottom: 5px;">立即登录</button>
						<!-- 
						<span><a href="#">找回密码</a></span>
						<span><a href="#" class="pull-right">注册</a></span>	
						 -->
					</div>			
				</div>
	        </div><!-- /.modal-content -->
	    </div><!-- /.modal-dialog -->
	</div>

<script type="text/javascript">
/* 后台管理登录请求 */
function login() {
		var username = $("#username").val();
		var password = $("#password").val();

		if (username == null || username == "") {
			$("#error").html("用户名不能为空！");
			return;
		}
		if (password == null || password == "") {
			$("#error").html("密码不能为空！");
			return;
		}
		$.post("${pageContext.request.contextPath}/login.do", {
			"username" : username,
			"password" : password,
		}, function(result) {
			if (result.success) {
				window.location.href="${pageContext.request.contextPath}/admin/blog/index.html";
				$("#error").val("");
			}else {
				$("#error").html("用户名或者密码错误！");
				
			}
		}, "json")
}
</script>