package com.jlkj.kitchen.action;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import com.jlkj.kitchen.bean.Answer;
import com.jlkj.kitchen.dao.AnswerDao;
import com.opensymphony.xwork2.ActionSupport;

import net.sf.json.JSONObject;
/**
 * 给问题添加回答，需要传入除了id以外的answer的值
 * 返回值是success或者error
 * 对应的返回码查阅返回码表
 * @author benrui
 *
 */
public class AddAnswerAction extends ActionSupport{
	
	private Answer answer;
	private AnswerDao dao;
	private InputStream inputStream;
	
	public Answer getAnswer() {
		return answer;
	}

	public void setAnswer(Answer answer) {
		this.answer = answer;
	}

	public AnswerDao getDao() {
		return dao;
	}

	public void setDao(AnswerDao dao) {
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

	public String add() throws UnsupportedEncodingException {
		JSONObject result = new JSONObject();
		if(dao.addAnswer(answer)) {
			result.put("code", 200);
			result.put("msg", "success");
			inputStream = new ByteArrayInputStream(result.toString().getBytes("utf-8"));
			return ERROR;
		}else {
			result.put("code", 320);
			result.put("msg", "error");
			inputStream = new ByteArrayInputStream(result.toString().getBytes("utf-8"));
			return SUCCESS;
		}
	}

}
