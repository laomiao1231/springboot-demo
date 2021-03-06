package com.m.dao;

import com.m.model.User;
import org.apache.ibatis.annotations.*;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface UserDao {

    @Select("SELECT * FROM gril where id = #{id}")
    @Results({ @Result(property = "Id", column = "id"),
            @Result(property = "age", column = "age"),
            @Result(property = "cupSize", column = "cup_size")})
    User getUserById(Integer Id);

    @Insert("insert into gril(age, cup_size) values(#{age}, #{cupSize})")
    void saveUser(User user);

    @Update("update gril set age=#{age}, cup_size=#{cupSize} where id = #{Id}")
    void updateUser(User user);

    @Delete("delete from gril where id = #{Id}")
    void removeUserById(Integer Id);
}
