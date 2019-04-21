package com.winterchen.dao;

import com.winterchen.model.UserDomain;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface UserDao {

    int insert(UserDomain record);

    List<UserDomain> selectUsers();
}
