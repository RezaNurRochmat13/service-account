package com.mega.service.account.module.users.presenter;

import com.mega.service.account.module.users.entity.User;
import com.mega.service.account.module.users.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserPresenter {
    private final UserServiceImpl userService;

    @GetMapping("/users")
    public Map<String, Object> findAllActiveUsers() {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("data", userService.findAllActiveUsers());
        return response;
    }

    @GetMapping("/users/{id}")
    public Map<String, Object> findUserById(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("data", userService.findUserById(id));
        return response;
    }

    @PostMapping("/users")
    public Map<String, Object> createNewUser(@RequestBody User payload) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("data", userService.createNewUser(payload));
        return response;
    }

    @PutMapping("/users/{id}")
    public Map<String, Object> updateUser(@PathVariable Long id, @RequestBody User payload) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("data", userService.updateUser(id, payload));
        return response;
    }

    @DeleteMapping("/users/{id}")
    public Map<String, Object> deleteById(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        userService.deleteById(id);
        return response;
    }
}
