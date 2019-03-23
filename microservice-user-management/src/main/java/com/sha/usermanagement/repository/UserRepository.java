package com.sha.usermanagement.repository;

import com.sha.usermanagement.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    @Query("Select u.name from User u where u.id in (:pIdList)")
    List<String> findUserNames(@Param("pIdList") List<Long> userIdList);
}
