package com.mega.service.account.module.users.service;

import com.mega.service.account.module.users.entity.User;

import java.util.List;

public interface UserService {
    List<User> findAllActiveUsers();
    User findUserById(Long id);
    User createNewUser(User user);
    User updateUser(Long id, User user);
    void deleteById(Long id);
}
