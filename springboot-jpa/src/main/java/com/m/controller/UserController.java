package com.m.controller;

import com.m.entity_primary.User;
import com.m.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/save")
    public String saveUser() {
        User user = new User("mxw");
        userService.saveUser(user);
        return "success";
    }

    @GetMapping("/get/{id}")
    public User getUser(@PathVariable("id") Integer id) {
        User user = userService.getUserById(id);
        return user;
    }

    @DeleteMapping("/remove/{id}")
    public String remove(@PathVariable("id") Integer id) {
        userService.removeUser(id);
        return "success";
    }

    @PostMapping("/create")
    public String createUser() {
        User user = new User("mxf");
        userService.createUser(user);
        return "success";
    }
}
