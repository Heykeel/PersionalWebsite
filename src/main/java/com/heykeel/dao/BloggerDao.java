package com.heykeel.dao;

import com.heykeel.entity.Blogger;

public interface BloggerDao {

	// 获取博主信息
	public Blogger getBloggerData();
	
	// 更新博主信息
	public void updateBlogger(Blogger blogger);
}
