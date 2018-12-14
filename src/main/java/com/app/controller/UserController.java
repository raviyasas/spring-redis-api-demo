package com.app.controller;

import com.app.model.User;
import com.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/rest/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Cacheable(value = "users", key = "#userId", unless = "#result.salary < 100000")
    @GetMapping(value = "/getUser/{userId}")
    public Optional<User> getUser(@PathVariable String userId) {
        return userRepository.findById(Long.valueOf(userId));
    }

    @CachePut(value = "users", key = "#user.id")
    @PutMapping("/updateUser")
    public User updatePersonByID(@RequestBody User user) {
        return userRepository.save(user);
    }

    @CacheEvict(value = "users", allEntries=true)
    @DeleteMapping("/removeUser/{id}")
    public void deleteUserByID(@PathVariable Long id) {
        userRepository.deleteById(id);
    }

}
