package com.jlkj.kitchen.dao;

import java.util.List;

import com.jlkj.kitchen.bean.Answer;

public interface AnswerDao {
	public boolean addAnswer(Answer answer);
	public List<Answer> getAnswer(int questionid);
	public boolean deleteAnswer(int id);
	public boolean updateAnswer(Answer answer);
}
