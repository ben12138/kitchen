package com.jlkj.kitchen.dao;

import java.util.List;

import com.jlkj.kitchen.bean.Comment;

public interface CommentDao {
	public boolean addComment(Comment comment);
	public List<Comment> getComment(int menuid);
	public boolean deleteComment(int id);
	public boolean updateComment(Comment comment);
}
