package com.example.springbootweb.controller;


import com.example.springbootweb.config.IntentKey;
import com.example.springbootweb.mojo.Users;
import com.example.springbootweb.service.UserService;
import com.example.springbootweb.util.Util_function;


import net.minidev.json.JSONObject;
import org.python.modules._json._json;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 在这里处理用户的注册登录注销等操作
 */
@Controller
public class UserController {
    @Autowired
    UserService userService;
    @PostMapping(IntentKey.Register_api)
    @ResponseBody
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

    @PostMapping(IntentKey.Login_api)
    @ResponseBody
    public JSONObject login(Users users){
        JSONObject jsonObject = new JSONObject();
        Users logUser = new Users();
        try{
            logUser = this.userService.login(users);
        }catch (Exception e){
            e.printStackTrace();
            return Util_function.setHttpHeader(405,e.toString());
        }

        if (logUser != null ){
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.appendField("nickname",logUser.getNickname());
            jsonObject1.appendField("phone",logUser.getPhone());
            System.out.println(logUser.getNickname());
            return Util_function.setHttpHeader(200,"登录成功",jsonObject1);
        }

        return Util_function.setHttpHeader(405,"账号或密码错误");
    }
}
