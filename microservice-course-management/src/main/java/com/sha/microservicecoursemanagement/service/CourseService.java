package com.sha.microservicecoursemanagement.service;

import com.sha.microservicecoursemanagement.model.Course;
import com.sha.microservicecoursemanagement.model.Transaction;

import java.util.List;

public interface CourseService {
    List<Course> allCourses();

    List<Course> filterCoursesByIdList(List<Long> idList);

    List<Course> filterCourses(String content);

    List<Transaction> filterTransactionsOfUser(Long userId);

    List<Transaction> filterTransactionsOfCourse(Long courseId);

    void saveTransaction(Transaction transaction);

    Course findCourseById(Long courseId);
}
