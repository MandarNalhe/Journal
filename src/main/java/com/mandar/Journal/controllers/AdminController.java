package com.mandar.Journal.controllers;

import com.mandar.Journal.entity.User;
import com.mandar.Journal.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private UserService userService;
    @GetMapping("/all-users")
    public ResponseEntity<?> getAllUsers() {
        try {
                List<User> users = userService.getAllUsers();
                return new ResponseEntity<>(users, HttpStatus.OK);

        }catch (Exception e){
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
        }
    }
    @PostMapping
    public ResponseEntity<?> createAdmin(@RequestBody User user){
        user.setRoles("ADMIN");
        String greet = userService.createUser(user);
        return new ResponseEntity<>(greet,HttpStatus.OK);
    }
}
