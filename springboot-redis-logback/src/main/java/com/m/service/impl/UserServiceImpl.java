package com.m.service.impl;

import com.m.dao.UserDao;
import com.m.model.User;
import com.m.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDao userDao;

    @Override
    @Cacheable(value = "test", key = "#root.targetClass.#Id", unless="#result == null")
    public User getUserById(Integer Id) {
        System.out.println("in service");
        return userDao.getUserById(Id);
    }

    @Override
    @CachePut(value = "test", key = "#root.targetClass.#Id")
    public User updateUser(Integer Id) {
        User user = userDao.getUserById(Id);
        user.setCupSize("A+");
        userDao.updateUser(user);
        return user;
    }

    @Override
    @CacheEvict(value = "test", key = "#root.targetClass.#Id", allEntries = true)
    public void removeUserById(Integer Id) {
        userDao.removeUserById(Id);
    }
}
