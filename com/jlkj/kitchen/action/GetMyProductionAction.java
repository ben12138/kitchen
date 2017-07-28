package com.jlkj.kitchen.action;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

import com.google.gson.Gson;
import com.jlkj.kitchen.bean.Production;
import com.jlkj.kitchen.dao.ProductionDao;
import com.opensymphony.xwork2.ActionSupport;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
/**
 * 得到我的作品的信息，需要userid
 * 返回值是success或者error
 * 对应的返回码查阅返回码表
 * @author benrui
 *
 */
public class GetMyProductionAction extends ActionSupport {
	
	private int senderid;
	private ProductionDao dao;
	private InputStream inputStream;
	
	public int getSenderid() {
		return senderid;
	}

	public void setSenderid(int senderid) {
		this.senderid = senderid;
	}

	public ProductionDao getDao() {
		return dao;
	}

	public void setDao(ProductionDao dao) {
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
		JSONObject result = new JSONObject();
		List<Production> productions = dao.getProduction(senderid);
		if(productions == null) {
			result.put("code", 260);
			result.put("msg", "error");
			inputStream = new ByteArrayInputStream(result.toString().getBytes("utf-8"));
			return ERROR;
		}else {
			result.put("code", 200);
			result.put("msg", "success");
			JSONArray array = new JSONArray();
			for(Production production:productions) {
				Gson gson = new Gson();
				String json = gson.toJson(production);
				array.put(json);
			}
			result.put("data", array.toString());
			inputStream = new ByteArrayInputStream(result.toString().getBytes("utf-8"));
			return SUCCESS;
		}
	}
	
}
