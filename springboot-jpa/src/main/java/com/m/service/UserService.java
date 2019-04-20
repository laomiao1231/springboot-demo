package com.m.service;

import com.m.entity_primary.User;

public interface UserService {
    //jdbc接口
    void saveUser(User user);
    void removeUser(Integer id);
    User getUserById(Integer id);
    //jpa接口
    User createUser(User user);
    User retrieveUserById(Integer id);
}
