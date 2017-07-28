package com.jlkj.kitchen.dao;

import java.util.List;

import com.jlkj.kitchen.bean.CourseVideo;

public interface CourseVideoDao {
	public boolean addCourseVideo(CourseVideo courseVideo);
	public boolean deleteCourseVideo(int id);
	public List<CourseVideo> getCourseVideo(int courseid);
	public boolean updateCourseVideo(CourseVideo courseVideo);
}
