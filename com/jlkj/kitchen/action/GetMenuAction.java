package com.jlkj.kitchen.action;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

import com.jlkj.kitchen.bean.Menu;
import com.jlkj.kitchen.dao.MenuDao;
import com.opensymphony.xwork2.ActionSupport;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
/**
 * 得到所有的menu信息，不需要传参数
 * 返回值是success或者error
 * 对应的返回码查阅返回码表
 * @author benrui
 *
 */
public class GetMenuAction extends ActionSupport{
	private MenuDao dao;
	private InputStream inputStream;
	public MenuDao getDao() {
		return dao;
	}
	public void setDao(MenuDao dao) {
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
	public String get() throws UnsupportedEncodingException {
		List<Menu> menus = dao.getMenu();
		JSONObject result = new JSONObject();
		if(result == null) {
			result.put("code", 260);
			result.put("msg", "error");
			inputStream = new ByteArrayInputStream(result.toString().getBytes("utf-8"));
			return ERROR;
		}else {
			result.put("code", 200);
			result.put("msg", "success");
			JSONArray array = new JSONArray();
			for(Menu menu :menus) {
				JSONObject json = new JSONObject();
				json.put("id", menu.getId());
				json.put("title", menu.getTitle());
				json.put("senderid", menu.getSenderid());
				json.put("cover", menu.getCover());
				json.put("story", menu.getStory());
				json.put("food", menu.getFood());
				json.put("category1", menu.getCategory1());
				json.put("category2", menu.getCategory2());
				json.put("category3", menu.getCategory3());
				json.put("step1", menu.getStep1());
				json.put("step2", menu.getStep2());
				json.put("step3", menu.getStep3());
				json.put("pic1", menu.getPic1());
				json.put("pic2", menu.getPic2());
				json.put("pic3", menu.getPic3());
				json.put("time", menu.getTime());
				array.put(json);
			}
			result.put("data", array.toString());
			inputStream = new ByteArrayInputStream(result.toString().getBytes("utf-8"));
			return SUCCESS;
		}
	}
}
