package com.jlkj.kitchen.dao;

import java.util.List;

import com.jlkj.kitchen.bean.CourseComment;

public interface CourseCommentDao {
	public boolean addCourseComment(CourseComment comment);
	public boolean deleteCourseComment(int id);
	public List<CourseComment> getCourseComment(int courseid);
	public boolean updateCourseComment(CourseComment comment);
}
