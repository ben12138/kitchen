package com.jlkj.kitchen.action;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

import com.google.gson.Gson;
import com.jlkj.kitchen.bean.Course;
import com.jlkj.kitchen.bean.Menu;
import com.jlkj.kitchen.dao.CourseDao;
import com.jlkj.kitchen.dao.MenuDao;
import com.opensymphony.xwork2.ActionSupport;

import net.sf.json.JSONObject;
/**
 * 分类搜索，得到相应类别的menu信息
 * 返回值是success或者error
 * 对应的返回码查阅返回码表
 * @author benrui
 *
 */
public class GetCategoryMenuAction extends ActionSupport{
	
	private int category1;
	private int category2;
	private int category3;
	private MenuDao dao;
	private InputStream inputStream;
	
	public int getCategory1() {
		return category1;
	}

	public void setCategory1(int category1) {
		this.category1 = category1;
	}

	public int getCategory2() {
		return category2;
	}

	public void setCategory2(int category2) {
		this.category2 = category2;
	}

	public int getCategory3() {
		return category3;
	}

	public void setCategory3(int category3) {
		this.category3 = category3;
	}

	public MenuDao getDao() {
		return dao;
	}

	public void setDao(MenuDao dao) {
		this.dao = dao;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public String get() throws UnsupportedEncodingException {
		JSONObject result = new JSONObject();
		List<Menu> menus = dao.searchMenu(category1, category2, category3);
		if(menus == null) {
			result.put("code", 470);
			result.put("msg", ERROR);
			inputStream = new ByteArrayInputStream(result.toString().getBytes("utf-8"));
			return ERROR;
		}else {
			result.put("code", 200);
			result.put("msg", SUCCESS);
			Gson gson = new Gson();
			result.put("data", gson.toJson(menus));
			inputStream = new ByteArrayInputStream(result.toString().getBytes("utf-8"));
			return SUCCESS;
		}
	}
	
}
