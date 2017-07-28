package com.jlkj.kitchen.action;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

import com.jlkj.kitchen.bean.Answer;
import com.jlkj.kitchen.bean.Menu;
import com.jlkj.kitchen.dao.AnswerDao;
import com.opensymphony.xwork2.ActionSupport;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
/**
 * 得到回答，需要传入问题的id
 * 返回值是success或者error
 * 对应的返回码查阅返回码表
 * @author benrui
 *
 */
public class GetAnswerAction extends ActionSupport {
	private int id;
	private AnswerDao dao;
	private InputStream inputStream;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String get() throws UnsupportedEncodingException {
		JSONObject result = new JSONObject();
		List<Answer> answers = dao.getAnswer(id);
		if(answers == null) {
			result.put("code", 310);
			result.put("msg", "error");
			inputStream = new ByteArrayInputStream(result.toString().getBytes("utf-8"));
			return ERROR;
		}else {
			result.put("code", 200);
			result.put("msg", "success");
			JSONArray array = new JSONArray();
			for(Answer answer :answers) {
				JSONObject json = new JSONObject();
				json.put("id", answer.getId());
				json.put("questionid", answer.getQuestionid());
				json.put("senderid", answer.getSenderid());
				json.put("answer", answer.getAnswer());
				json.put("praise", answer.getPraise());
				json.put("time", answer.getTime());
				array.put(json);
			}
			result.put("data", array.toString());
			inputStream = new ByteArrayInputStream(result.toString().getBytes("utf-8"));
			return SUCCESS;
		}
	}
}
