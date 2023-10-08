package com.courseRest.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.courseRest.Dao.CourseDao;
import com.courseRest.entities.Course;

@Component
public class CourseService {

	@Autowired
	private CourseDao courseDao;

//get all courses
	public List<Course> getAllCourses() {
		List<Course> list = (List<Course>) this.courseDao.findAll();
		return list;
	}

//get single course by Id
	public Optional<Course> getCourseById(int id) {
		Optional<Course> course = null;

		try {
			course = this.courseDao.findById(id);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return course;
	}

//adding Course
	public Course addCourse(Course c) {

		Course result = courseDao.save(c);

		return result;
	}

//Deleting
	public void deleteCourse(int cid) {

		courseDao.deleteById(cid);
	}

//Updating Course
	public void updateCourse(Course course, int courseId) {

		course.setId(courseId);
		courseDao.save(course);

	}
}
