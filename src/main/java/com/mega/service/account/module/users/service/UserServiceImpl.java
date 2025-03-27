package com.mega.service.account.module.users.service;

import com.mega.service.account.module.users.entity.User;
import com.mega.service.account.module.users.repository.UserRepository;
import com.mega.service.account.utils.exception.ResourceNotFound;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public List<User> findAllActiveUsers() {
        return userRepository.findAllActiveUsers();
    }

    @Override
    public User findUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("User not found :" + id));
    }

    @Override
    public User createNewUser(User payload) {
        User user = new User();
        user.setUsername(payload.getUsername());
        user.setPassword(passwordEncoder.encode(payload.getPassword()));
        user.setEmail(payload.getEmail());
        user.setFirstName(payload.getFirstName());
        user.setLastName(payload.getLastName());
        user.setPhoneNumber(payload.getPhoneNumber());
        return userRepository.save(user);
    }

    @Override
    public User updateUser(Long id, User payload) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("User not found :" + id));
        user.setUsername(payload.getUsername());
        user.setPassword(passwordEncoder.encode(payload.getPassword()));
        user.setEmail(payload.getEmail());
        user.setFirstName(payload.getFirstName());
        user.setLastName(payload.getLastName());
        user.setPhoneNumber(payload.getPhoneNumber());
        return userRepository.save(user);
    }

    @Override
    public void deleteById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("User not found :" + id));
        user.setDeletedAt(LocalDateTime.now());
        userRepository.save(user);
    }
}
