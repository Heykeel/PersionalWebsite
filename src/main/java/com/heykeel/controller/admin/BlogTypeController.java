package com.heykeel.controller.admin;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.heykeel.entity.BlogType;
import com.heykeel.entity.Pagination;
import com.heykeel.service.BlogTypeService;
import com.heykeel.util.PageUtil;

@Controller
@RequestMapping("/admin/blogtype")
public class BlogTypeController {
	
	@Resource
	private BlogTypeService blogTypeService;
	
	@RequestMapping("/blogtypelist")
	public ModelAndView blogtypelist(@RequestParam(value = "page", required = false) String page,
			HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();

		if (page == null)
			page = "1";
		Integer currentPage = Integer.parseInt(page);
		Pagination pagination = new Pagination(currentPage, 5);
		String pageCode = PageUtil.getPagination(request.getContextPath() + "/admin/blog/blogtypelist.html",
				blogTypeService.getBlogTypeSize(), currentPage, pagination.getPageSize());

		// 获取博客信息
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("startNum", pagination.getStartNum());
		map.put("pageSize", pagination.getPageSize());

		List<BlogType> blogTypeList = blogTypeService.getBlogTypeList(map);

		modelAndView.addObject("pageCode", pageCode);
		modelAndView.addObject("blogTypeList", blogTypeList);
		modelAndView.addObject("commonAdminPage", "/admin/blogTypeList.jsp");
		modelAndView.setViewName("/WEB-INF/pages/admin/admin");
		return modelAndView;
	}
	
	@RequestMapping("/addblogtype")
	public ModelAndView addblogtype(@RequestParam("typeName") String typeName, @RequestParam("orderNum") Integer orderNum, @RequestParam(value = "page", required = false) String page,
			HttpServletRequest request) throws UnsupportedEncodingException {
		ModelAndView modelAndView = new ModelAndView();

		typeName = new String(typeName.getBytes("ISO-8859-1"));
		blogTypeService.addBlogType(typeName, orderNum);
		
		if (page == null)
			page = "1";
		Integer currentPage = Integer.parseInt(page);
		Pagination pagination = new Pagination(currentPage, 5);
		String pageCode = PageUtil.getPagination(request.getContextPath() + "/admin/blog/blogtypelist.html",
				blogTypeService.getBlogTypeSize(), currentPage, pagination.getPageSize());

		// 获取博客信息
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("startNum", pagination.getStartNum());
		map.put("pageSize", pagination.getPageSize());

		List<BlogType> blogTypeList = blogTypeService.getBlogTypeList(map);

		modelAndView.addObject("pageCode", pageCode);
		modelAndView.addObject("blogTypeList", blogTypeList);
		modelAndView.addObject("commonAdminPage", "blogTypeList.jsp");
		modelAndView.setViewName("/WEB-INF/pages/admin/admin");
		return modelAndView;
	}
}
