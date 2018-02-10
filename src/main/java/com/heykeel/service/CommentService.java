package com.heykeel.service;

import java.util.List;

import com.heykeel.entity.Comment;

public interface CommentService {
	public List<Comment> getCommentList(Integer blogId);
	public void addComment(Comment comment);
	public void	deleteCommentList(Integer blogId);
	public void	deleteComment(Integer id);
	public void reprotedById(Integer id, Boolean isReported);
	public List<Comment> getCommentListByReport();
	public Comment getCommentById(Integer id);
}
