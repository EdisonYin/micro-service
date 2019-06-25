package com.light.springboot.service.impl;

import com.light.springboot.service.FileConvertService;

import java.io.*;

/**
 * Created by Edison.Yin on 2019/6/25.
 */
public class FileConvertServiceServiceImpl implements FileConvertService {

    @Override
    public void convertCsvToHtml(File file) {
        FileReader fr = null;
        BufferedReader br = null;
        StringBuffer sBuffer = new StringBuffer();
        FileWriter fw = null;
        try {
            fr = new FileReader(file);
            br = new BufferedReader(fr);// 建立BufferedReader对象，并实例化为br
            String Line = br.readLine();// 从文件读取一行字符串
            StringBuffer sb2 = new StringBuffer();
            String temp[] = new String[2];
            // 判断读取到的字符串是否不为空
            sBuffer.append("<table>");
            while (Line != null) {
                temp = Line.split(",");
                 //就因为这条判断语句没加，害得我好惨！吸取教训，哈哈
                if (!temp[0].equals("") && !temp[1].equals("")) {
                    sBuffer.append("<tr>");
                    sBuffer.append("<td>");
                    sBuffer.append(temp[0]);
                    sBuffer.append("</td>");
                    sBuffer.append("<td>");
                    sBuffer.append(temp[1]);
                    sBuffer.append("</td>");
                    sBuffer.append("</tr>");
                    sBuffer.append("/n");
                }

                Line = br.readLine();// 从文件中继续读取一行数据
            }

            sBuffer.append("</table>");
            fw.write(sBuffer.toString());
            fw.flush();

        }catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        } catch (Exception ioExc) {
            ioExc.printStackTrace();
        } finally {
            try {
                if (br != null)
                    br.close();
                if (fr != null)
                    fr.close();// 关闭文件
            } catch (IOException ioexce) {
                ioexce.printStackTrace();
            }
        }
    }
}
