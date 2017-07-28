package com.jlkj.kitchen.action;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

import com.google.gson.Gson;
import com.jlkj.kitchen.bean.ProductionComment;
import com.jlkj.kitchen.dao.ProductionCommentDao;
import com.opensymphony.xwork2.ActionSupport;

import net.sf.json.JSONObject;
/**
 * 得到作品评论信息，需要传入productionid
 * 返回值是success或者error
 * 对应的返回码查阅返回码表
 * @author benrui
 *
 */
public class GetProductionCommentAction extends ActionSupport{
	
	private ProductionCommentDao dao;
	private int productionid;
	private InputStream inputStream;
	
	public ProductionCommentDao getDao() {
		return dao;
	}

	public void setDao(ProductionCommentDao dao) {
		this.dao = dao;
	}

	public int getProductionid() {
		return productionid;
	}

	public void setProductionid(int productionid) {
		this.productionid = productionid;
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
		List<ProductionComment> comments = dao.getProductionComment(productionid);
		if(comments == null) {
			result.put("code", 310);
			result.put("msg", ERROR);
			inputStream = new ByteArrayInputStream(result.toString().getBytes("utf-8"));
			return ERROR;
		}else {
			result.put("code", 200);
			result.put("msg", SUCCESS);
			Gson gson = new Gson();
			result.put("data", gson.toJson(comments));
			inputStream = new ByteArrayInputStream(result.toString().getBytes("utf-8"));
			return SUCCESS;
		}
		
	}
	
}
