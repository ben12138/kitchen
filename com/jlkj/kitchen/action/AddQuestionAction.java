package com.jlkj.kitchen.action;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import com.jlkj.kitchen.bean.Question;
import com.jlkj.kitchen.dao.QuestionDao;
import com.opensymphony.xwork2.ActionSupport;

import net.sf.json.JSONObject;
/**
 * 添加问题，需要传入问题对象
 * 返回值是success或者error
 * 对应的返回码查阅返回码表
 * @author benrui
 *
 */
public class AddQuestionAction extends ActionSupport{
	private Question question;
	private QuestionDao dao;
	private InputStream inputStream;
	
	public InputStream getResult() {
		return inputStream;
	}
	
	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public QuestionDao getDao() {
		return dao;
	}

	public void setDao(QuestionDao dao) {
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
		if(dao.addQuestion(question)) {
			result.put("code", 200);
			result.put("msg", "success");
			inputStream = new ByteArrayInputStream(result.toString().getBytes("utf-8"));
			return SUCCESS;
		}else {
			result.put("code", 280);
			result.put("msg", "error");
			inputStream = new ByteArrayInputStream(result.toString().getBytes("utf-8"));
			return ERROR;
		}
	}
}
