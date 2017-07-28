package com.jlkj.kitchen.action;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import com.google.gson.Gson;
import com.jlkj.kitchen.bean.Admin;
import com.jlkj.kitchen.dao.AdminDao;
import com.opensymphony.xwork2.ActionSupport;

import net.sf.json.JSONObject;
/**
 * 得到教师信息，传入教师id
 * 返回值是success或者error
 * 对应的返回码查阅返回码表
 * @author benrui
 *
 */
public class GetTeacherAction extends ActionSupport{
	
	private int id;
	private InputStream inputStream;
	private AdminDao dao;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public AdminDao getDao() {
		return dao;
	}

	public void setDao(AdminDao dao) {
		this.dao = dao;
	}

	public String get() throws UnsupportedEncodingException {
		JSONObject result = new JSONObject();
		Admin admin = dao.getAdmin(id);
		if(admin == null) {
			result.put("code", 430);
			result.put("msg", ERROR);
			inputStream = new ByteArrayInputStream(result.toString().getBytes("utf-8"));
			return ERROR;
		}else {
			result.put("code", 200);
			result.put("msg", SUCCESS);
			Gson gson = new Gson();
			result.put("data", gson.toJson(admin));
			inputStream = new ByteArrayInputStream(result.toString().getBytes("utf-8"));
			return SUCCESS;
		}
	}
	
}
