package com.jlkj.kitchen.action;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import com.jlkj.kitchen.bean.Collection;
import com.jlkj.kitchen.dao.IsCollectedDao;
import com.opensymphony.xwork2.ActionSupport;

import net.sf.json.JSONObject;
/**
 * 收藏menu，需要传入menuid和userid
 * 返回值是success或者error
 * 对应的返回码查阅返回码表
 * @author benrui
 *
 */
public class CollectAction extends ActionSupport{
	
	private IsCollectedDao dao;
	private Collection collection;
	private InputStream inputStream;
	public IsCollectedDao getDao() {
		return dao;
	}
	public void setDao(IsCollectedDao dao) {
		this.dao = dao;
	}
	public Collection getCollection() {
		return collection;
	}
	public void setCollection(Collection collection) {
		this.collection = collection;
	}
	public InputStream getInputStream() {
		return inputStream;
	}
	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}
	
	public String collect() throws UnsupportedEncodingException {
		JSONObject result = new JSONObject();
		if(dao.collect(collection)) {
			result.put("code", 200);
			result.put("msg", SUCCESS);
			inputStream = new ByteArrayInputStream(result.toString().getBytes("utf-8"));
			return SUCCESS;
		}else {
			result.put("code", 380);
			result.put("msg", ERROR);
			inputStream = new ByteArrayInputStream(result.toString().getBytes("utf-8"));
			return ERROR;
		}
	}

}
