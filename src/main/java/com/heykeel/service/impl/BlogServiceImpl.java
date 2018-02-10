package com.heykeel.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.heykeel.dao.BlogDao;
import com.heykeel.entity.Blog;
import com.heykeel.service.BlogService;

@Transactional
@Service("blogService")
public class BlogServiceImpl implements BlogService {

	@Resource
	private BlogDao blogDao;

	public List<Blog> getBlogList(Map<String, Integer> map) {
		return blogDao.getBlogList(map);
	}

	public Blog getBlogById(Integer id) {
		return blogDao.getBlogById(id);
	}

	public Integer getBlogSize() {
		return blogDao.getBlogSize();
	}

	public Blog getPrevBlog(Integer id) {
		return blogDao.getPrevBlog(id);
	}

	public Blog getNextBlog(Integer id) {
		return blogDao.getNextBlog(id);
	}

	public void addBlog(Blog blog) {
		blogDao.addBlog(blog);
	}
	
	public void updateBlog(Blog blog) {
		blogDao.updateBlog(blog);
	}

	public void deleteBlog(Integer id) {
		blogDao.deleteBlog(id);
	}

	public List<Blog> getBlogListByType(Map<String, Integer> map) {
		return blogDao.getBlogListByType(map);
	}

	public Integer getBlogSizeByType(Integer typeId) {
		return blogDao.getBlogSizeByType(typeId);
	}

	public Integer getBlogSizeByDate(String releaseDateStr) {
		return blogDao.getBlogSizeByDate(releaseDateStr);
	}

	public List<Blog> getBlogListByDate(String releaseDateStr,Integer startNum, Integer pageSize){
		return blogDao.getBlogListByDate(releaseDateStr, startNum, pageSize);
	}

	public void updateClick(Integer id, Integer clickHit) {
		blogDao.updateClick(id, clickHit);
	}

	public void updateReply(Integer id, Integer replyHit) {
		blogDao.updateReply(id, replyHit);
	}
	
}
