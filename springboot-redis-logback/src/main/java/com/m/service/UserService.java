package com.m.service;

import com.m.model.User;

public interface UserService {
    User getUserById(Integer Id);
    User updateUser(Integer Id);
    void removeUserById(Integer Id);
}
