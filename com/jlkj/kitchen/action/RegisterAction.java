package com.jlkj.kitchen.action;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import com.jlkj.kitchen.bean.UserInf;
import com.jlkj.kitchen.dao.UserInfDao;
import com.jlkj.kitchen.util.SendMail;
import com.opensymphony.xwork2.ActionSupport;

import net.sf.json.JSONObject;
/**
 * 对注册的操作，第一步是验证用户名是否被注册，第二步是获得验证码，第三步是正式注册
 * @author benrui
 *
 */
public class RegisterAction extends ActionSupport {
	private UserInf user;
	private InputStream inputStream;
	private UserInfDao dao;
	private int step;
	private String email;
	private File upload;
	
	
	public File getUpload() {
		return upload;
	}
	public void setUpload(File upload) {
		this.upload = upload;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getStep() {
		return step;
	}
	public void setStep(int step) {
		this.step = step;
	}
	public InputStream getResult() {
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
	public UserInfDao getDao() {
		return dao;
	}
	public void setDao(UserInfDao dao) {
		this.dao = dao;
	}
	public String register() throws UnsupportedEncodingException {
		JSONObject result = new JSONObject();
		if(step == 1) {
			//第一步，验证邮箱是否被注册
			if(!dao.isRegistered(email)) {
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
			//第二步，发送邮件
			SendMail mail = new SendMail();
			try {
				mail.send(email);
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
			//正式注册
			if(dao.register(user)) {
				result.put("code", 200);
				result.put("msg", "success");
				FileOutputStream fos;
				try {
					fos = new FileOutputStream("/Users/benrui/Documents/headimage/"+user.getEmail()+".png");
					FileInputStream fin = new FileInputStream(getUpload());
					byte[] buffer = new byte[1024];
					int len = 0;
					while((len = fin.read(buffer))>0){
						fos.write(buffer,0,len);
					}
					fos.close();
					fin.close();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				inputStream = new ByteArrayInputStream(result.toString().getBytes("utf-8"));
				return SUCCESS;
			}else {
				result.put("code", 240);
				result.put("msg", "error");
				inputStream = new ByteArrayInputStream(result.toString().getBytes("utf-8"));
				return ERROR;
			}
		}
		return SUCCESS;
	}
}
