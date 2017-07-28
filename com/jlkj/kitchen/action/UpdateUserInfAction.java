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
import com.opensymphony.xwork2.ActionSupport;

import net.sf.json.JSONObject;
/**
 * 更新个人信息，传入userinf对象
 * 返回值是success或者error
 * 对应的返回码查阅返回码表
 * @author benrui
 *
 */
public class UpdateUserInfAction extends ActionSupport{
	private UserInf user;
	private InputStream inputStream;
	private UserInfDao dao;
	
	private int step;
	
	private File headimage;
	
	public int getStep() {
		return step;
	}
	public void setStep(int step) {
		this.step = step;
	}
	public File getHeadimage() {
		return headimage;
	}
	public void setHeadimage(File headimage) {
		this.headimage = headimage;
	}
	public UserInfDao getDao() {
		return dao;
	}
	public void setDao(UserInfDao dao) {
		this.dao = dao;
	}
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
	public String update() throws UnsupportedEncodingException {
		JSONObject result = new JSONObject();
		System.out.println("update");
		if(step == 1) {
			FileOutputStream fos;
			try {
				fos = new FileOutputStream("/Users/benrui/Documents/headimage/"+user.getEmail()+".png");
				FileInputStream fin = new FileInputStream(getHeadimage());
				byte[] buffer = new byte[1024];
				int len = 0;
				while((len = fin.read(buffer))>0){
					fos.write(buffer,0,len);
				}
				fos.close();
				fin.close();
				result.put("code", 200);
				result.put("msg", "success");
				System.out.println(result.toString());
				inputStream = new ByteArrayInputStream(result.toString().getBytes("utf-8"));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				result.put("code", 250);
				result.put("msg", "error");
				System.out.println(result.toString());
				inputStream = new ByteArrayInputStream(result.toString().getBytes("utf-8"));
				return ERROR;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				result.put("code", 250);
				result.put("msg", "error");
				System.out.println(result.toString());
				inputStream = new ByteArrayInputStream(result.toString().getBytes("utf-8"));
				return ERROR;
			}
			return SUCCESS;
		}else if(step == 2) {
			if(dao.updateUserInf(user)) {
				result.put("code", 200);
				result.put("msg", "success");
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
