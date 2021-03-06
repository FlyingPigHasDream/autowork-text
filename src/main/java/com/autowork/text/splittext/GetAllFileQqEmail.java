package com.autowork.text.splittext;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author rainyday
 * @createTime 2018/5/24.
 */
public class GetAllFileQqEmail {

    // 存放所有文本路径
    private ArrayList<String> txtPathAll = new ArrayList<>();

    private int qqCounts = 0;

    private int emailCounts = 0;

    private int wrrteCounts = 0;

    public static void main(String[] args) {
        //String filepath = "F:\\111";//D盘下的file文件夹的目录
        String filepath = "F:\\5月17号";//D盘下的file文件夹的目录
        String newFileName = "F:\\222.txt";
        String newFileName2 = "F:\\333.txt";

        GetAllFileQqEmail getAllFileQqEmail = new GetAllFileQqEmail();
        getAllFileQqEmail.findTxt(filepath);

     //   File file = new File(filepath);//File类型可以是文件也可以是文件夹
      //  File[] fileList = file.listFiles();//将该目录下的所有文件放置在一个File类型的数组中


        for (int j = 0; j < getAllFileQqEmail.txtPathAll.size(); j++) {

            String thisFileName = String.valueOf(getAllFileQqEmail.txtPathAll.get(j));

            System.out.println("thisFileName = " + thisFileName);
//        ArrayList<String> lists = new ArrayList<>();
            HashMap<String, Integer> maps =  new HashMap<>();
            String filename = thisFileName;

            try {
                FileInputStream in = new FileInputStream(filename);
                InputStreamReader inReader = new InputStreamReader(in, "UTF-8");
                BufferedReader bufReader = new BufferedReader(inReader);
                String line = null;
     /*           int i = 1;
                while((line = bufReader.readLine()) != null){
                    // String thisStr = line.split("com")[0] + "com";
                    String thisStr = line.split("---")[0].trim() ;
                    if (maps.get(thisStr) == null) {
                        maps.put(thisStr, 1);
                    }
                    System.out.println("第" + i + "行：" + line.split("m")[0] + "m");
                    i++;
                }*/


                //------------------------------------------------------------------

                while((line = bufReader.readLine()) != null){
                    // String thisStr = line.split("com")[0] + "com";
                    String[] thisArray = line.split("----");

                    for (int k = 0;k < thisArray.length; k++  ) {
                        String thisStr = thisArray[k];
                        // 纯数字
                        if (thisStr.matches("[0-9]+") && thisStr.length() > 6) {
                            if (maps.get(thisStr + "@qq.cpm") == null) {
                                maps.put(thisStr + "@qq.cpm", 1);
                            }
                            getAllFileQqEmail.qqCounts++;
                        }else if (thisStr.contains("@qq.com")) {
                        // .com
                            if (maps.get(thisStr) == null) {
                                maps.put(thisStr, 1);
                            }
                            getAllFileQqEmail.emailCounts++;
                        }
                    }
                }






                //------------------------------------------------------------------
                bufReader.close();
                inReader.close();
                in.close();
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("读取" + filename + "出错！");
            }


            Iterator iter = maps.entrySet().iterator();


            try {
                File file3 = new File(newFileName);
                if (!file3.exists()) {
                    file3.createNewFile();
                }
                FileOutputStream out = new FileOutputStream(newFileName, true);
                OutputStreamWriter outWriter = new OutputStreamWriter(out, "UTF-8");
                BufferedWriter bufWrite = new BufferedWriter(outWriter);

                while (iter.hasNext()) {
                    Map.Entry entry = (Map.Entry) iter.next();
                    Object key = entry.getKey();
                    System.out.println(key );
                    bufWrite.write(String.valueOf(key) + "\r\n");
                    //bufWrite.write(String.valueOf(key) );
                   // bufWrite.append(String.valueOf(key));
                    //bufWrite.newLine();
                }
                bufWrite.close();
                outWriter.close();
                out.close();
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("读取" + filename + "出错！");
            }
        }






        HashMap<String, Integer> maps = new HashMap<>();

        try {
            FileInputStream in = new FileInputStream(newFileName);
            InputStreamReader inReader = new InputStreamReader(in, "UTF-8");
            BufferedReader bufReader = new BufferedReader(inReader);
            String line = null;
            int i = 1;
            while((line = bufReader.readLine()) != null){
                if (maps.get(line) == null) {
                    maps.put(line, 1);
                }
                System.out.println("第" + i + "行：" + line);
                i++;
            }
            bufReader.close();
            inReader.close();
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("读取" + newFileName + "出错！");
        }


        Iterator iter = maps.entrySet().iterator();

        try {

            File file2 = new File(newFileName2);
            if (!file2.exists()) {
                file2.createNewFile();
            }
            FileOutputStream out = new FileOutputStream(newFileName2, true);
            OutputStreamWriter outWriter = new OutputStreamWriter(out, "UTF-8");
            BufferedWriter bufWrite = new BufferedWriter(outWriter);

            while (iter.hasNext()) {
                Map.Entry entry = (Map.Entry) iter.next();
                String key = String.valueOf(entry.getKey());
                System.out.println(key );
                bufWrite.write(key + "\r\n");
                getAllFileQqEmail.wrrteCounts ++;
            }
            bufWrite.close();
            outWriter.close();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("读取" + newFileName2 + "出错！");
        }


        int all = getAllFileQqEmail.qqCounts + getAllFileQqEmail.emailCounts;

        System.out.println("一共：" + getAllFileQqEmail.qqCounts + "条QQ账号");
        System.out.println("一共：" + getAllFileQqEmail.emailCounts + "条QQ邮箱");
        System.out.println("整合后一共：" + all + "条邮箱");
        System.out.println("一共打印出：" + getAllFileQqEmail.wrrteCounts + "条邮箱");
        System.out.println("文件个数 :" + getAllFileQqEmail.txtPathAll.size());
        System.out.println("map :" + maps.size());

    }







    public  void findTxt (String filePath) {
        File file = new File(filePath);//File类型可以是文件也可以是文件夹
        File[] fileList = file.listFiles();//将该目录下的所有文件放置在一个File类型的数组中

        for (int j = 0; j < fileList.length; j++) {
            String files = String.valueOf(fileList[j]);
            if (files.contains("txt")) {
                System.out.println("txt = " + files);
                txtPathAll.add(files);
            }else {
                System.out.println(files);
                findTxt(files);
            }
        }
    }

}
