package com.example.springbootweb.service;

import com.example.springbootweb.mojo.Models;

import java.io.IOException;

public interface ModelService {

    public void newModel(Models models) throws IOException, InterruptedException;

}
