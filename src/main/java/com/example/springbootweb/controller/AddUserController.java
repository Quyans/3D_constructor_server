package com.example.springbootweb.controller;

import com.example.springbootweb.config.IntentKey;
import com.example.springbootweb.mojo.TestGetBean;
import com.example.springbootweb.mojo.Users;
import com.example.springbootweb.service.UserService;
import com.example.springbootweb.util.Command;
import com.example.springbootweb.util.Util_function;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class AddUserController {

    @Autowired
    private UserService userService;

    @PostMapping("/user/adduser")
    public String addUser(Users users ){
        //把users传给业务层和持久层
//        System.out.println(users.getUsername());
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

    @RequestMapping("/testCommand")
    public String testCommand(){
//        String commandStr = "ping www.baidu.com";
        List<String> commandStr = new ArrayList<String>();
//        commandStr = {};
        commandStr.add("ping");
        commandStr.add("www.baidu.com");
        Command.exeCmd(commandStr);
        return "null";
    }

    @GetMapping("/user/test")
    @ResponseBody
    public JSONObject returnjson(Model model){

        //把users传给业务层和持久层
        JSONObject jsonObject = new JSONObject();
        jsonObject.appendField("data","ok");
        jsonObject.appendField("msg","suc");
//        Json json  = new Json(200,"ok",jsonObject);

        return Util_function.setHttpHeader(200,"ok",jsonObject);
    }

    @RequestMapping("/testcmd")
    public String testcmd() throws IOException, InterruptedException {


        String executer = "python";
        // python绝对路径
        String file_path = "/home/qys/Documents/graduate_code/testMVS/MvgMvsPipeline.py";
        String inputUrl = "/home/qys/Documents/graduate_code/Code/3D_constructor_server/src/main/resources/static/api/models/18560125097/photo";
        String outputUrl = "/home/qys/Documents/graduate_code/Code/3D_constructor_server/src/main/resources/static/api/models/18560125097/model";

        String[] command_line = new String[] {executer, file_path, inputUrl, outputUrl};
        try {
            Process process = Runtime.getRuntime().exec(command_line);
            BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream(),"UTF-8"));
            BufferedReader stdError = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            String line;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
            while ((line = stdError.readLine()) != null) {
                System.err.println(line);
            }

            in.close();
            // java代码中的 process.waitFor() 返回值（和我们通常意义上见到的0与1定义正好相反）
            // 返回值为0 - 表示调用python脚本成功；
            // 返回值为1 - 表示调用python脚本失败。
            int re = process.waitFor();
            System.out.println("调用 python 脚本是否成功：" + re);
        } catch (IOException e1) {
            e1.printStackTrace();
        } catch (InterruptedException e2) {
            e2.printStackTrace();
        }
        return "ok";
    }


}
