package com.example.springbootweb.service;

import com.example.springbootweb.mojo.Models;

import java.io.IOException;
import java.util.List;

public interface ModelService {

    public void newModel(Models models) throws IOException, InterruptedException;
    public List<Models> getAllModel(String phone);
}
