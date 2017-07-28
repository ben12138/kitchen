package com.jlkj.kitchen.dao;

import java.util.List;

import com.jlkj.kitchen.bean.Course;
import com.jlkj.kitchen.bean.MyCourse;

public interface MyCourseDao {
	public boolean hasCourse(int courseid,int userid);
	public boolean addCourse(MyCourse myCourse);
	public boolean deleteCourse(int courseid,int userid);
	public List<MyCourse> getMyCourse(int userid);
}
