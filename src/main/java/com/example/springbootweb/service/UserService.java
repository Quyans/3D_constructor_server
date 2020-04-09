package com.example.springbootweb.service;

import com.example.springbootweb.mojo.Users;

import java.util.List;

public interface UserService {
    public void addUser(Users users);

    public List<Users> findAllUser();
}
