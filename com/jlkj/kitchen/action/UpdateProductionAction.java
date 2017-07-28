package com.jlkj.kitchen.action;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import com.jlkj.kitchen.bean.Production;
import com.jlkj.kitchen.dao.ProductionDao;
import com.opensymphony.xwork2.ActionSupport;

import net.sf.json.JSONObject;
/**
 * 更新production信息，需要传入production对象
 * 返回值是success或者error
 * 对应的返回码查阅返回码表
 * @author benrui
 *
 */
public class UpdateProductionAction extends ActionSupport{
	
	private Production production;
	private ProductionDao dao;
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

	public InputStream getResult() {
		return inputStream;
	}
	
	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public String update() throws UnsupportedEncodingException {
		JSONObject result = new JSONObject();
		if(dao.updateProduction(production)) {
			result.put("code", 200);
			result.put("msg", "success");
			inputStream = new ByteArrayInputStream(result.toString().getBytes("utf-8"));
			return SUCCESS;
		}else {
			result.put("code", 330);
			result.put("msg", "error");
			inputStream = new ByteArrayInputStream(result.toString().getBytes("utf-8"));
			return ERROR;
		}
	}
}
