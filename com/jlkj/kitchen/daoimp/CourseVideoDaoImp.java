package com.jlkj.kitchen.daoimp;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.jlkj.kitchen.bean.CourseVideo;
import com.jlkj.kitchen.bean.Menu;
import com.jlkj.kitchen.dao.CourseVideoDao;
import com.jlkj.kitchen.databasehelper.DataBaseHelper;

public class CourseVideoDaoImp implements CourseVideoDao {

	@Override
	public boolean addCourseVideo(CourseVideo courseVideo) {
		// TODO Auto-generated method stub
		Session session = DataBaseHelper.getSession();
		session.clear();
		Transaction transaction = session.beginTransaction();
		try {
			session.save(courseVideo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		transaction.commit();
		return true;
	}

	@Override
	public boolean deleteCourseVideo(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<CourseVideo> getCourseVideo(int courseid) {
		// TODO Auto-generated method stub
		Session session = DataBaseHelper.getSession();
		session.clear();
		List list = session.createSQLQuery("select * from coursevideo where courseid="+courseid).list();
		List<CourseVideo> courseVideos;
		if (list.size() == 0) {
			return null;
		} else {
			courseVideos = new ArrayList<>();
			for (int i = 0; i < list.size(); i++) {
				Object[] obj = (Object[]) list.get(i);
				CourseVideo courseVideo = new CourseVideo();
				courseVideo.setId((Integer)obj[0]);
				courseVideo.setCourseid((Integer)obj[1]);
				courseVideo.setCoursename((String)obj[2]);
				courseVideo.setCourseurl((String)obj[3]);
				courseVideos.add(courseVideo);
			}
			return courseVideos;
		}
	}

	@Override
	public boolean updateCourseVideo(CourseVideo courseVideo) {
		// TODO Auto-generated method stub
		return false;
	}

}
