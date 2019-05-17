package com.sha.microservicecoursemanagement.repository;

import com.sha.microservicecoursemanagement.model.Transaction;

import java.util.List;

public interface TransactionRepository extends IGenericDao<Transaction> {
    List<Transaction> findAllTransactionsOfUser(Long userId);

    List<Transaction> findAllTransactionsOfCourse(Long courseId);
}
