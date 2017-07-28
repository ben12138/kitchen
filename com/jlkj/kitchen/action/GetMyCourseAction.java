package com.jlkj.kitchen.action;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.jlkj.kitchen.bean.Course;
import com.jlkj.kitchen.bean.MyCourse;
import com.jlkj.kitchen.dao.CourseDao;
import com.jlkj.kitchen.dao.MyCourseDao;
import com.jlkj.kitchen.daoimp.CourseDaoImp;
import com.opensymphony.xwork2.ActionSupport;

import net.sf.json.JSONObject;
/**
 * 得到我的报名的课程，需要传入userid
 * 返回值是success或者error
 * 对应的返回码查阅返回码表
 * @author benrui
 *
 */
public class GetMyCourseAction extends ActionSupport{
	
	private MyCourseDao dao;
	private int userid;
	private InputStream inputStream;
	
	public MyCourseDao getDao() {
		return dao;
	}

	public void setDao(MyCourseDao dao) {
		this.dao = dao;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public InputStream getResult() {
		return inputStream;
	}
	
	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public String get() throws UnsupportedEncodingException {
		JSONObject result = new JSONObject();
		List<MyCourse> mycourses = dao.getMyCourse(userid);
		if(mycourses == null) {
			result.put("code", 440);
			result.put("msg", ERROR);
			inputStream = new ByteArrayInputStream(result.toString().getBytes("utf-8"));
			return ERROR;
		}else {
			result.put("code", 200);
			result.put("msg", SUCCESS);
			CourseDao coursedao = new CourseDaoImp();
			List<Course> courses = new ArrayList<>();
			for(MyCourse mycourse:mycourses) {
				courses.add(coursedao.getCourse(mycourse.getCourseid()));
			}
			Gson gson = new Gson();
			result.put("data", gson.toJson(courses));
			inputStream = new ByteArrayInputStream(result.toString().getBytes("utf-8"));
			return SUCCESS;
		}
	}
	
}
