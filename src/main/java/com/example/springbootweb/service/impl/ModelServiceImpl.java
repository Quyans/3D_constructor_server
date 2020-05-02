package com.example.springbootweb.service.impl;

import com.example.springbootweb.config.IntentKey;
import com.example.springbootweb.dao.ModelDao;
import com.example.springbootweb.dao.impl.ModelDaoImpl;
import com.example.springbootweb.mojo.Models;
import com.example.springbootweb.service.ModelService;
import com.example.springbootweb.util.Command;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


@Service
public class ModelServiceImpl implements ModelService {

    @Autowired
    private ModelDao modelDao;
    /**
     *
     * 在这里进行调用库文件进行重建
     *
     * @param models
     * @throws IOException
     * @throws InterruptedException
     */
    @Override
    @Transactional   //事物 注解
    @Async
    public void newModel(Models models) throws IOException, InterruptedException {

        //模型ID
        String id = models.getID();
        String dirPath = IntentKey.PROJECT_URL+ "/src/main/resources/static/api/models/"+id;



        String inputUrl = dirPath + "/photo";
        String outputUrl = dirPath + "/model";


        String ScriptUrl = "/home/qys/Documents/graduate_code/testMVS/MvgMvsPipeline.py";

        this.modelDao.newModel(models);

        List<String> shell = new ArrayList<String>();
        shell.add("python");
        shell.add(ScriptUrl);
        shell.add(inputUrl);
        shell.add(outputUrl);
//        System.out.println(shell);
        Command.exeCmd(shell);
    }

    @Override
    public List<Models> getAllModel(String phone) {
        return this.modelDao.getAllModel(phone);
    }
}
