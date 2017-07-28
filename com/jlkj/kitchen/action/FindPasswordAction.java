package com.jlkj.kitchen.action;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import com.jlkj.kitchen.bean.UserInf;
import com.jlkj.kitchen.dao.UserInfDao;
import com.jlkj.kitchen.util.SendMail;
import com.opensymphony.xwork2.ActionSupport;

import net.sf.json.JSONObject;
/**
 * 找回密码模块呢，分为三个步骤，第一步，验证验证吗，第二步，返回个人信息，需要客户端重新设置密码，第三部，得到用户信息
 * @author benrui
 *
 */
public class FindPasswordAction extends ActionSupport{
	private UserInf user;
	private InputStream inputStream;
	private int step;
	private UserInfDao dao;
	
	public UserInf getUser() {
		return user;
	}
	public void setUser(UserInf user) {
		this.user = user;
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
	public int getStep() {
		return step;
	}
	public void setStep(int step) {
		this.step = step;
	}
	public UserInfDao getDao() {
		return dao;
	}
	public void setDao(UserInfDao dao) {
		this.dao = dao;
	}
	public String findpassword() throws UnsupportedEncodingException {
		JSONObject result = new JSONObject();
		if(step == 1) {
			//第一步，验证邮箱是否可用
			if(!dao.isRegistered(user.getUsername())) {
				result.put("code", 200);
				result.put("msg", "success");
				inputStream = new ByteArrayInputStream(result.toString().getBytes("utf-8"));
				return SUCCESS;
			}else {
				result.put("code", 220);
				result.put("msg", "error");
				inputStream = new ByteArrayInputStream(result.toString().getBytes("utf-8"));
				return ERROR;
			}
		}else if(step == 2) {
			//验证验证吗
			SendMail mail = new SendMail();
			try {
				mail.send(user.getEmail());
				result.put("code", 200);
				result.put("msg", "success");
				JSONObject json = new JSONObject();
				json.put("verification", mail.getContent());
				result.put("data", json.toString());
				inputStream = new ByteArrayInputStream(result.toString().getBytes("utf-8"));
				return SUCCESS;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				result.put("code", 230);
				result.put("msg", "error");
				inputStream = new ByteArrayInputStream(result.toString().getBytes("utf-8"));
				return ERROR;
			}
		}else if(step == 3) {
			//得到用户信息
			UserInf user1 = dao.findpassword(user.getUsername());
			if(user1 != null) {
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
			}else {
				result.put("code", 250);
				result.put("msg", "error");
				inputStream = new ByteArrayInputStream(result.toString().getBytes("utf-8"));
				return ERROR;
			}
		}
		return SUCCESS;
	}
}
