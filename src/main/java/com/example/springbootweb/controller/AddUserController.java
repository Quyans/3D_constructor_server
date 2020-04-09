package com.example.springbootweb.controller;

import com.example.springbootweb.mojo.Users;
import com.example.springbootweb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Controller
//@RestController
//sfa
public class AddUserController {

    @Autowired
    private UserService userService;


    @RequestMapping("/user/adduser")
    public String addUser(Users users ){
        //把users传给业务层和持久层
        try {
            this.userService.addUser(users);
        }catch (Exception e){
            e.printStackTrace();
            return "error";
        }
        return "successful";
    }

    /**
     * 请求全部用户
     */
    @GetMapping("/user/findUserAll")
    @ResponseBody
    public List<Users> findUserAll(Model model){

        //把users传给业务层和持久层
        List<Users> list = new ArrayList<Users>();
        Users users = new Users();
        users.setUsername("name10");
        users.setUsersex(1);
        list.add(users);
        users.setUsername("name11");
        users.setUsersex(2);
        list.add(users);
        try {
            list = this.userService.findAllUser();
//            model.addAttribute("list",list);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("...........111111111111111111...........");
            return list;
        }
        System.out.println("this is list");
        return list;
    }

}
