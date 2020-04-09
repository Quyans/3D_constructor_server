package com.example.springbootweb.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * 文件上传
 */
@RestController
public class fileUploadController {
    @PostMapping("/fileUploadController")

//    注意下面这个形参的 名字 file必须和上传页面的name一样
    public  String fileUpload(MultipartFile file) throws IOException {
        System.out.println(file.getOriginalFilename());
        file.transferTo(new File("/home/qys/Documents/" + file.getOriginalFilename() ));
        return "OK";
    }
}
