package com.m.service.impl;

import com.m.entity_primary.User;
import com.m.entity_secondary.Demo;
import com.m.repository_primary.UserRepository;
import com.m.repository_secondary.UserSecondaryRepository;
import com.m.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    @Qualifier("primaryJdbcTemplate")
    private JdbcTemplate primaryJdbcTemplate;
    @Autowired
    @Qualifier("secondaryJdbcTemplate")
    private JdbcTemplate secondaryJdbcTemplate;
    @Autowired
    private UserSecondaryRepository userSecondaryRepository;
    @Autowired
    private UserRepository userRepository;
    @Override
    public void saveUser(User user) {
        String sql = "insert into demo (id,name) value (?,?)";
        Object[] args = null;
        if (user != null) {
            args = new Object[]{user.getId(), user.getName()};
        }
        primaryJdbcTemplate.update(sql, args);
    }

    @Override
    public void removeUser(Integer id) {
        String sql = "delete from demo where id = ?";
        secondaryJdbcTemplate.update(sql, id);
    }

    @Override
    public User getUserById(Integer id) {
        User user = null;
        String sql = "select id,name from demo u where u.id = ?";
        List<User> users = primaryJdbcTemplate.query(sql, new Object[]{id}, new BeanPropertyRowMapper<User>(User.class));
        if(users != null && users.size() > 0) {
            user = users.get(0);
        }
        return user;
    }

    @Override
    @Transactional(transactionManager = "secondaryJpaTransactionManager", propagation = Propagation.REQUIRED)
    public User createUser(User user) {
        Demo demo = new Demo();
        BeanUtils.copyProperties(user, demo);
        userSecondaryRepository.save(demo);
        return user;
    }

    @Override
    @Transactional(transactionManager = "secondaryTransactionManager", propagation = Propagation.REQUIRED)
    public User retrieveUserById(Integer id) {
        User user = userRepository.getOne(id);
        return user;
    }
}
