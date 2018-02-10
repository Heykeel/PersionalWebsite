package com.heykeel.aop;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

import com.heykeel.entity.Blog;
import com.heykeel.entity.Comment;
import com.heykeel.service.BlogService;
import com.heykeel.service.CommentService;

@Component
@Aspect
public class ServiceAspect {
	
	@Resource
	private BlogService blogService;
	@Resource
	private CommentService commentService;
	@Resource
	JavaMailSenderImpl javaMailSender;
	
	/**定义增加文章切点**/
	@Pointcut("execution(* com.heykeel.controller.BlogController.article(..))")
	public void updateClickHit() {}
	
	@Before("updateClickHit()")
	public void beforeClickHit(JoinPoint joinPoint) {
		Object[] args = joinPoint.getArgs();
		Integer id = (Integer) args[0];
		
		Blog blog = blogService.getBlogById(id);
		synchronized (blog) {
			blogService.updateClick(id, blog.getClickHit()+1);
		}
	}
	
	/**定义增加评论切点**/
	@Pointcut("execution(* com.heykeel.controller.CommentController.save(..))")
	public void updateComment() {}
	
	@Before("updateComment()")
	public void beforeComment(JoinPoint joinPoint) {
		Object[] args = joinPoint.getArgs();
		String comment = (String) args[0];
		Integer id = (Integer) args[1];
		HttpServletRequest request = (HttpServletRequest) args[2];
		
		Blog blog = blogService.getBlogById(id);
		synchronized (blog) {
			blogService.updateReply(id, blog.getReplyHit()+1);
		}
		
		// 发送邮件
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		
		// 设置收件人，寄件人  
		mailMessage.setTo("641617230@qq.com");  
		mailMessage.setFrom("1943880182@qq.com");
		mailMessage.setSubject("有新的评论！");
		
		String visitorIp = request.getRemoteAddr(); // 获取网友ip
		String mailText = "尊敬的用户:"+"\n"+"游客"+visitorIp+"对博文《"+blog.getTitle()+"》评论了：\""+comment+"\"。评论地址："+"http://www.heykeel.com/blog/articles/"+id+".html。";
		mailMessage.setText(mailText);
		javaMailSender.send(mailMessage);
		
	}
	@AfterReturning("updateComment()")
	public void afterComment(JoinPoint joinPoint) {
		Object[] args = joinPoint.getArgs();
		Integer id = (Integer) args[1];
		Blog blog = blogService.getBlogById(id);
		synchronized (blog) {
			blogService.updateClick(id, blog.getClickHit()-1);
		}
	}
	
	/**定义举报评论切点**/
	@Pointcut("execution(* com.heykeel.controller.CommentController.reportedComment(..))")
	public void reportedComment() {}
	
	@Before("reportedComment()")
	public void reportedComment(JoinPoint joinPoint) {
		Object[] args = joinPoint.getArgs();
		Integer id = (Integer) args[0];
		HttpServletRequest request = (HttpServletRequest) args[2];
		
		Comment comment = commentService.getCommentById(id);
		Blog blog = comment.getBlog();
		
		// 发送邮件
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		
		// 设置收件人，寄件人  
		mailMessage.setTo("641617230@qq.com");  
		mailMessage.setFrom("1943880182@qq.com");
		mailMessage.setSubject("举报评论！");
		
		String visitorIp = request.getRemoteAddr(); // 获取网友ip
		String mailText = "尊敬的用户:"+"\n"+"游客"+visitorIp+"举报了博文《"+blog.getTitle()+"》的评论\""+comment.getContent()+"\"。评论地址："+"http://www.heykeel.com/blog/articles/"+blog.getId()+".html。";
		mailMessage.setText(mailText);
		javaMailSender.send(mailMessage);
		
	}
}
