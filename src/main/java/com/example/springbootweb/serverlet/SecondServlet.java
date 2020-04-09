package com.example.springbootweb.serverlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 整合servlet配置2
 */
public class SecondServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response){
        System.out.println("secondServlet....");
    }
}
