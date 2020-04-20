package com.example.springbootweb.controller;


import com.example.springbootweb.config.IntentKey;
import com.example.springbootweb.mojo.Models;
import com.example.springbootweb.service.ModelService;
import com.example.springbootweb.service.impl.ModelServiceImpl;
import com.example.springbootweb.util.Command;
import com.example.springbootweb.util.Util_function;
import net.minidev.json.JSONObject;
import org.python.antlr.ast.Str;
import org.python.antlr.op.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;


import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.List;

/**
 * 处理模型重建的Controller
 */
@Controller
public class ModelController {
    @Autowired
    private ModelService  modelService;

//    @RequestMapping(value = IntentKey.baseUrl+"/model/create", method = RequestMethod.POST)
    @PostMapping(IntentKey.CreateModel_api)
    @ResponseBody
    public JSONObject creatModel(HttpServletRequest request) throws IOException, InterruptedException {
        Models model = new Models();
        JSONObject jsonObject = new JSONObject();
        String baseUrl = "./src/main/resources/static/api/models/";
        //暂时作为当前用户的手机号 等建立了socket再动态修改
        String phone = "18560125097";
        String ownerName = "jackie_qu";

        MultipartHttpServletRequest params=((MultipartHttpServletRequest) request);
        List<MultipartFile> files = ((MultipartHttpServletRequest) request)
                .getFiles("uploadFiles");
        //获取模型名称
        String name=params.getParameter("name");
        System.out.println("name:"+name);
//        String id=params.getParameter("id");
//        System.out.println("id:"+id);
        MultipartFile file = null;
        BufferedOutputStream stream = null;


        String modelID = getID();
        String dirPath = baseUrl + modelID;
        String photoPath = dirPath+"/photo";
        String modelPath = dirPath+"/model";
        File rootDir = new File(dirPath);
        File photoDir = new File(photoPath);
        File modelDir = new File(modelPath);

        if (!rootDir.exists()) {
            rootDir.mkdirs();
            photoDir.mkdirs();
            modelDir.mkdirs();
        }

        System.out.println("大小"+files.size());
        for (int i = 0; i < files.size(); ++i) {
            file = files.get(i);
            System.out.println(file.getOriginalFilename()+"----out");
            if (!file.isEmpty()) {
                try {
                    byte[] bytes = file.getBytes();
                    stream = new BufferedOutputStream(new FileOutputStream(
                            new File(photoPath+"/"+file.getOriginalFilename())));
                    System.out.println(file.getOriginalFilename());
                    stream.write(bytes);
                    stream.close();
                } catch (Exception e) {
                    stream = null;
                    return Util_function.setHttpHeader(200,"You failed to upload " + i + " => "
                            + e.getMessage());
                }
            } else {
                return Util_function.setHttpHeader(200,"You failed to upload " + i
                        + " because the file was empty.");
            }
        }


        System.out.println(modelID);
        model.setID(modelID);
        model.setName(name);
        model.setPhone(phone);
        model.setOwnerName(ownerName);

        //进入创建模型
        try {
            this.modelService.newModel(model);
        }catch (Exception e){
            e.printStackTrace();
            return Util_function.setHttpHeader(200,e.toString(),jsonObject);
        }


        jsonObject.appendField("id","134561");
        jsonObject.appendField("name","卢浮宫");

        return Util_function.setHttpHeader(200,"ok",jsonObject);






    }

    /**
     * 随机生成一个六位ID号 作为新模型的ID
     * @return
     */
    public String getID(){
        int a =(int)((Math.random()*9+1)*100000);
        return String.valueOf(a);
    }

}
