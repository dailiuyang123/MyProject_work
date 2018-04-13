package com.daily.test.Test2018;

import com.daily.util.ExcellUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Map;

/**
 * Created by json on 2018/4/3.
 * Describe:
 */
public class ReadExcellTest {

    public static void main(String[] args) {
        String url="D:\\中国.xlsx";
        File file=new File(url);
        try {
            FileInputStream fileInputStream=new FileInputStream(file);
            ExcellUtil excellUtil=new ExcellUtil();
            Map map = excellUtil.readExcellData(fileInputStream);
            for (Object o : map.keySet()) {
                System.out.println(o);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }

}
