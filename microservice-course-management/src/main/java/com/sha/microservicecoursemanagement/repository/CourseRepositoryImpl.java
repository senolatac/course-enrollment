package com.sha.microservicecoursemanagement.repository;

import com.sha.microservicecoursemanagement.model.Course;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import java.util.List;

@Transactional
@Repository
public class CourseRepositoryImpl extends AbstractGenericDao<Course> implements CourseRepository {

    @Override
    public List<Course> filterCourses(final String text){
        String hql = "Select c from Course c where 1=1 ";
        if(text!=null && !"".equals(text.trim())){
            hql+="AND (lower(c.title) like lower(:pText) or lower(c.category) like lower(:pText) or lower(c.author) " +
                    "like lower(:pText))";
        }
        Query query = em.createQuery(hql);
        if(text!=null && !"".equals(text.trim())){
            query.setParameter("pText","%"+text+"%");
        }
        return query.getResultList();
    }

    @Override
    public List<Course> filterCoursesByIdList(final List<Long> idList){
        String hql = "Select c from Course c where c.id in (:pIdList)";
        Query query = em.createQuery(hql);
        query.setParameter("pIdList", idList);
        return query.getResultList();
    }
}
