package com.jlkj.kitchen.dao;

import java.util.List;

import com.jlkj.kitchen.bean.Course;

public interface CourseDao {
	public boolean addCourse(Course course);
	public List<Course> getAllCourse();
	public List<Course> searchCourse(String key);
	public List<Course> getRecommendCourse();
	public boolean deleteCourse(int id);
	public boolean updateCourse(Course course);
	public Course getCourse(int id);
}
