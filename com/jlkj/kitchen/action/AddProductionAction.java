package com.jlkj.kitchen.action;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import com.jlkj.kitchen.bean.Production;
import com.jlkj.kitchen.dao.ProductionDao;
import com.opensymphony.xwork2.ActionSupport;

import net.sf.json.JSONObject;
/**
 * 发布自己的作品，需要传入作品的对象和作品的图片
 * 返回值是success或者error
 * 对应的返回码查阅返回码表
 * @author benrui
 *
 */
public class AddProductionAction extends ActionSupport{
	
	private Production production;
	private ProductionDao dao;
	private String key;
	private File img;
	private InputStream inputStream;
	
	public Production getProduction() {
		return production;
	}

	public void setProduction(Production production) {
		this.production = production;
	}

	public ProductionDao getDao() {
		return dao;
	}

	public void setDao(ProductionDao dao) {
		this.dao = dao;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public File getImg() {
		return img;
	}

	public void setImg(File img) {
		this.img = img;
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

	public String add() throws UnsupportedEncodingException {
		JSONObject result = new JSONObject();
		if(dao.addProduction(production)) {
			result.put("code", 200);
			result.put("msg", "success");
			FileOutputStream fos;
			try {
				fos = new FileOutputStream("/Users/benrui/Documents/production/"+production.getSenderid()+"_"+key+".png");
				FileInputStream fin = new FileInputStream(getImg());
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
			result.put("code", 350);
			result.put("msg", "error");
			inputStream = new ByteArrayInputStream(result.toString().getBytes("utf-8"));
			return ERROR;
		}
	}
	
}
