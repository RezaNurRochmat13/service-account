package com.mega.service.account.module.users.repository;

import com.mega.service.account.module.users.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u FROM User u WHERE u.deletedAt IS NULL")
    List<User> findAllActiveUsers();
}
