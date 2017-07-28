package com.jlkj.kitchen.action;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import com.jlkj.kitchen.bean.UserInf;
import com.jlkj.kitchen.dao.UserInfDao;
import com.opensymphony.xwork2.ActionSupport;

import net.sf.json.JSONObject;
/**
 * 得到用户信息，传入用户id
 * 返回值是success或者error
 * 对应的返回码查阅返回码表
 * @author benrui
 *
 */
public class GetUserInfAction extends ActionSupport{
	private int id;
	private InputStream inputStream;
	private UserInfDao dao;
	
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

	public UserInfDao getDao() {
		return dao;
	}

	public void setDao(UserInfDao dao) {
		this.dao = dao;
	}

	public String get() throws UnsupportedEncodingException {
		JSONObject result = new JSONObject();
		UserInf user = dao.getUserInfExceptpassword(id);
		if(user == null) {
			result.put("code", 300);
			result.put("msg", "error");
			inputStream = new ByteArrayInputStream(result.toString().getBytes("utf-8"));
			return ERROR;
		}else {
			result.put("code", 200);
			result.put("msg", "success");
			JSONObject userjson = new JSONObject();
			userjson.put("id", user.getId());
			userjson.put("username", user.getUsername());
			userjson.put("password", user.getPassword());
			userjson.put("nickname", user.getNickname());
			userjson.put("birthday", user.getBirthday());
			userjson.put("introduction", user.getIntroduction());
			userjson.put("imgurl", user.getImgurl());
			userjson.put("sex", user.getSex());
			userjson.put("company", user.getCompany());
			userjson.put("email", user.getEmail());
			result.put("data", userjson.toString());
			inputStream = new ByteArrayInputStream(result.toString().getBytes("utf-8"));
			return SUCCESS;
		}
	}
	
}
