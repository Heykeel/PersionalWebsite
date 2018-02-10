package com.heykeel.dao;

import java.util.List;
import java.util.Map;

import com.heykeel.entity.BlogType;


public interface BlogTypeDao {

	// 获取博客类别信息
	public List<BlogType> getBlogTypeList();
	public List<BlogType> getBlogTypeListPage(Map<String, Integer> map);
	
	// 根据id查找博客类别信息
	public BlogType getTypeById(Integer id);
	
	public Integer getBlogTypeSize();
	public void addBlogType(String typeName, Integer orderNum);
}
