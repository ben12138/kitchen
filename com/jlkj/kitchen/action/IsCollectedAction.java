package com.jlkj.kitchen.action;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import com.jlkj.kitchen.bean.Collection;
import com.jlkj.kitchen.dao.IsCollectedDao;
import com.opensymphony.xwork2.ActionSupport;

import net.sf.json.JSONObject;
/**
 * 判断是否收藏需要传入menuid和userid
 * 返回值是success或者error
 * 对应的返回码查阅返回码表
 * @author benrui
 *
 */
public class IsCollectedAction extends ActionSupport {
	
	private Collection collection;
	private IsCollectedDao dao;
	private InputStream inputStream;
	
	public Collection getCollection() {
		return collection;
	}

	public void setCollection(Collection collection) {
		this.collection = collection;
	}

	public IsCollectedDao getDao() {
		return dao;
	}

	public void setDao(IsCollectedDao dao) {
		this.dao = dao;
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

	public String check() throws UnsupportedEncodingException {
		JSONObject result = new JSONObject();
		if(dao.isCollected(collection.getMenuid(), collection.getUserid())) {
			result.put("code", 200);
			result.put("msg", SUCCESS);
			inputStream = new ByteArrayInputStream(result.toString().getBytes("utf-8"));
			return SUCCESS;
		}else {
			result.put("code", 370);
			result.put("msg", ERROR);
			inputStream = new ByteArrayInputStream(result.toString().getBytes("utf-8"));
			return ERROR;
		}
	}
	
}
