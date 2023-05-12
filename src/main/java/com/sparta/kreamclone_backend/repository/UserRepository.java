package com.sparta.kreamclone_backend.repository;

import com.sparta.kreamclone_backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

//    @Query("SELECT u FROM TB_USER u WHERE u.userEmail = :userEmail")
//    Optional<User> findByUserId(@Param("userEmail") String userEmail);

    Optional<User> findUserByUserEmail(String userEmail);

}
