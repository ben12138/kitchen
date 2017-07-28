package com.jlkj.kitchen.daoimp;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.jlkj.kitchen.bean.CourseComment;
import com.jlkj.kitchen.bean.CourseVideo;
import com.jlkj.kitchen.dao.CourseCommentDao;
import com.jlkj.kitchen.databasehelper.DataBaseHelper;

public class CourseCommentDaoImp implements CourseCommentDao {

	@Override
	public boolean addCourseComment(CourseComment comment) {
		// TODO Auto-generated method stub
		Session session = DataBaseHelper.getSession();
		session.clear();
		Transaction transaction = session.beginTransaction();
		try {
			session.save(comment);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		transaction.commit();
		return true;
	}

	@Override
	public boolean deleteCourseComment(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<CourseComment> getCourseComment(int courseid) {
		// TODO Auto-generated method stub
		Session session = DataBaseHelper.getSession();
		session.clear();
		List list = session.createSQLQuery("select * from coursecomment where courseid="+courseid+" order by id desc").list();
		List<CourseComment> comments;
		if (list.size() == 0) {
			return null;
		} else {
			comments = new ArrayList<>();
			for (int i = 0; i < list.size(); i++) {
				Object[] obj = (Object[]) list.get(i);
				CourseComment comment = new CourseComment();
				comment.setId((Integer)obj[0]);
				comment.setCourseid((Integer)obj[1]);
				comment.setSenderid((Integer)obj[2]);
				comment.setComment((String)obj[3]);
				comment.setPraise((Integer)obj[4]);
				comment.setTime((String)obj[5]);
				comments.add(comment);
			}
			return comments;
		}
	}

	@Override
	public boolean updateCourseComment(CourseComment comment) {
		// TODO Auto-generated method stub
		Session session = DataBaseHelper.getSession();
		session.clear();
		Transaction transaction = session.beginTransaction();
		try {
			session.update(comment);
			transaction.commit();
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

}
