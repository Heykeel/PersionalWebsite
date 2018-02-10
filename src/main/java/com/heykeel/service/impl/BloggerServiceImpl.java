package com.heykeel.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.heykeel.dao.BloggerDao;
import com.heykeel.entity.Blogger;
import com.heykeel.service.BloggerService;

@Transactional
@Service("bloggerService")
public class BloggerServiceImpl implements BloggerService {

	@Resource
	private BloggerDao bloggerDao;
	
	// 获取博主信息
	public Blogger getBloggerData() {
		return bloggerDao.getBloggerData();
	}

	// 更新博主信息
	public void updateBlogger(Blogger blogger) {
		bloggerDao.updateBlogger(blogger);
	}

}
