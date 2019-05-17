package com.sha.microservicecoursemanagement.repository;

import com.sha.microservicecoursemanagement.model.Transaction;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import java.util.List;

@Transactional
@Repository
public class TransactionRepositoryImpl extends AbstractGenericDao<Transaction> implements TransactionRepository{

    @Override
    public List<Transaction> findAllTransactionsOfUser(final Long userId){
        String hql="Select t from Transaction t Where t.userId = :pUserId";
        Query query = em.createQuery(hql);
        query.setParameter("pUserId",userId);
        return query.getResultList();
    }

    @Override
    public List<Transaction> findAllTransactionsOfCourse(final Long courseId){
        String hql = "Select t from Transaction t Where t.course.id = :pCourseId";
        Query query = em.createQuery(hql);
        query.setParameter("pCourseId", courseId);
        return query.getResultList();
    }

}
