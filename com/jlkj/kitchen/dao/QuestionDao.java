package com.jlkj.kitchen.dao;

import java.util.List;

import com.jlkj.kitchen.bean.Question;

public interface QuestionDao {
	public boolean addQuestion(Question question);
	public boolean deleteQuestion(int id);
	public boolean updateQuestion(Question question);
	public List<Question> getQuestion();
	public List<Question> searchQuestion(String key);
}
