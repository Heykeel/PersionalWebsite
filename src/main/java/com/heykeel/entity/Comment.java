package com.heykeel.entity;

import java.util.Date;

public class Comment {
	
	private Integer id;          // 评论id
	private String visitorIp;    // 游客ip
	private String content;      // 评论内容
	private Date commentDate;    // 评论日期
	private Blog blog;           // 关联的博客
	private boolean isReported;  // 是否被举报
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getVisitorIp() {
		return visitorIp;
	}
	public void setVisitorIp(String visitorIp) {
		this.visitorIp = visitorIp;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getCommentDate() {
		return commentDate;
	}
	public void setCommentDate(Date commentDate) {
		this.commentDate = commentDate;
	}
	public Blog getBlog() {
		return blog;
	}
	public void setBlog(Blog blog) {
		this.blog = blog;
	}
	public boolean isReported() {
		return isReported;
	}
	public void setReported(boolean isReported) {
		this.isReported = isReported;
	}
}
