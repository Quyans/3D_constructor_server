package com.example.springbootweb.controller;

import com.example.springbootweb.mojo.JsonProduct;
import com.example.springbootweb.mojo.TestGetBean;
import com.example.springbootweb.mojo.Users;
import com.example.springbootweb.service.UserService;
import net.minidev.json.JSONObject;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AddUserController {

    @Autowired
    private UserService userService;

    @PostMapping("/user/adduser")
    public String addUser(Users users ){
        //把users传给业务层和持久层
        System.out.println(users.getUsername());
        try {
            this.userService.addUser(users);
        }catch (Exception e){
            e.printStackTrace();
            return "error.html";
        }
        return "success.html";
    }

    /**
     * 请求全部用户
     */
    @GetMapping("/user/findUserAll")
    @ResponseBody
    public List<Users> findUserAll(Model model){

        //把users传给业务层和持久层
        List<Users> list = new ArrayList<Users>();
        Users users = new Users();
        users.setUsername("name10");
        users.setUsersex(1);
        list.add(users);
        users.setUsername("name11");
        users.setUsersex(2);
        list.add(users);
        try {
            list = this.userService.findAllUser();
        }catch (Exception e){
            e.printStackTrace();
            return list;
        }

        return list;
    }

    //@RequestMapping(value = "/OkHttp", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    //post方法接受请求并且返回
    // 前端发json对象的时候用RequestParam   前端发json对象的String的时候用Requestbody
    @PostMapping("/OkHttp")
    @ResponseBody
    public String OkHttp(@RequestParam("name") String name,
                         @RequestParam("sex") String sex,
                         @RequestParam("id") String id){

        System.out.println("name"+name);
        JSONObject result = new JSONObject();
        result.put("msg", "ok");
        result.put("method", "json");
//        result.put("data", jsonParam);
        return result.toJSONString();
    }

    //测试get方法
    //https://www.cnblogs.com/zhanglijun/p/9403483.html 讲了GetMapping获取参数的几种方式
    @GetMapping(value = "/testGet")
    @ResponseBody
    public String testGet(TestGetBean testGetBean){
        System.out.println(testGetBean.getName());
        JSONObject result = new JSONObject();
        result.put("msg", "ok");
        result.put("method", "json");
        return result.toJSONString();
    }

    /**
     * 文件上传具体实现方法;
     *
     * @param request
     * @return
     */
    //https://www.cnblogs.com/zhao1949/p/9681701.html  这里讲了如何接收多个文件和多个参数
    @RequestMapping(value = "/batch/upload", method = RequestMethod.POST)
    @ResponseBody
    public String handleFileUpload(HttpServletRequest request) {
        MultipartHttpServletRequest params=((MultipartHttpServletRequest) request);
        List<MultipartFile> files = ((MultipartHttpServletRequest) request)
                .getFiles("uploadFiles");
//        String name=params.getParameter("name");
//        System.out.println("name:"+name);
//        String id=params.getParameter("id");
//        System.out.println("id:"+id);
        MultipartFile file = null;
        BufferedOutputStream stream = null;
        System.out.println("大小"+files.size());
        for (int i = 0; i < files.size(); ++i) {
            file = files.get(i);
            System.out.println(file.getOriginalFilename()+"----out");
            if (!file.isEmpty()) {
                try {
                    byte[] bytes = file.getBytes();
                    stream = new BufferedOutputStream(new FileOutputStream(
                            new File("./src/main/resources/static/uploadFiles/"+file.getOriginalFilename())));
                    System.out.println(file.getOriginalFilename());
                    stream.write(bytes);
                    stream.close();
                } catch (Exception e) {
                    stream = null;
                    return "You failed to upload " + i + " => "
                            + e.getMessage();
                }
            } else {
                return "You failed to upload " + i
                        + " because the file was empty.";
            }
        }
        return "upload successful";
    }

    /**
     * 实现文件下载
     */
    @RequestMapping("/file/downloadFile")
    private String downloadFile(HttpServletResponse response) throws IOException {
        System.out.println("1111");
        String downloadFilePath = "./src/main/resources/static/uploadFiles/";//被下载的文件在服务器中的路径,
        String fileName = "100_7102.JPG";//被下载文件的名称
        String fileUrl = downloadFilePath+fileName;
        File file = new File(fileUrl);
        if (file.exists()) {
            response.setContentType("application/force-download");// 设置强制下载不打开            
            response.addHeader("Content-Disposition", "attachment;fileName=" + fileName);
            byte[] buffer = new byte[1024];
            FileInputStream fis = null;
            BufferedInputStream bis = null;
            OutputStream outputStream = null;
            try {
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                outputStream = response.getOutputStream();
                int i = bis.read(buffer);
                while (i != -1) {
                    outputStream.write(buffer, 0, i);
                    i = bis.read(buffer);
                }
                return "下载成功";
            } catch (Exception e) {
                e.printStackTrace();
            } finally {

                if (bis != null) {
                    try {
                        bis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (outputStream != null){
                    try {
                        outputStream.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return "下载失败";
    }

}
