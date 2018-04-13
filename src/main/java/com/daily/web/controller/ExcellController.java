package com.daily.web.controller;



import com.daily.util.ExcellUtil;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Azir on 2018/2/25.
 */

@RestController
@RequestMapping("/Excell")
public class ExcellController {

    // Excell 导出
    @RequestMapping(value = "/exportExcell",method = RequestMethod.POST)
    public void  exportExcesll(HttpServletRequest request, HttpServletResponse response){
            try {
                // 初始化数据
                List data=new ArrayList();
                for (int i=0;i<2;i++){
                    Map map=new HashMap();
                    map.put("No",i+"");
                    map.put("name","编号"+i);
                    map.put("sex","待设定");
                    map.put("age","1");
                    data.add(map);
                }

                ExcellUtil excellUtil=new ExcellUtil();
                excellUtil.createExcel(data,request,response);
            }catch (Exception e){
                e.printStackTrace();
            }

    }

    @RequestMapping(value = "/readExcellValue",method = RequestMethod.POST)
    public void readExcellValue(HttpServletRequest request,HttpServletResponse response){
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

    //多sheet Excell文件导出
    @RequestMapping(value = "/exportReport",method = RequestMethod.POST)
    public void exportReport(HttpServletRequest request,HttpServletResponse response){
        try {
            //数据插入
            Map<String,Object> dataMap=new HashMap<>();
            //***********投后管理人sheet*******
            List peoples=new ArrayList();
            for (int i=0;i<5;i++){
                List people=new ArrayList();
                for (int j=0;j<11;j++){
                    String c=i+":"+j;
                    System.out.println(c);
                    people.add(c);
                }
                peoples.add(people);

            }
            dataMap.put("people",peoples);
            //*************顾投项目sheet********
            List items=new ArrayList();

            for (int i=0;i<5;i++){
                List item=new ArrayList();
                for (int j=0;j<11;j++){
                    String c=i+":"+j;
                    System.out.println(c);
                    item.add(c);
                }
                items.add(item);

            }
            dataMap.put("item",items);
            ExcellUtil excellUtil=new ExcellUtil();
            excellUtil.exportExcell(dataMap,request,response);

        }catch (Exception e){

        }


    }

}
