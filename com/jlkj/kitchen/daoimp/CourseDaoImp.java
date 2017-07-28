package com.jlkj.kitchen.daoimp;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.jlkj.kitchen.bean.Course;
import com.jlkj.kitchen.bean.Menu;
import com.jlkj.kitchen.dao.CourseDao;
import com.jlkj.kitchen.databasehelper.DataBaseHelper;

public class CourseDaoImp implements CourseDao {

	@Override
	public boolean addCourse(Course course) {
		// TODO Auto-generated method stub
		Session session = DataBaseHelper.getSession();
		session.clear();
		Transaction transaction = session.beginTransaction();
		try {
			session.save(course);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		transaction.commit();
		return true;
	}

	@Override
	public List<Course> getAllCourse() {
		// TODO Auto-generated method stub
		Session session = DataBaseHelper.getSession();
		session.clear();
		List list = session.createSQLQuery("select * from course order by degree desc").list();
		List<Course> courses;
		if (list.size() == 0) {
			return null;
		} else {
			courses = new ArrayList<>();
			for (int i = 0; i < list.size(); i++) {
				Object[] obj = (Object[]) list.get(i);
				Course course = new Course();
				course.setId((Integer) obj[0]);
				course.setCoursename((String) obj[1]);
				course.setTeacherid((Integer) obj[2]);
				course.setIntroduction((String) obj[3]);
				course.setCourseimgurl((String) obj[4]);
				course.setDegree((double) obj[5]);
				courses.add(course);
			}
			return courses;
		}
	}

	@Override
	public List<Course> searchCourse(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteCourse(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateCourse(Course course) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Course> getRecommendCourse() {
		// TODO Auto-generated method stub
		Session session = DataBaseHelper.getSession();
		session.clear();
		List list = session.createSQLQuery("select * from course order by degree desc limit 0,4").list();
		List<Course> courses;
		if (list.size() == 0) {
			return null;
		} else {
			courses = new ArrayList<>();
			for (int i = 0; i < list.size(); i++) {
				Object[] obj = (Object[]) list.get(i);
				Course course = new Course();
				course.setId((Integer) obj[0]);
				course.setCoursename((String) obj[1]);
				course.setTeacherid((Integer) obj[2]);
				course.setIntroduction((String) obj[3]);
				course.setCourseimgurl((String) obj[4]);
				course.setDegree((double) obj[5]);
				courses.add(course);
			}
			return courses;
		}
	}

	@Override
	public Course getCourse(int id) {
		// TODO Auto-generated method stub
		Session session = DataBaseHelper.getSession();
		session.clear();
		List list = session.createSQLQuery("select * from course where id=" + id).list();
		if (list.size() == 0) {
			return null;
		} else {
			Object[] obj = (Object[]) list.get(0);
			Course course = new Course();
			course.setId((Integer) obj[0]);
			course.setCoursename((String) obj[1]);
			course.setTeacherid((Integer) obj[2]);
			course.setIntroduction((String) obj[3]);
			course.setCourseimgurl((String) obj[4]);
			course.setDegree((double) obj[5]);
			return course;
		}
	}

}
