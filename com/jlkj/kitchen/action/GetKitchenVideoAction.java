package com.jlkj.kitchen.action;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

import com.google.gson.Gson;
import com.jlkj.kitchen.bean.CourseVideo;
import com.jlkj.kitchen.dao.CourseDao;
import com.jlkj.kitchen.dao.CourseVideoDao;
import com.opensymphony.xwork2.ActionSupport;

import net.sf.json.JSONObject;
/**
 * 得到课程的视频信息，需要传入课程id
 * 返回值是success或者error
 * 对应的返回码查阅返回码表
 * @author benrui
 *
 */
public class GetKitchenVideoAction extends ActionSupport{
	
	private InputStream inputStream;
	private int courseid;
	private CourseVideoDao dao;
	
	public InputStream getResult() {
		return inputStream;
	}
	
	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public int getCourseid() {
		return courseid;
	}

	public void setCourseid(int courseid) {
		this.courseid = courseid;
	}

	public CourseVideoDao getDao() {
		return dao;
	}

	public void setDao(CourseVideoDao dao) {
		this.dao = dao;
	}

	public String get() throws UnsupportedEncodingException {
		JSONObject result = new JSONObject();
		List<CourseVideo> coursevideos = dao.getCourseVideo(courseid);
		if(coursevideos == null) {
			result.put("code", 420);
			result.put("msg", ERROR);
			inputStream = new ByteArrayInputStream(result.toString().getBytes("utf-8"));
			return ERROR;
		}else {
			Gson gson = new Gson();
			result.put("code", 200);
			result.put("msg", SUCCESS);
			result.put("data", gson.toJson(coursevideos));
			inputStream = new ByteArrayInputStream(result.toString().getBytes("utf-8"));
			return SUCCESS;
		}
	}
	
}
