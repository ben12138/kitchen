package com.jlkj.kitchen.daoimp;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.jlkj.kitchen.bean.Menu;
import com.jlkj.kitchen.bean.MyCourse;
import com.jlkj.kitchen.dao.MyCourseDao;
import com.jlkj.kitchen.databasehelper.DataBaseHelper;

public class MyCourseDaoImp implements MyCourseDao{

	@Override
	public boolean hasCourse(int courseid, int userid) {
		// TODO Auto-generated method stub
		Session session = DataBaseHelper.getSession();
		session.clear();
		List list = session.createSQLQuery("select * from mycourse where courseid="+courseid+" and userid="+userid).list();
		if(list.size() == 0){
			return false;
		}else {
			return true;
		}
	}

	@Override
	public boolean addCourse(MyCourse myCourse) {
		// TODO Auto-generated method stub
		Session session = DataBaseHelper.getSession();
		session.clear();
		Transaction transaction = session.beginTransaction();
		try {
			session.save(myCourse);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		transaction.commit();
		return true;
	}

	@Override
	public boolean deleteCourse(int courseid, int userid) {
		// TODO Auto-generated method stub
		Session session = DataBaseHelper.getSession();
		session.clear();
		Transaction transaction = session.beginTransaction();
		int result = session.createSQLQuery("delete from mycourse where courseid="+courseid+" and userid="+userid).executeUpdate();
		transaction.commit();
		if(result == 0){
			return false;
		}else {
			return true;
		}
	}

	@Override
	public List<MyCourse> getMyCourse(int userid) {
		// TODO Auto-generated method stub
		Session session = DataBaseHelper.getSession();
		session.clear();
		List list = session.createSQLQuery("select * from mycourse where userid="+userid).list();
		List<MyCourse> mycourses;
		if (list.size() == 0) {
			return null;
		} else {
			mycourses = new ArrayList<>();
			for (int i = 0; i < list.size(); i++) {
				Object[] obj = (Object[]) list.get(i);
				MyCourse course = new MyCourse();
				course.setId((Integer)obj[0]);
				course.setCourseid((Integer)obj[1]);
				course.setUserid((Integer)obj[2]);
				mycourses.add(course);
			}
			return mycourses;
		}
	}
	
}
