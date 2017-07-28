package com.jlkj.kitchen.action;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import com.jlkj.kitchen.bean.ProductionComment;
import com.jlkj.kitchen.dao.ProductionCommentDao;
import com.jlkj.kitchen.dao.ProductionDao;
import com.opensymphony.xwork2.ActionSupport;

import net.sf.json.JSONObject;
/**
 * 更新production评论信息，传入评论对象
 * 返回值是success或者error
 * 对应的返回码查阅返回码表
 * @author benrui
 *
 */
public class UpdateProductionCommentAction extends ActionSupport{
	
	private ProductionComment comment;
	private ProductionCommentDao dao;
	private InputStream inputStream;
	
	public ProductionCommentDao getDao() {
		return dao;
	}

	public void setDao(ProductionCommentDao dao) {
		this.dao = dao;
	}

	public ProductionComment getComment() {
		return comment;
	}

	public void setComment(ProductionComment comment) {
		this.comment = comment;
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
		if(dao.update(comment)) {
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
