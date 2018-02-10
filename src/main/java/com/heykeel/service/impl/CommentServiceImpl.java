package com.heykeel.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.heykeel.dao.CommentDao;
import com.heykeel.entity.Comment;
import com.heykeel.service.CommentService;

@Transactional
@Service("commentService")
public class CommentServiceImpl implements CommentService {

	@Resource
	private CommentDao commentDao;

	public List<Comment> getCommentList(Integer blogId) {
		return commentDao.getCommentList(blogId);
	}

	public void addComment(Comment comment) {
		commentDao.addComment(comment);
	}

	public void deleteCommentList(Integer blogId) {
		commentDao.deleteCommentList(blogId);
	}

	public void deleteComment(Integer id) {
		commentDao.deleteComment(id);
	}

	public void reprotedById(Integer id, Boolean isReported) {
		commentDao.reprotedById(id, isReported);
	}

	public List<Comment> getCommentListByReport() {
		return commentDao.getCommentListByReport();
	}

	public Comment getCommentById(Integer id) {
		return commentDao.getCommentById(id);
	}
}
