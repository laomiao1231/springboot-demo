package com.winterchen.service;

import com.github.pagehelper.PageInfo;
import com.winterchen.model.UserDomain;

public interface UserService {

    int addUser(UserDomain user);

    PageInfo<UserDomain> findAllUser(int pageNum, int pageSize);
}
