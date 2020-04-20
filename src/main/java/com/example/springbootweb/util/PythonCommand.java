package com.example.springbootweb.util;



public class PythonCommand {

//    public static List<String> python(String pythonPath) {
//        List<String> res = new ArrayList<>();
////        String url=PythonRunner.class.getClassLoader().getResource("").getPath()+"cfg/spring/cs.py";
////        logger.info("脚本地址："+url);
//        File file = new File(url);
//        if (!file.exists()){
//            res.add("python脚本不存在！");
//            return res;
//        }
//        String [] cmd={"python",url};
//        try {
//            logger.info("开始执行脚本...\n");
//            Process process = Runtime.getRuntime().exec(cmd);
//            if (process == null) {
//                return null;
//            }
//            Scanner scanner = new Scanner(process.getInputStream());
//            while (scanner.hasNextLine()) {
//                String line = scanner.nextLine();
//                res.add(line);
//            }
//
//            try {
//                process.waitFor();
//            } catch (InterruptedException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
//            logger.info("脚本执行结束...\n 本次查询数据"+res.size()+"条");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return res;
//    }
//
}
