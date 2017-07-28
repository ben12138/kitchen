package com.jlkj.kitchen.action;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import com.jlkj.kitchen.bean.MyCourse;
import com.jlkj.kitchen.dao.MyCourseDao;
import com.opensymphony.xwork2.ActionSupport;

import net.sf.json.JSONObject;
/**
 * 报名课程，需要课程id和个人信息id
 * 返回值是success或者error
 * 对应的返回码查阅返回码表
 * @author benrui
 *
 */
public class AddMyCourseAction extends ActionSupport{
	private int courseid;
	private int userid;
	private InputStream inputStream;
	private MyCourseDao dao;
	
	public int getCourseid() {
		return courseid;
	}

	public void setCourseid(int courseid) {
		this.courseid = courseid;
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

	public MyCourseDao getDao() {
		return dao;
	}

	public void setDao(MyCourseDao dao) {
		this.dao = dao;
	}

	public String add() throws UnsupportedEncodingException {
		JSONObject result = new JSONObject();
		MyCourse myCourse = new MyCourse();
		myCourse.setUserid(userid);
		myCourse.setCourseid(courseid);
		if(dao.addCourse(myCourse)) {
			result.put("code", 200);
			result.put("msg", SUCCESS);
			inputStream = new ByteArrayInputStream(result.toString().getBytes("utf-8"));
			return SUCCESS;
		}else {
			result.put("code", 460);
			result.put("msg", ERROR);
			inputStream = new ByteArrayInputStream(result.toString().getBytes("utf-8"));
			return ERROR;
		}
	}
}
