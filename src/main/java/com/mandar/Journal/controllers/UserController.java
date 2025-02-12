package com.mandar.Journal.controllers;

import com.mandar.Journal.entity.User;
import com.mandar.Journal.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;
    @GetMapping
    public ResponseEntity<?> getAllUsers() {
        try {
            List<User> users = userService.getAllUsers();
            return new ResponseEntity<>(users, HttpStatus.OK);
        }catch (Exception e){
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
        }
    }
    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody User user ) {
        Authentication auth=SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        boolean res = userService.updateUser(user,username);
        return new ResponseEntity<>(res,HttpStatus.OK);
    }
    @DeleteMapping
    public ResponseEntity<?> deleteUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        boolean res = userService.deleteUser(username);
        return new ResponseEntity<>(res,HttpStatus.OK);
    }
}

