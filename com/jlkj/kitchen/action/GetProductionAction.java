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
 * 得到所有作品信息，不需要传参数
 * 返回值是success或者error
 * 对应的返回码查阅返回码表
 * @author benrui
 *
 */
public class GetProductionAction extends ActionSupport{
	
	private InputStream inputStream;
	private ProductionDao dao;
	public InputStream getInputStream() {
		return inputStream;
	}
	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}
	public ProductionDao getDao() {
		return dao;
	}
	public void setDao(ProductionDao dao) {
		this.dao = dao;
	}
	
	public String get() throws UnsupportedEncodingException {
		JSONObject result = new JSONObject();
		List<Production> productions = dao.getProduction();
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
