package com.jlkj.kitchen.action;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

import com.jlkj.kitchen.bean.Question;
import com.jlkj.kitchen.dao.QuestionDao;
import com.opensymphony.xwork2.ActionSupport;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
/**
 * 搜索问题，需要传入搜索的key
 * 返回值是success或者error
 * 对应的返回码查阅返回码表
 * @author benrui
 *
 */
public class SearchQuestionAction extends ActionSupport{
	
	private InputStream inputStream;
	private QuestionDao dao;
	private String key;
	
	public InputStream getResult() {
		return inputStream;
	}
	
	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public QuestionDao getDao() {
		return dao;
	}

	public void setDao(QuestionDao dao) {
		this.dao = dao;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String search() throws UnsupportedEncodingException {
		JSONObject result = new JSONObject();
		List<Question> questions = dao.searchQuestion(key);
		if(questions == null) {
			result.put("code", 290);
			result.put("msg", "error");
			inputStream = new ByteArrayInputStream(result.toString().getBytes("utf-8"));
			return ERROR;
		}else {
			result.put("code", 200);
			result.put("msg", "success");
			JSONArray array = new JSONArray();
			for(Question temp:questions) {
				JSONObject json = new JSONObject();
				json.put("id", temp.getId());
				json.put("senderid", temp.getSenderid());
				json.put("question", temp.getQuestion());
				json.put("time", temp.getTime());
				array.put(json);
			}
			result.put("data", array.toString());
			inputStream = new ByteArrayInputStream(result.toString().getBytes("utf-8"));
			return SUCCESS;
		}
	}

}
