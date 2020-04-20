package com.example.springbootweb.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

public class Command {
    public static void exeCmd(List<String>  args) {
        BufferedReader br = null;
        System.out.println("测试测试");


        String [] cmd = new String[args.size()];
        for (int i = 0;i<args.size(); i++){
            cmd[i] = args.get(i);
        }
        try {
            Process p = Runtime.getRuntime().exec(cmd);

            BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
            BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));
            String line;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
            while ((line = stdError.readLine()) != null) {
            //打印错误信息
                System.err.println(line);
            }
            in.close();
            int re = p.waitFor();
            System.out.println("end");
            // java代码中的 process.waitFor() 返回值（和我们通常意义上见到的0与1定义正好相反）
            // 返回值为0 - 表示调用python脚本成功；
            // 返回值为1 - 表示调用python脚本失败。
            System.out.println("调用 脚本是否成功：" + re);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
    }
}