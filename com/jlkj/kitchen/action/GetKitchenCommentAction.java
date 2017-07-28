package com.jlkj.kitchen.action;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

import com.google.gson.Gson;
import com.jlkj.kitchen.bean.CourseComment;
import com.jlkj.kitchen.dao.CourseCommentDao;
import com.opensymphony.xwork2.ActionSupport;

import net.sf.json.JSONObject;
/**
 * 得到课程评论信息，需要传入课程id
 * 返回值是success或者error
 * 对应的返回码查阅返回码表
 * @author benrui
 *
 */
public class GetKitchenCommentAction extends ActionSupport{
	
	private InputStream inputStream;
	private CourseCommentDao dao;
	private int courseid;
	
	public InputStream getResult() {
		return inputStream;
	}
	
	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public CourseCommentDao getDao() {
		return dao;
	}

	public void setDao(CourseCommentDao dao) {
		this.dao = dao;
	}

	public int getCourseid() {
		return courseid;
	}

	public void setCourseid(int courseid) {
		this.courseid = courseid;
	}

	public String get() throws UnsupportedEncodingException {
		JSONObject result = new JSONObject();
		List<CourseComment> comments = dao.getCourseComment(courseid);
		if(comments == null) {
			result.put("code", 310);
			result.put("msg", ERROR);
			inputStream = new ByteArrayInputStream(result.toString().getBytes("utf-8"));
			return ERROR;
		}else {
			Gson gson = new Gson();
			result.put("code", 200);
			result.put("msg", SUCCESS);
			result.put("data", gson.toJson(comments));
			inputStream = new ByteArrayInputStream(result.toString().getBytes("utf-8"));
			return SUCCESS;
		}
	}
	
}
