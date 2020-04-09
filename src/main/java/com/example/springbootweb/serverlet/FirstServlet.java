package com.example.springbootweb.serverlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 整合servlet方式一
 */
@WebServlet(name = "1stServlet",urlPatterns = "/first")
public class FirstServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response){
        System.out.println("servlet....");
    }
}
