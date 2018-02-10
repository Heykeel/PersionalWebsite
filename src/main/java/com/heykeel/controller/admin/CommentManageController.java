package com.heykeel.controller.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.heykeel.entity.Blog;
import com.heykeel.entity.Comment;
import com.heykeel.entity.Pagination;
import com.heykeel.service.BlogService;
import com.heykeel.service.CommentService;
import com.heykeel.util.PageUtil;
import com.heykeel.util.ResponseUtil;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/admin/comment")
public class CommentManageController {
	
	@Resource
	private BlogService blogService;
	@Resource
	private CommentService commentService;
	
	@RequestMapping("/commentlist")
	public ModelAndView commentList(@RequestParam(value = "page", required = false) String page,
			HttpServletRequest request){
		ModelAndView modelAndView = new ModelAndView();
		
		if (page == null)
			page = "1";
		Integer currentPage = Integer.parseInt(page);
		Pagination pagination = new Pagination(currentPage, 5);
		String pageCode = PageUtil.getPagination(request.getContextPath() + "/admin/comment/commentlist.html",
				blogService.getBlogSize(), currentPage, pagination.getPageSize());

		// 获取博客信息
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("startNum", pagination.getStartNum());
		map.put("pageSize", pagination.getPageSize());

		List<Blog> blogList = blogService.getBlogList(map);

		modelAndView.addObject("pageCode", pageCode);
		modelAndView.addObject("blogList", blogList);
		modelAndView.addObject("commonAdminPage", "commentList.jsp");
		modelAndView.setViewName("/WEB-INF/pages/admin/admin");
		return modelAndView;
	}
	
	@RequestMapping("/comments/{id}")
	public ModelAndView comments(@PathVariable("id") Integer id, @RequestParam(value = "page", required = false) String page, HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		
		// 显示评论
		List<Comment> commentList = commentService.getCommentList(id);
		modelAndView.addObject("commentList", commentList);
		
		if (page == null)
			page = "1";
		Integer currentPage = Integer.parseInt(page);
		Pagination pagination = new Pagination(currentPage, 5);
		String pageCode = PageUtil.getPagination(request.getContextPath() + "/admin/comment/comments/"+id+".html",
				commentList.size(), currentPage, pagination.getPageSize());
		modelAndView.addObject("pageCode", pageCode);
		
		Blog blog = blogService.getBlogById(id);
		modelAndView.addObject("blog", blog);
		
		// 页面信息
		modelAndView.addObject("commonAdminPage", "commentDetail.jsp");
		modelAndView.setViewName("/WEB-INF/pages/admin/admin");
		return modelAndView;
	}
	
	@RequestMapping("deletecomment")
	public void deleteComnment(@RequestParam(value = "id", required = false) String idStr, HttpServletResponse response) throws Exception {
		Integer id = Integer.parseInt(idStr);
		Comment comment =  commentService.getCommentById(id);
		System.out.println(comment.getBlog().getReplyHit());
		synchronized (comment) {
			blogService.updateReply(comment.getBlog().getId(), comment.getBlog().getReplyHit()-1);
			System.out.println(comment.getBlog().getReplyHit());
			commentService.deleteComment(id);
		}
		
		JSONObject result = new JSONObject();
		result.put("success", true);
		ResponseUtil.write(response, result);
	}
	
	@RequestMapping("checkcomment")
	public ModelAndView checkComment(@RequestParam(value = "page", required = false) String page,HttpServletRequest request){
		ModelAndView modelAndView = new ModelAndView();
		List<Comment> commentList = commentService.getCommentListByReport();
		
		if (page == null)
			page = "1";
		Integer currentPage = Integer.parseInt(page);
		Pagination pagination = new Pagination(currentPage, 5);
		String pageCode = PageUtil.getPagination(request.getContextPath() + "/admin/comment/checkcomment.html",
				commentList.size(), currentPage, pagination.getPageSize());
		modelAndView.addObject("pageCode", pageCode);
		
		modelAndView.addObject("commentList", commentList);
		modelAndView.addObject("commonAdminPage", "commentReported.jsp");
		modelAndView.setViewName("/WEB-INF/pages/admin/admin");
		return modelAndView;
	}
}
