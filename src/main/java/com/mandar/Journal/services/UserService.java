package com.mandar.Journal.services;

import com.mandar.Journal.entity.User;
import com.mandar.Journal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserService {
    @Autowired
    UserRepository userRepo;
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }
    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    public void createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepo.save(user);
    }

    public boolean updateUser(User user, String username) {
        User userInDB = userRepo.findByUsername(username);
        userInDB.setUsername(user.getUsername());
        userInDB.setPassword(user.getPassword());
        createUser(userInDB);
        return true;
    }

    public boolean deleteUser(String username) {
        userRepo.deleteByUsername(username);
        return true;
    }
}
