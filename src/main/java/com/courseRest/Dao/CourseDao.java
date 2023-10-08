package com.courseRest.Dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.courseRest.entities.Course;

public interface CourseDao extends JpaRepository<Course, Integer> {

}
