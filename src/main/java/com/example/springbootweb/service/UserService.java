package com.example.springbootweb.service;

import com.example.springbootweb.mojo.Users;


import java.util.List;

public interface UserService {
    public void addUser(Users users);

    public List<Users> findAllUser();

    public Users login(Users users);

    public Users getUserInfo(Users users);
}
