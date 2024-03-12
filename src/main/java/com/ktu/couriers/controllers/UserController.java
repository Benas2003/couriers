package com.ktu.couriers.controllers;

import com.ktu.couriers.models.User;
import com.ktu.couriers.models.UserSearch;
import com.ktu.couriers.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/user")
@Slf4j
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping()
    public List<User> getAllUsers() {
        return this.userService.list();
    }

    @GetMapping("/search")
    public List<User> getUsersBySearch(@PathVariable UserSearch userSearch) {
        return this.userService.search(userSearch);
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) {
        return this.userService.get(id);
    }

    @PostMapping()
    public User createUser(@RequestBody(required = true) User user) {
        return this.userService.create(user);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody(required = true) User user) {
        return this.userService.update(id, user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        this.userService.delete(id);
    }
}
