package com.jlkj.kitchen.action;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.jlkj.kitchen.bean.Collection;
import com.jlkj.kitchen.bean.Menu;
import com.jlkj.kitchen.dao.IsCollectedDao;
import com.jlkj.kitchen.dao.MenuDao;
import com.jlkj.kitchen.daoimp.MenuDaoImp;
import com.opensymphony.xwork2.ActionSupport;

import net.sf.json.JSONObject;
/**
 * 得到收藏的menu信息，传入userid
 * 返回值是success或者error
 * 对应的返回码查阅返回码表
 * @author benrui
 *
 */
public class GetCollectionAction extends ActionSupport{
	
	private int userid;
	private InputStream inputStream;
	private IsCollectedDao dao;
	
	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public IsCollectedDao getDao() {
		return dao;
	}

	public void setDao(IsCollectedDao dao) {
		this.dao = dao;
	}

	public String get() throws UnsupportedEncodingException {
		JSONObject result = new JSONObject();
		List<Collection> collections = dao.getCollections(userid);
		if(collections == null) {
			result.put("code", 400);
			result.put("msg", ERROR);
			inputStream = new ByteArrayInputStream(result.toString().getBytes("utf-8"));
			return ERROR;
		}else {
			result.put("code", 200);
			result.put("msg", SUCCESS);
			Gson gson = new Gson();
			MenuDao dao = new MenuDaoImp();
			List<Menu> menus = new ArrayList<>();
			for(Collection collection:collections) {
				menus.add(dao.getMenu(collection.getMenuid(), 1));
			}
			result.put("data", gson.toJson(menus).toString());
			inputStream = new ByteArrayInputStream(result.toString().getBytes("utf-8"));
			return SUCCESS;
		}
	}
}
