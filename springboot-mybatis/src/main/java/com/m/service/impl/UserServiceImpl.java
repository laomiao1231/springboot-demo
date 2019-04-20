package com.m.service.impl;

import com.m.dao.primary.UserPrimaryDao;
import com.m.dao.secondary.UserSecondaryDao;
import com.m.entity.User;
import com.m.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserPrimaryDao userPrimaryDao;
    @Autowired
    private UserSecondaryDao userSecondaryDao;

    @Override
    public User getUserById(Integer Id) {
        return userSecondaryDao.getUserById(Id);
    }

    @Override
    @Transactional(transactionManager = "primaryTransactionManager", propagation = Propagation.REQUIRED)
    public User updateUser(Integer Id) {
        User user = userPrimaryDao.getUserById(Id);
        user.setCupSize("A+");
        userPrimaryDao.updateUser(user);
        throw new RuntimeException("test");
//        return user;
    }

    @Override
    public void removeUserById(Integer Id) {
        userPrimaryDao.removeUserById(Id);
    }
}
