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
 * 搜索菜谱，可以通过分类搜索，也可以通过关键词搜索，关键词可以输入标题关键词，背后故事关键词和食材关键词
 * @author benrui
 *
 */
public class SearchMenuAction extends ActionSupport{
	private InputStream inputStream;
	private int category1;
	private int category2;
	private int category3;
	private String key;
	private MenuDao dao;
	private int type;//可以传值，1代表通过关键词进行搜索，2代表通过分类进行搜索
	
	public InputStream getResult() {
		return inputStream;
	}
	
	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

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

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public MenuDao getDao() {
		return dao;
	}

	public void setDao(MenuDao dao) {
		this.dao = dao;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String search() throws UnsupportedEncodingException {
		JSONObject result = new JSONObject();
		List<Menu> menus = null;
		if(type == 1) {
			//通过输入的关键词进行查找
			menus = dao.searchMenu(key);
		}else if(type == 2) {
			//通过分类进行查找
			menus = dao.searchMenu(category1,category2,category3);
		}
		if(menus == null) {
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
