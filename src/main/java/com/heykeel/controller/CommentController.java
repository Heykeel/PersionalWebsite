package com.heykeel.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.heykeel.entity.Comment;
import com.heykeel.service.BlogService;
import com.heykeel.service.CommentService;
import com.heykeel.util.ResponseUtil;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/comment")
public class CommentController {

	@Resource
	private BlogService blogService;
	@Resource
	private CommentService commentService;

	// 添加评论
	@RequestMapping("/save")
	public void save(@RequestParam("content") String content, @RequestParam("blogId") Integer id,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String visitorIp = request.getRemoteAddr(); // 获取网友ip
		Comment comment = new Comment();
		
		synchronized (comment) {
			comment.setBlog(blogService.getBlogById(id));
			comment.setVisitorIp(visitorIp);
			comment.setContent(content);
			commentService.addComment(comment);
		}
		
		JSONObject result = new JSONObject();
		result.put("success", true);
		ResponseUtil.write(response, result);
	}
	
	@RequestMapping("/reproted")
	public void reportedComment(@RequestParam(value = "id", required = false) Integer id,@RequestParam(value = "flag", required = false) Integer flag,HttpServletRequest request,HttpServletResponse response) throws Exception{
		if(flag == 1)
			commentService.reprotedById(id,true);
		else
			commentService.reprotedById(id,false);
		
		JSONObject result = new JSONObject();
		result.put("success", true);
		ResponseUtil.write(response, result);
	}
}
