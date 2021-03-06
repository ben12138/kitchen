package com.jlkj.kitchen.action;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import com.jlkj.kitchen.dao.MyCourseDao;
import com.opensymphony.xwork2.ActionSupport;

import net.sf.json.JSONObject;
/**
 * 判断有没有报名这个课程
 * 返回值是success或者error
 * 对应的返回码查阅返回码表
 * @author benrui
 *
 */
public class IsHasCourseAction extends ActionSupport {
	
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

	public String check() throws UnsupportedEncodingException {
		JSONObject result = new JSONObject();
		if(dao.hasCourse(courseid, userid)) {
			result.put("code", 200);
			result.put("msg", SUCCESS);
			inputStream = new ByteArrayInputStream(result.toString().getBytes("utf-8"));
			return SUCCESS;
		}else {
			result.put("code", 440);
			result.put("msg", ERROR);
			inputStream = new ByteArrayInputStream(result.toString().getBytes("utf-8"));
			return ERROR;
		}
	}
	
}
