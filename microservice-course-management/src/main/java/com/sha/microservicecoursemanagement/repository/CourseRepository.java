package com.sha.microservicecoursemanagement.repository;

import com.sha.microservicecoursemanagement.model.Course;

import java.util.List;

public interface CourseRepository extends IGenericDao<Course> {
    List<Course> filterCourses(String text);

    List<Course> filterCoursesByIdList(List<Long> idList);
}
