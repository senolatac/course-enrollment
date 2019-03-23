package com.sha.coursemanagement.repository;

import com.sha.coursemanagement.model.Transaction;

import java.util.List;

public interface TransactionRepository  extends IGenericDao<Transaction> {

    List<Transaction> findAllTransactionsOfUser(Long userId);

    List<Transaction> findAllTransactionsOfCourse(Long courseId);
}
