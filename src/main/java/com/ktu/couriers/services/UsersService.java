package com.ktu.couriers.services;

import com.ktu.couriers.models.User;
import com.ktu.couriers.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersService {

    @Autowired
    private UsersRepository usersRepository;

    public List<User> list() {
        return this.usersRepository.findAll();
    }

    public User get(Long id) {
        return this.usersRepository.findById(id).orElse(null);
    }

    public User create(User user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        return this.usersRepository.save(user);
    }

    public User update(Long id, User user) {
        return this.usersRepository.save(user);
    }

    public void delete(Long id) {
        this.usersRepository.deleteById(id);
    }
}
