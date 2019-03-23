package com.sha.logmanagement.repository;

import com.sha.logmanagement.model.Summary;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SummaryRepository extends CrudRepository<Summary, Long> {

    Optional<Summary> findByCourseId(Long courseId);

    @Query("update summary set hit_count = hit_count + 1 where course_id=?0")
    void incrementHitCount(Long courseId);

    @Query("Select * from summary limit 100")
    List<Summary> findPopularCourses();
}
