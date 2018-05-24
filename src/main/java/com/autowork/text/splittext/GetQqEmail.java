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
public class GetQqEmail {


    public static void main(String[] args) {
//        ArrayList<String> lists = new ArrayList<>();
        HashMap<String, Integer> maps =  new HashMap<>();
        String filename = "F:\\333.txt";
        String newFileName = "F:\\222.txt";
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
            FileOutputStream out = new FileOutputStream(newFileName);
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
            System.out.println("读取" + filename + "出错！");
        }
    }
}
