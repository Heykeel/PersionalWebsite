package com.heykeel.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.heykeel.dao.BlogTypeDao;
import com.heykeel.entity.BlogType;
import com.heykeel.service.BlogTypeService;

@Transactional
@Service("blogTypeService")
public class BlogTypeServiceImpl implements BlogTypeService {

	@Resource
	private BlogTypeDao blogTypeDao;

	public List<BlogType> getBlogTypeList() {
		return blogTypeDao.getBlogTypeList();
	}
	
	public List<BlogType> getBlogTypeList(Map<String, Integer> map) {
		
		return blogTypeDao.getBlogTypeListPage(map);
	}

	public BlogType getTypeById(Integer id) {
		return blogTypeDao.getTypeById(id);
	}

	public Integer getBlogTypeSize(){
		return blogTypeDao.getBlogTypeSize();
	}

	public void addBlogType(String typeName, Integer orderNum) {
		blogTypeDao.addBlogType(typeName, orderNum);
	}

}
