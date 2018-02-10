package com.heykeel.dao;

import java.util.List;

import com.heykeel.entity.Comment;

public interface CommentDao {
	public List<Comment> getCommentList(Integer blogId);
	public void	addComment(Comment comment);
	public void	deleteCommentList(Integer blogId);
	public void	deleteComment(Integer id);
	public void reprotedById(Integer id, Boolean isReported);
	public List<Comment> getCommentListByReport();
	public Comment getCommentById(Integer id);
}
