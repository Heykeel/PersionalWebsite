package com.heykeel.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.heykeel.entity.Blog;
import com.heykeel.entity.BlogType;
import com.heykeel.entity.Blogger;
import com.heykeel.entity.DateTime;
import com.heykeel.entity.Pagination;
import com.heykeel.service.BlogService;
import com.heykeel.service.BlogTypeService;
import com.heykeel.service.BloggerService;
import com.heykeel.util.CryptographyUtil;
import com.heykeel.util.PageUtil;
import com.heykeel.util.ResponseUtil;

import net.sf.json.JSONObject;

@Controller
public class IndexController {
	@Resource
	private BloggerService bloggerService;
	@Resource
	private BlogTypeService blogTypeService;
	@Resource
	private BlogService blogService;
	
	@RequestMapping("/index")
	public ModelAndView index(@RequestParam(value = "page", required = false) String page,HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		
		// 博客分页
		if(page == null)
			page = "1";
		Integer currentPage = Integer.parseInt(page);
		Pagination pagination = new Pagination(currentPage,5);
		String pageCode =  PageUtil.getPagination(request.getContextPath() + "/index.html", blogService.getBlogSize(), currentPage, pagination.getPageSize());
		
		// 获取博主信息
		Blogger blogger = bloggerService.getBloggerData();
		// 获取博客信息
		Map<String,Integer> map = new HashMap<String,Integer>();
		map.put("startNum", pagination.getStartNum());
		map.put("pageSize", pagination.getPageSize());
		
		List<Blog> blogList = blogService.getBlogList(map);
		
		modelAndView.addObject("pageCode",pageCode);
		modelAndView.addObject("blogger", blogger);
		modelAndView.addObject("blogList", blogList);
		modelAndView.addObject("commonPage", "blog-list.jsp");
		
		// 左侧信息
		List<BlogType> blogTypeList = blogTypeService.getBlogTypeList();
		modelAndView.addObject("blogTypeList",blogTypeList);
		modelAndView.addObject("dateList", getListDateTime());
		
		modelAndView.setViewName("/WEB-INF/pages/visitor/home");
		return modelAndView;
	}
	
	@RequestMapping("/aboutme")
	public ModelAndView aboutMe() {
		ModelAndView modelAndView = new ModelAndView();
		// 左侧信息
		List<BlogType> blogTypeList = blogTypeService.getBlogTypeList();
		modelAndView.addObject("blogTypeList",blogTypeList);
		modelAndView.addObject("dateList", getListDateTime());
		
		modelAndView.addObject("commonPage", "about-me.jsp");
		modelAndView.setViewName("/WEB-INF/pages/visitor/home");
		return modelAndView;
	}
	
	@RequestMapping("/login")
	public void login(@RequestParam("username") String username, @RequestParam("password") String password,HttpServletResponse response) {
		Blogger blogger = bloggerService.getBloggerData();
		Subject subject = SecurityUtils.getSubject();      // 获取当前登录的主体
		String newPassword = CryptographyUtil.md5(password, "javacoder");//将密码使用md5加密
		// 将用户信息封装到token当中
		UsernamePasswordToken token = new UsernamePasswordToken(blogger.getUsername(),newPassword);
		
		JSONObject result = new JSONObject();
		try {
			subject.login(token);
			result.put("success", true);
		} catch (AuthenticationException e) {
			result.put("success", false);
		} finally {
			try {
				ResponseUtil.write(response, result);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	@RequestMapping("/loginout")
	public void loginout(HttpServletRequest request, HttpServletResponse response) {
		try {
			Subject subject = SecurityUtils.getSubject();
			subject.logout();
			response.sendRedirect(request.getContextPath()+"/index.html");
		} catch (IOException e) {
			e.printStackTrace();
		}
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