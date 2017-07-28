package com.jlkj.kitchen.daoimp;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.jlkj.kitchen.bean.Answer;
import com.jlkj.kitchen.bean.Question;
import com.jlkj.kitchen.dao.AnswerDao;
import com.jlkj.kitchen.databasehelper.DataBaseHelper;

public class AnswerDaoImp implements AnswerDao {

	@Override
	public boolean addAnswer(Answer answer) {
		// TODO Auto-generated method stub
		Session session = DataBaseHelper.getSession();
		session.clear();
		Transaction transaction = session.beginTransaction();
		try {
			session.save(answer);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		transaction.commit();
		return true;
	}

	@Override
	public List<Answer> getAnswer(int questionid) {
		// TODO Auto-generated method stub
		Session session = DataBaseHelper.getSession();
		try{
			String sql = "select * from answer where questionid="+questionid+" order by id desc";
			//执行sql语句返回结果集
			Query q = session.createSQLQuery(sql);
			java.util.List list = (java.util.List) q.list();
			if(list.size() == 0){
				return null;
			}else{
				List<Answer> data = new ArrayList<>();
				for(int i=0;i<list.size();i++){
					Object[] obj = (Object[]) list.get(i);
					Answer b = new Answer();
					b.setId((Integer)obj[0]);
					b.setQuestionid((Integer)obj[1]);
					b.setSenderid((Integer)obj[2]);
					b.setAnswer((String)obj[3]);
					b.setPraise((Integer)obj[4]);
					b.setTime((String)obj[5]);
					data.add(b);
				}
				return data;
			}
		}catch(HibernateException e){
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean deleteAnswer(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateAnswer(Answer answer) {
		// TODO Auto-generated method stub
		Session session = DataBaseHelper.getSession();
		session.clear();
		Transaction transaction = session.beginTransaction();
		try {
			session.update(answer);
			transaction.commit();
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	public static void main(String[] args) {
		AnswerDaoImp dao = new AnswerDaoImp();
		System.out.println(dao.getAnswer(5).get(0).getAnswer());
	}

}
