package com.example.springbootweb.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * 整合listener
 */
@WebListener
public class FirstListener implements ServletContextListener {
    public void contextDestroyed(ServletContextEvent event){

    }
    public void contextInitialized(ServletContextEvent event){
        System.out.println("Listener init.....");
    }
}
