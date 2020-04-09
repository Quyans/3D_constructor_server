package com.example.springbootweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.sql.DataSource;

@Controller
public class PageController {
    @RequestMapping("/page")
    public String showPage(){
        return "index.html";
    }

    @Autowired
    private DataSource dataSource;
    @GetMapping("/showInfo")
    public String showInfo(){
        return "ok";
    }

}
