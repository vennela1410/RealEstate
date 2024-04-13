package com.softwareverification.realestate.repository;

import com.softwareverification.realestate.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, String> {

    UserEntity findByUserId(String userId);

    UserEntity findByUserIdAndPassword(String userId, String password);

    UserEntity findByEmailId(String emailId);
}
