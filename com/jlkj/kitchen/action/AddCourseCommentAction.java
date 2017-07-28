package com.jlkj.kitchen.action;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import com.jlkj.kitchen.bean.CourseComment;
import com.jlkj.kitchen.dao.CourseCommentDao;
import com.opensymphony.xwork2.ActionSupport;

import net.sf.json.JSONObject;
/**
 * 给课程添加评论，需要传入除评论id以外的值
 * 返回值是success或者error
 * 对应的返回码查阅返回码表
 * @author benrui
 *
 */
public class AddCourseCommentAction extends ActionSupport{
	
	private CourseComment comment;
	private CourseCommentDao dao;
	private InputStream inputStream;
	
	public CourseComment getComment() {
		return comment;
	}

	public void setComment(CourseComment comment) {
		this.comment = comment;
	}

	public CourseCommentDao getDao() {
		return dao;
	}

	public void setDao(CourseCommentDao dao) {
		this.dao = dao;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public String add() throws UnsupportedEncodingException {
		JSONObject result = new JSONObject();
		if(dao.addCourseComment(comment)) {
			result.put("code", 200);
			result.put("msg", SUCCESS);
			inputStream = new ByteArrayInputStream(result.toString().getBytes("utf-8"));
			return SUCCESS;
		}else {
			result.put("code", 320);
			result.put("msg", ERROR);
			inputStream = new ByteArrayInputStream(result.toString().getBytes("utf-8"));
			return ERROR;
		}
	}
	
}
