package com.ktu.couriers.controllers;

import com.ktu.couriers.models.User;
import com.ktu.couriers.services.UsersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/user")
@CrossOrigin(origins = "http://localhost:4200")
@Slf4j
public class UserController {

    @Autowired
    UsersService usersService;

    @GetMapping()
    public List<User> getAllUsers() {
        log.info("Getting all users");
        return this.usersService.list();
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) {
        log.info("Getting user by id: {}", id);
        return this.usersService.get(id);
    }

    @PostMapping()
    public User createUser(@RequestBody User user) {
        log.info("Creating user: {}", user);
        return this.usersService.create(user);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user) {
        log.info("Updating user: {}", user);
        return this.usersService.update(id, user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        log.info("Deleting user by id: {}", id);
        this.usersService.delete(id);
    }
}
