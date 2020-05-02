package com.example.springbootweb.service.impl;

import com.example.springbootweb.dao.UserDao;
import com.example.springbootweb.mojo.Users;
import com.example.springbootweb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 用户管理业务层
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    /**
     * 添加user业务逻辑
     * @param users
     */
    @Override
    @Transactional   //事物 注解
    public void addUser(Users users) {
        this.userDao.insertUsers(users);
    }


    //这是个查询 因此不需要加事物注解
    @Override
    public List<Users> findAllUser() {

        return this.userDao.findAllUser();
    }

    @Override
    public Users login(Users users) {
        return this.userDao.login(users);
    }

    @Override
    public Users getUserInfo(Users users) {

        return this.userDao.getUserInfo(users);
    }
}
