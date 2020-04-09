package com.example.springbootweb.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * 整合Filter方式一
 */
//这个拦截的传的是个数组 可以列出想拦截的后缀   也可以直接传 字符串比如 "/first"
//@WebFilter(filterName = "FirstFilter",urlPatterns = {"*.do","*.jsp"})
@WebFilter(filterName = "FirstFilter",urlPatterns = "/first")
public class FirstFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }
    //过滤和拦截的时候执行的方法
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("进入filter");
        chain.doFilter(request,response);
        System.out.println("离开filter");
    }
    @Override
    public void destroy() {

    }
}
