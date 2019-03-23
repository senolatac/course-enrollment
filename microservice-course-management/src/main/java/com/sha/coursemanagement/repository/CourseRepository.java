package com.sha.coursemanagement.repository;

import com.sha.coursemanagement.model.Course;

import java.util.List;

public interface CourseRepository  extends IGenericDao<Course> {
    List<Course> filterCourses(final String content);

    List<Course> filterCoursesByIdList(List<Long> idList);
}
