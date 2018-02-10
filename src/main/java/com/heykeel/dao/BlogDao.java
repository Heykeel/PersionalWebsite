package com.heykeel.dao;

import java.util.List;
import java.util.Map;

import com.heykeel.entity.Blog;

public interface BlogDao {

	public List<Blog> getBlogList(Map<String,Integer> map);
	
	public List<Blog> getBlogListByType(Map<String,Integer> map);
	
	public List<Blog> getBlogListByDate(String releaseDateStr,Integer startNum, Integer pageSize);
	
	public Blog getBlogById(Integer id);
	
	public Integer getBlogSize();
	
	public Integer getBlogSizeByType(Integer typeId);
	
	public Integer getBlogSizeByDate(String releaseDateStr);
	
	public Blog getPrevBlog(Integer id);
	
	public Blog getNextBlog(Integer id);
	
	public void addBlog(Blog blog);
	
	public void updateBlog(Blog blog);
	
	public void deleteBlog(Integer id);
	
	public void updateClick(Integer id,Integer clickHit);
	
	public void updateReply(Integer id,Integer replyHit);
}
