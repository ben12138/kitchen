package com.jlkj.kitchen.daoimp;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.google.gson.Gson;
import com.jlkj.kitchen.bean.Question;
import com.jlkj.kitchen.dao.QuestionDao;
import com.jlkj.kitchen.databasehelper.DataBaseHelper;

public class QuestionDaoImp implements QuestionDao {

	@Override
	public boolean addQuestion(Question question) {
		// TODO Auto-generated method stub
		Session session = DataBaseHelper.getSession();
		session.clear();
		Transaction transaction = session.beginTransaction();
		try {
			session.save(question);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		transaction.commit();
		return true;
	}

	@Override
	public boolean deleteQuestion(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateQuestion(Question question) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Question> getQuestion() {
		// TODO Auto-generated method stub
		Session session = DataBaseHelper.getSession();
		try{
			String sql = "select * from question order by id desc";
			//执行sql语句返回结果集
			Query q = session.createSQLQuery(sql);
			java.util.List list = (java.util.List) q.list();
			if(list.size() == 0){
				return null;
			}else{
				List<Question> data = new ArrayList<>();
				for(int i=0;i<list.size();i++){
					Object[] obj = (Object[]) list.get(i);
					Question b = new Question();
					b.setId((Integer)obj[0]);
					b.setSenderid((Integer)obj[1]);
					b.setQuestion((String)obj[2]);
					b.setTime((String)obj[3]);
					data.add(b);
				}
				return data;
			}
		}catch(HibernateException e){
			e.printStackTrace();
			return null;
		}
	}
	
	public static void main(String[] args) {
		List<Question> questions = new QuestionDaoImp().searchQuestion("怎么");
		for(Question temp: questions) {
			Gson gson = new Gson();
			String json = gson.toJson(temp);
			System.out.println(json);
		}
	}

	@Override
	public List<Question> searchQuestion(String key) {
		// TODO Auto-generated method stub
		Session session = DataBaseHelper.getSession();
		try{
			String sql = "select * from question where question like '%"+key+"%'";
			//执行sql语句返回结果集
			Query q = session.createSQLQuery(sql);
			java.util.List list = (java.util.List) q.list();
			if(list.size() == 0){
				return null;
			}else{
				List<Question> data = new ArrayList<>();
				for(int i=0;i<list.size();i++){
					Object[] obj = (Object[]) list.get(i);
					Question b = new Question();
					b.setId((Integer)obj[0]);
					b.setSenderid((Integer)obj[1]);
					b.setQuestion((String)obj[2]);
					b.setTime((String)obj[3]);
					data.add(b);
				}
				return data;
			}
		}catch(HibernateException e){
			e.printStackTrace();
			return null;
		}
	}

}
