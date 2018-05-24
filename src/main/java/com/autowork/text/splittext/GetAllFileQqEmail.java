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


    public static void main(String[] args) {
        String filepath = "F:\\111";//D盘下的file文件夹的目录
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
                int i = 1;
                while((line = bufReader.readLine()) != null){
                    String thisStr = line.split("m")[0] + "m";
                    if (maps.get(thisStr) == null) {
                        maps.put(thisStr, 1);
                    }
                    System.out.println("第" + i + "行：" + line.split("m")[0] + "m");
                    i++;
                }
                bufReader.close();
                inReader.close();
                in.close();
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("读取" + filename + "出错！");
            }


            Iterator iter = maps.entrySet().iterator();

            try {
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
                Object key = entry.getKey();
                System.out.println(key );
                bufWrite.write(String.valueOf(key) + "\r\n");

            }
            bufWrite.close();
            outWriter.close();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("读取" + newFileName2 + "出错！");
        }

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
