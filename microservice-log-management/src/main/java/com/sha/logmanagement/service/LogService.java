package com.sha.logmanagement.service;

import com.sha.logmanagement.model.Log;
import com.sha.logmanagement.model.Summary;

import java.util.List;
import java.util.UUID;

public interface LogService {
    Summary findSummaryByCourseId(Long courseId);

    Log saveOrUpdate(Log log);

    Summary saveOrUpdate(Summary summary);

    List<Summary> findPopularCourses();
}
