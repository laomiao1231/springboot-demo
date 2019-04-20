package com.m.controller;

import com.m.entity.User;
import com.m.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/get/{Id}")
    public User getUser(@PathVariable("Id") Integer Id) {
        User user = userService.getUserById(Id);
        return user;
    }

    @PutMapping("/update/{Id}")
    public User updateUser(@PathVariable("Id") Integer Id) {
        userService.updateUser(Id);
        User user = userService.getUserById(Id);
        return user;
    }

    @GetMapping("/remove/{Id}")
    public String removeUserById(@PathVariable("Id") Integer Id) {
        userService.removeUserById(Id);
        return "success";
    }
}
