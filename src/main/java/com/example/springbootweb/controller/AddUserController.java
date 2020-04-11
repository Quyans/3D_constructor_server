package com.example.springbootweb.controller;

import com.example.springbootweb.mojo.JsonProduct;
import com.example.springbootweb.mojo.TestGetBean;
import com.example.springbootweb.mojo.Users;
import com.example.springbootweb.service.UserService;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
//@RestController
//sfa
public class AddUserController {

    @Autowired
    private UserService userService;

    @PostMapping("/user/adduser")
    public String addUser(Users users ){
        //把users传给业务层和持久层
        System.out.println(users.getUsername());
        try {
            this.userService.addUser(users);
        }catch (Exception e){
            e.printStackTrace();
            return "error.html";
        }
        return "success.html";
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

        return list;
    }

//    @RequestMapping(value = "/OkHttp", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    //post方法接受请求并且返回
    // 前端发json对象的时候用RequestParam   前端发json对象的String的时候用Requestbody
    @PostMapping("/OkHttp")
    @ResponseBody
    public String OkHttp(@RequestParam("name") String name,
                         @RequestParam("sex") String sex,
                         @RequestParam("id") String id){

        System.out.println("name"+name);
        JSONObject result = new JSONObject();
        result.put("msg", "ok");
        result.put("method", "json");
//        result.put("data", jsonParam);
        return result.toJSONString();
    }

    //测试get方法
    //https://www.cnblogs.com/zhanglijun/p/9403483.html 讲了GetMapping获取参数的几种方式
    @GetMapping(value = "/testGet")
    @ResponseBody
    public String testGet(TestGetBean testGetBean){
        System.out.println(testGetBean.getName());
        JSONObject result = new JSONObject();
        result.put("msg", "ok");
        result.put("method", "json");
        return result.toJSONString();
    }

}
