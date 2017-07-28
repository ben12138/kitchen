package com.jlkj.kitchen.action;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

import com.google.gson.Gson;
import com.jlkj.kitchen.bean.Course;
import com.jlkj.kitchen.dao.CourseDao;
import com.opensymphony.xwork2.ActionSupport;

import net.sf.json.JSONObject;
/**
 * 得到推荐课程信息，不需要传参数
 * 返回值是success或者error
 * 对应的返回码查阅返回码表
 * @author benrui
 *
 */
public class GetRecommendCourseAction extends ActionSupport {
	
	private CourseDao dao;
	private InputStream inputStream;
	
	public CourseDao getDao() {
		return dao;
	}

	public void setDao(CourseDao dao) {
		this.dao = dao;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public String get() throws UnsupportedEncodingException {
		JSONObject result = new JSONObject();
		List<Course> courses = dao.getRecommendCourse();
		if(courses == null) {
			result.put("code", 410);
			result.put("msg", ERROR);
			inputStream = new ByteArrayInputStream(result.toString().getBytes("utf-8"));
			return ERROR;
		}else {
			Gson gson = new Gson();
			result.put("code", 200);
			result.put("msg", SUCCESS);
			result.put("data", gson.toJson(courses));
			inputStream = new ByteArrayInputStream(result.toString().getBytes("utf-8"));
			return SUCCESS;
		}
	}

}
