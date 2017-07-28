package com.jlkj.kitchen.action;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import com.jlkj.kitchen.bean.UserInf;
import com.jlkj.kitchen.dao.UserInfDao;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import net.sf.json.JSONObject;
/**
 * 登录Action，需要传入用户名和密码
 * @author benrui
 *
 */
public class LoginAction extends ActionSupport{
	private UserInf user;
	private InputStream inputStream;
	private UserInfDao dao;
	public InputStream getResult(){
		return inputStream;
	}
	public UserInf getUser() {
		return user;
	}
	public void setUser(UserInf user) {
		this.user = user;
	}
	public InputStream getInputStream() {
		return inputStream;
	}
	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}
	public void setDao(UserInfDao dao) {
		this.dao = dao;
	}
	
	public String login() throws UnsupportedEncodingException {
		UserInf user1 = dao.login(user.getUsername(), user.getPassword());
		JSONObject result = new JSONObject();
		if(user1 == null) {
			result.put("code", 210);
			result.put("msg", "error");
			inputStream = new ByteArrayInputStream(result.toString().getBytes("utf-8"));
			return ERROR;
		}else {
			result.put("code", 200);
			result.put("msg", "success");
			JSONObject userjson = new JSONObject();
			userjson.put("id", user1.getId());
			userjson.put("username", user1.getUsername());
			userjson.put("password", user1.getPassword());
			userjson.put("nickname", user1.getNickname());
			userjson.put("birthday", user1.getBirthday());
			userjson.put("introduction", user1.getIntroduction());
			userjson.put("imgurl", user1.getImgurl());
			userjson.put("sex", user1.getSex());
			userjson.put("company", user1.getCompany());
			userjson.put("email", user1.getEmail());
			result.put("data", userjson.toString());
			inputStream = new ByteArrayInputStream(result.toString().getBytes("utf-8"));
			return SUCCESS;
		}
	}
	
	public String register() {
		System.out.println("aaa");
		return "success";
	}
	
}
