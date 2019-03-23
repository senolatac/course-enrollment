package com.sha.logmanagement.service;

import com.sha.logmanagement.model.Log;
import com.sha.logmanagement.model.Summary;
import com.sha.logmanagement.repository.LogRepository;
import com.sha.logmanagement.repository.SummaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Transactional
@Service
public class LogServiceImpl implements LogService {

    @Autowired
    private LogRepository logRepository;

    @Autowired
    private SummaryRepository summaryRepository;

    @Override
    public Summary findSummaryByCourseId(Long courseId) {
        return summaryRepository.findByCourseId(courseId).orElse(null);
    }

    @Override
    public Log saveOrUpdate(Log log) {
        Summary existSummary = summaryRepository.findByCourseId(log.getCourseId()).orElse(null);
        if(existSummary!=null){
            summaryRepository.delete(existSummary);
            existSummary.setHitCount(existSummary.getHitCount()+1);
            summaryRepository.save(existSummary);
        }else{
            Summary summary = new Summary();
            summary.setCourseId(log.getCourseId());
            summary.setHitCount(1L);
            summaryRepository.save(summary);
        }
        log.setId(UUID.randomUUID());
        logRepository.save(log);
        return log;
    }

    @Override
    public Summary saveOrUpdate(Summary summary) {
        summaryRepository.save(summary);
        return summary;
    }


    @Override
    public List<Summary> findPopularCourses(){
        return summaryRepository.findPopularCourses();
    }
}
