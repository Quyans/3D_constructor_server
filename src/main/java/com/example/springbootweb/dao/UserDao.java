package com.example.springbootweb.dao;

import com.example.springbootweb.mojo.Users;

import java.util.List;

public interface UserDao {

    //在这里操作数据库
    void insertUsers(Users users);
    List<Users> findAllUser();

}
