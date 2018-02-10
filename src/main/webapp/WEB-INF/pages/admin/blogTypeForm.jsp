<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    
<!-- 增添博客类别表单 -->
<form action="${pageContext.request.contextPath}/admin/blogtype/addblogtype.do" method="post" onsubmit="return addType()" accept-charset="UTF-8">
	<!-- 模态框（Modal） -->
	<div class="modal fade" id="addTypeModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content modal-sm">
			    <div class="modal-header" style="padding-bottom: 0px">
			        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					<h3 class="text-center text-primary" style="margin: 10px">新增类别</h3>
				</div>
				<div class="modal-body" style="padding-top:5px; padding-bottom: 0px;">
					<div class="form-group" style="margin-bottom:0px; line-height: 8px; height: 10px;">
							<font id="error" face="微软雅黑" style="font-size: 8px;"></font>
					</div>
					<div class="form-group">
						<input type="text" class="form-control input-sm" id="typeName" name="typeName" placeholder="类别名称">
					</div>
					<div class="form-group">
						<input type="text" class="form-control input-sm" id="orderNum" name="orderNum" placeholder="优先级">	
					</div>	
					<div class="form-group">
						<button class="btn btn-primary btn-sm btn-block" type="submit" style="margin-bottom: 5px;">确&nbsp;&nbsp;&nbsp;&nbsp;定</button>
					</div>	
				</div>
			</div><!-- /.modal-content -->
		</div><!-- /.modal-dialog -->
	</div>
</form>