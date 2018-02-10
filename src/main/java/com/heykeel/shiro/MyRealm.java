package com.heykeel.shiro;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;

import com.heykeel.entity.Blogger;
import com.heykeel.service.BloggerService;

public class MyRealm extends AuthorizingRealm {
	@Resource
	private BloggerService bloggerService;
	// 为当前登陆的用户授予角色和权限
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
		// 这个个人博客项目是没有这一项的，因为就一个用户
		return null;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String username = (String) token.getPrincipal();  // 获取用户名
		Blogger blogger = bloggerService.getBloggerData();
		if(username.equals(blogger.getUsername())) {
			Subject currentUser = SecurityUtils.getSubject();
			currentUser.getSession().setTimeout(1000*60*10);
			AuthenticationInfo authcInfo = new SimpleAuthenticationInfo(blogger.getUsername(), blogger.getPassword(), "MyRealm");
			return authcInfo;
		}else {
			return null;
		}
	}


}
