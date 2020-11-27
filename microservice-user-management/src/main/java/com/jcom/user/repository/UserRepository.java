package com.jcom.user.repository;

import com.jcom.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

/**
 *
 * @author jclopez
 */
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    @Query("select u.username from User u where u.id in (:pIdList)")
    public List<User> findByIdList(@Param("pIdList") List<Long> idList);

}
