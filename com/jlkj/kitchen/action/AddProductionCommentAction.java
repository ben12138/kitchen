package com.jlkj.kitchen.action;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.PipedInputStream;
import java.io.UnsupportedEncodingException;

import com.jlkj.kitchen.bean.ProductionComment;
import com.jlkj.kitchen.dao.ProductionCommentDao;
import com.opensymphony.xwork2.ActionSupport;

import net.sf.json.JSONObject;
/**
 * 给作品添加评论，需要传入作品评论对象，除了id以外
 * 返回值是success或者error
 * 对应的返回码查阅返回码表
 * @author benrui
 *
 */
public class AddProductionCommentAction extends ActionSupport{
	
	private ProductionCommentDao dao;
	private InputStream inputStream;
	private ProductionComment comment;
	
	public ProductionComment getComment() {
		return comment;
	}

	public void setComment(ProductionComment comment) {
		this.comment = comment;
	}

	public ProductionCommentDao getDao() {
		return dao;
	}

	public void setDao(ProductionCommentDao dao) {
		this.dao = dao;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public String add() throws UnsupportedEncodingException {
		JSONObject result = new JSONObject();
		if(dao.addComment(comment)) {
			result.put("code", 200);
			result.put("msg", SUCCESS);
			inputStream = new ByteArrayInputStream(result.toString().getBytes("utf-8"));
			return SUCCESS;
		}else {
			result.put("code", 320);
			result.put("msg", ERROR);
			inputStream = new ByteArrayInputStream(result.toString().getBytes("utf-8"));
			return ERROR;
		}
	}
	
}
