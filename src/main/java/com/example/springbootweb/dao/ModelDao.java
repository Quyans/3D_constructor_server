package com.example.springbootweb.dao;

import com.example.springbootweb.mojo.Models;
import org.springframework.ui.Model;

import java.util.List;

public interface ModelDao {

    public void newModel(Models models);
    public List<Models> getAllModel(String phone);
}
