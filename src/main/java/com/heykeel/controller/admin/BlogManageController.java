package com.heykeel.controller.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.heykeel.entity.Blog;
import com.heykeel.entity.BlogType;
import com.heykeel.entity.Pagination;
import com.heykeel.service.BlogService;
import com.heykeel.service.BlogTypeService;
import com.heykeel.service.CommentService;
import com.heykeel.util.PageUtil;
import com.heykeel.util.ResponseUtil;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/admin/blog")
public class BlogManageController {

	@Resource
	private BlogService blogService;
	@Resource
	private BlogTypeService blogTypeService;
	@Resource
	private CommentService commentService;

	@RequestMapping("/index")
	public ModelAndView index() {
		ModelAndView modelAndView = new ModelAndView();
		List<BlogType> blogTypeList = blogTypeService.getBlogTypeList();
		modelAndView.addObject("blogTypeList", blogTypeList);
		modelAndView.addObject("commonAdminPage", "publishBlog.jsp");
		modelAndView.setViewName("/WEB-INF/pages/admin/admin");
		return modelAndView;
	}

	//添加和更新博客
	@RequestMapping("/save")
	public void save(Blog blog,HttpServletResponse response) throws Exception{
		if(blog.getId()==null)
			blogService.addBlog(blog);
		else{
			blogService.updateBlog(blog);
		}
		JSONObject result = new JSONObject();
		result.put("success", true);
		ResponseUtil.write(response, result);
	}
	
	@RequestMapping("/bloglist")
	public ModelAndView blogList(@RequestParam(value = "page", required = false) String page,
			HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();

		if (page == null)
			page = "1";
		Integer currentPage = Integer.parseInt(page);
		Pagination pagination = new Pagination(currentPage, 5);
		String pageCode = PageUtil.getPagination(request.getContextPath() + "/admin/blog/bloglist.html",
				blogService.getBlogSize(), currentPage, pagination.getPageSize());

		// 获取博客信息
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("startNum", pagination.getStartNum());
		map.put("pageSize", pagination.getPageSize());

		List<Blog> blogList = blogService.getBlogList(map);

		modelAndView.addObject("pageCode", pageCode);
		modelAndView.addObject("blogList", blogList);
		modelAndView.addObject("commonAdminPage", "blogListAdmin.jsp");
		modelAndView.setViewName("/WEB-INF/pages/admin/admin");
		return modelAndView;
	}

	@RequestMapping("/modifyBlog")
	public ModelAndView modifyBlog(@RequestParam(value = "id", required = false) Integer id) {
		ModelAndView modelAndView = new ModelAndView();

		Blog blog = blogService.getBlogById(id);
		List<BlogType> blogTypeList = blogTypeService.getBlogTypeList();

		modelAndView.addObject("blog", blog);
		modelAndView.addObject("blogTypeList", blogTypeList);
		modelAndView.addObject("commonAdminPage", "modifyBlog.jsp");
		modelAndView.setViewName("/WEB-INF/pages/admin/admin");
		return modelAndView;
	}

	@RequestMapping("deleteBlog")
	public void deleteBlog(@RequestParam(value = "id", required = false) String id, HttpServletResponse response)
			throws Exception {

		blogService.deleteBlog(Integer.parseInt(id));
		commentService.deleteCommentList(Integer.parseInt(id));

		JSONObject result = new JSONObject();
		result.put("success", true);
		ResponseUtil.write(response, result);
	}

}

