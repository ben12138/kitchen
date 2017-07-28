package com.jlkj.kitchen.action;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import com.jlkj.kitchen.bean.Menu;
import com.jlkj.kitchen.dao.MenuDao;
import com.opensymphony.xwork2.ActionSupport;

import net.sf.json.JSONObject;
/**
 * 发布菜谱，需要传入menu对象，菜谱的封面，以及做菜的步骤
 * 返回值是success或者error
 * 对应的返回码查阅返回码表
 * @author benrui
 *
 */
public class AddMenuAction extends ActionSupport{
	private Menu menu;
	private File cover;
	private File pic1;
	private File pic2;
	private File pic3;
	private String key;
	private InputStream inputStream;
	private MenuDao dao;
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public File getCover() {
		return cover;
	}
	public void setCover(File cover) {
		this.cover = cover;
	}
	public File getPic1() {
		return pic1;
	}
	public void setPic1(File pic1) {
		this.pic1 = pic1;
	}
	public File getPic2() {
		return pic2;
	}
	public void setPic2(File pic2) {
		this.pic2 = pic2;
	}
	public File getPic3() {
		return pic3;
	}
	public void setPic3(File pic3) {
		this.pic3 = pic3;
	}
	public Menu getMenu() {
		return menu;
	}
	public void setMenu(Menu menu) {
		this.menu = menu;
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
	public MenuDao getDao() {
		return dao;
	}
	public void setDao(MenuDao dao) {
		this.dao = dao;
	}
	public String add() throws UnsupportedEncodingException {
		JSONObject result = new JSONObject();
		if(dao.addMenu(menu)) {
			result.put("code", 200);
			result.put("msg", "success");
			if(getCover()!=null) {
				saveCover();
			}
			if(getPic1()!=null) {
				savePic1();
			}
			if(getPic2()!=null) {
				savePic2();
			}
			if(getPic3()!=null) {
				savePic3();
			}
			inputStream = new ByteArrayInputStream(result.toString().getBytes("utf-8"));
			return SUCCESS;	
		}else {
			result.put("code", 340);
			result.put("msg", "error");
			inputStream = new ByteArrayInputStream(result.toString().getBytes("utf-8"));
			return ERROR;
		}
	}
	
	private void saveCover() {
		FileOutputStream fos;
		try {
			fos = new FileOutputStream("/Users/benrui/Documents/cover/"+menu.getSenderid()+"_"+key+".png");
			FileInputStream fin = new FileInputStream(getCover());
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
	}
	
	private void savePic1() {
		FileOutputStream fos;
		try {
			fos = new FileOutputStream("/Users/benrui/Documents/step/"+menu.getSenderid()+"_step1_"+key+".png");
			FileInputStream fin = new FileInputStream(getPic1());
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
	}
	
	private void savePic2() {
		FileOutputStream fos;
		try {
			fos = new FileOutputStream("/Users/benrui/Documents/step/"+menu.getSenderid()+"_step2_"+key+".png");
			FileInputStream fin = new FileInputStream(getPic2());
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
	}
	
	private void savePic3() {
		FileOutputStream fos;
		try {
			fos = new FileOutputStream("/Users/benrui/Documents/step/"+menu.getSenderid()+"_step3_"+key+".png");
			FileInputStream fin = new FileInputStream(getPic3());
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
	}
	
}
