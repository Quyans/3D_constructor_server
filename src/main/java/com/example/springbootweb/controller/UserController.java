package com.example.springbootweb.controller;


import com.example.springbootweb.config.IntentKey;
import com.example.springbootweb.mojo.Users;
import com.example.springbootweb.service.UserService;
import com.example.springbootweb.util.Util_function;

import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;


/**
 * 在这里处理用户的注册登录注销等操作
 */
@Controller
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping(IntentKey.Register_api)
    public JSONObject register(Users users){
        JSONObject jsonObject = new JSONObject();

        try{
            this.userService.addUser(users);
        }catch (Exception e){
            e.printStackTrace();
            return Util_function.setHttpHeader(405,e.toString());
        }


        return Util_function.setHttpHeader(200,"注册成功");
    }
}
