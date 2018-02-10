package com.heykeel.service;

import com.heykeel.entity.Blogger;

public interface BloggerService {
	
	// 获取博主个人信息
	public Blogger getBloggerData();
	
	// 更新博主个人信息
	public void updateBlogger(Blogger blogger);
}
