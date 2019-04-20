package com.m.service;


import com.m.entity.User;

public interface UserService {
    User getUserById(Integer Id);
    User updateUser(Integer Id);
    void removeUserById(Integer Id);
}
