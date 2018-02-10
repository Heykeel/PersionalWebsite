package com.heykeel.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.heykeel.entity.Blog;
import com.heykeel.entity.BlogType;
import com.heykeel.entity.Comment;
import com.heykeel.entity.DateTime;
import com.heykeel.entity.Pagination;
import com.heykeel.service.BlogService;
import com.heykeel.service.BlogTypeService;
import com.heykeel.service.CommentService;
import com.heykeel.util.PageUtil;

@Controller
@RequestMapping("/blog")
public class BlogController {
	@Resource
	private BlogService blogService;
	@Resource
	private BlogTypeService blogTypeService;
	@Resource
	private CommentService commentService;
	
	@RequestMapping("/articles/{id}")
	public ModelAndView article(@PathVariable("id") Integer id, HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		
		// 左侧信息
		List<BlogType> blogTypeList = blogTypeService.getBlogTypeList();
		modelAndView.addObject("blogTypeList",blogTypeList);
		modelAndView.addObject("dateList", getListDateTime());
		
		Blog blog = blogService.getBlogById(id);
		modelAndView.addObject("blog", blog);
		
		// 显示评论
		List<Comment> commentList = commentService.getCommentList(id);
		modelAndView.addObject("commentList", commentList);
		
		// 上一篇和下一篇文章的链接
		modelAndView.addObject("pageCode", PageUtil.getPrevAndNextArticle(blogService.getPrevBlog(id),
				blogService.getNextBlog(id), request.getServletContext().getContextPath()));
		
		// 页面信息
		modelAndView.addObject("commonPage", "blog-content.jsp");
		modelAndView.setViewName("/WEB-INF/pages/visitor/home");
		
		return modelAndView;
	}
	
	@RequestMapping("/bloglist/{typeId}")
	public ModelAndView bloglistBytype(@PathVariable("typeId") Integer typeId, @RequestParam(value = "page", required = false) String page,HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		
		// 左侧信息
		List<BlogType> blogTypeList = blogTypeService.getBlogTypeList();
		modelAndView.addObject("blogTypeList",blogTypeList);
		modelAndView.addObject("dateList", getListDateTime());
		
		// 博客分页
		if(page == null)
			page = "1";
		Integer currentPage = Integer.parseInt(page);
		Pagination pagination = new Pagination(currentPage,5);
		String pageCode =  PageUtil.getPagination(request.getContextPath() + "/blog/bloglist/"+typeId+".html", blogService.getBlogSizeByType(typeId), currentPage, pagination.getPageSize());
		Map<String,Integer> map = new HashMap<String,Integer>();
		map.put("startNum", pagination.getStartNum());
		map.put("pageSize", pagination.getPageSize());
		modelAndView.addObject("pageCode",pageCode);
		
		map.put("typeId", typeId);
		List<Blog> blogList = blogService.getBlogListByType(map);
		
		modelAndView.addObject("blogList", blogList);
		modelAndView.addObject("commonPage", "blog-list.jsp");
		modelAndView.setViewName("/WEB-INF/pages/visitor/home");
		return modelAndView;
	}
	
	@RequestMapping("/datelist/{releaseDateStr}")
	public ModelAndView bloglistBytype(@PathVariable("releaseDateStr") String releaseDateStr, @RequestParam(value = "page", required = false) String page,HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		
		// 左侧信息
		List<BlogType> blogTypeList = blogTypeService.getBlogTypeList();
		modelAndView.addObject("blogTypeList",blogTypeList);
		modelAndView.addObject("dateList", getListDateTime());
		
		// 博客分页
		if(page == null)
			page = "1";
		Integer currentPage = Integer.parseInt(page);
		Pagination pagination = new Pagination(currentPage,5);
		String pageCode =  PageUtil.getPagination(request.getContextPath() + "/blog/datelist/"+releaseDateStr+".html", blogService.getBlogSizeByDate(releaseDateStr), currentPage, pagination.getPageSize());
		modelAndView.addObject("pageCode",pageCode);
		
		List<Blog> blogList = blogService.getBlogListByDate(releaseDateStr,pagination.getStartNum(),pagination.getPageSize());
		
		modelAndView.addObject("blogList", blogList);
		modelAndView.addObject("commonPage", "blog-list.jsp");
		modelAndView.setViewName("/WEB-INF/pages/visitor/home");
		return modelAndView;
	}
	
	public List<DateTime> getListDateTime(){
		List<DateTime>  dateList = new ArrayList<DateTime>();
		Calendar cal=Calendar.getInstance();      
		for(int i=cal.get(Calendar.YEAR);i>=2017;i--){
			for(int j=12;j>=1;j--){
				StringBuffer time = new StringBuffer();
				StringBuffer timeCN = new StringBuffer();
				if(j<10){
					time.append(i);
					time.append("-0");
					time.append(j);
					
					timeCN.append(i);
					timeCN.append("年0");
					timeCN.append(j);
					timeCN.append("月");
				}else{
					time.append(i);
					time.append("-");
					time.append(j);
					
					timeCN.append(i);
					timeCN.append("年");
					timeCN.append(j);
					timeCN.append("月");
				}
				String releaseDateStr = time.toString();
				String releaseDateStrCN = timeCN.toString();
				
				Integer dateCount = blogService.getBlogSizeByDate(releaseDateStr);
				if(dateCount !=0){
					DateTime dateTime = new DateTime(releaseDateStr,releaseDateStrCN,dateCount);
					dateList.add(dateTime);
				}
			}
		}
		return dateList;
	}
}









