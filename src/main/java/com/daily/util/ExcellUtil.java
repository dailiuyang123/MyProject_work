package com.daily.util;

import com.daily.web.controller.ExcellController;
import javafx.scene.chart.PieChart;
import jdk.nashorn.internal.objects.NativeUint16Array;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import redis.clients.jedis.BinaryClient;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Azir on 2018/2/25.
 */
public class ExcellUtil {

    public void createExcel(List<Map<String, String>> list, HttpServletRequest request, HttpServletResponse response) {

        Workbook tempWorkBook = null;
        Sheet tempSheet = null;
        Row tempRow = null;
        InputStream inputstream = null;

        try {
            //获取-模板路径
            String path = ExcellController.class.getResource("/").getPath() + "/templet/Demo.xlsx";
            File file = new File(path);
            boolean exists = file.exists();
            System.out.println(exists);
            FileInputStream fileInputStream = new FileInputStream(file);
            // 输入流 转换 到父类
            inputstream = fileInputStream;
            // 读取-模板
            tempWorkBook = new XSSFWorkbook(inputstream);
            // 获取模板sheet页
            tempSheet = tempWorkBook.getSheetAt(0);
            // 将数据写入excel
            for (int i = 0; i < list.size(); i++) {
                int cellNo = 0;
                // 获取指定行
                tempRow = tempSheet.getRow(0);
                //获取当前sheet最后一行数据对应的行索引
                int currentLastRowIndex = tempSheet.getLastRowNum();
                int newRowIndex = currentLastRowIndex + 1;
                //写入数据位置
                XSSFRow newRow = (XSSFRow) tempSheet.createRow(newRowIndex);
                //开始创建并设置该行每一单元格的信息，该行单元格的索引从 0 开始
                int cellIndex = 0;
                //创建一个单元格，设置其内的数据格式为字符串，并填充内容，其余单元格类同
                XSSFCell newNameCell = newRow.createCell(cellIndex++, Cell.CELL_TYPE_STRING);
                newNameCell.setCellValue(list.get(i).get("No").toString());
                XSSFCell newGenderCell = newRow.createCell(cellIndex++, Cell.CELL_TYPE_STRING);
                newGenderCell.setCellValue(list.get(i).get("name"));
                XSSFCell newAgeCell = newRow.createCell(cellIndex++, Cell.CELL_TYPE_NUMERIC);
                newAgeCell.setCellValue(list.get(i).get("sex"));
                XSSFCell newAddressCell = newRow.createCell(cellIndex++, Cell.CELL_TYPE_NUMERIC);
                newAddressCell.setCellValue(list.get(i).get("age").toString());
            }
            // 将内容写入Excel
            String fileRealName = "导出工作簿.xlsx";
            this.SetCharacterResponse(request, response, fileRealName);

            //tempWorkBook.write(output);
            ServletOutputStream outputStream = response.getOutputStream();
            //将数据写入到 response 响应流中
            tempWorkBook.write(outputStream);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void exportExcell(Map dataMap,HttpServletRequest request, HttpServletResponse response){
        Workbook tempWorkBook = null;
        Sheet tempSheet = null;
        Row tempRow = null;
        InputStream inputstream = null;

        try {
            //获取-模板路径
            String path = ExcellController.class.getResource("/").getPath() + "/templet/reportDemo.xlsx";
            File file = new File(path);
            boolean exists = file.exists();
            System.out.println(exists);
            FileInputStream fileInputStream = null;
            fileInputStream = new FileInputStream(file);
            // 输入流 转换 到父类
            inputstream = fileInputStream;
            // 读取-模板
            tempWorkBook = new XSSFWorkbook(inputstream);
            // 获取模板sheet页数
            int numberOfSheets = tempWorkBook.getNumberOfSheets();
            List sheetDate=new ArrayList();
            for (int i=0;i<numberOfSheets;i++){
                Sheet sheetAt0 = tempWorkBook.getSheetAt(i);
                String sheetName = sheetAt0.getSheetName();
                System.out.println("工作表名称："+sheetName);
                if (i==0){
                    sheetDate = (List)dataMap.get("people");
                }else if (i==1){
                    sheetDate=(List)dataMap.get("item");
                }

                //获取数据最后一行的工作表数据
                int lastRowNum = sheetAt0.getLastRowNum();
                int newRowIndex = lastRowNum + 1;
                //新写入row位置
                for (int j=0;j<sheetDate.size();j++){
                    Row row = sheetAt0.createRow(newRowIndex);
                    List o = (List)sheetDate.get(j);
                    //行写入点
                    int number=0;
                    //行数据
                    for (int k=0;k<o.size();k++){
                        Cell cell = row.createCell(number);
                        cell.setCellValue(o.get(number).toString());
                        number++;
                    }
                    newRowIndex++;
                }


            }
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
            Date now=new Date();
            String time = sdf.format(now);
            String realFileName="业务报表"+time+".xlsx";
            this.SetCharacterResponse(request, response, realFileName);
            ServletOutputStream outputStream = response.getOutputStream();
            tempWorkBook.write(outputStream);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 作者  json
     * 时间  2018/3/27 17:32
     * 描述  初始化 响应流的编码格式
     * 判断 浏览器品牌 设置响应流
     **/
    public void SetCharacterResponse(HttpServletRequest request, HttpServletResponse response, String fileName) throws UnsupportedEncodingException {
        String fileRealName = fileName;
        String userAgent = request.getHeader("User-Agent");
        if (userAgent.contains("Safari")) {
            request.setCharacterEncoding("gbk");
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment;fileName=" + new String(fileRealName.getBytes("UTF-8"), "iso-8859-1"));
        } else if (userAgent.contains("Windows NT")) {
            request.setCharacterEncoding("UTF-8");
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment;fileName=" + new String(fileRealName.getBytes("GBK"), "iso-8859-1"));
        } else {
            request.setCharacterEncoding("gbk");
            response.setContentType("text/html;charset=gbk");
            response.setHeader("Content-Disposition", "attachment;fileName=" + new String(fileRealName.getBytes("UTF-8"), "iso-8859-1"));
        }
    }


    /**
    *
    * 作者  json
    * 时间  2018/4/3 11:23
    * 描述 读取excell 文件
    *
    **/

    public  Map readExcellData(FileInputStream inputStream){

        try {
            //创建 workbook（工作簿） 对象
            Workbook workbook = null;
            List data=new ArrayList();
            Map result=new HashMap();
            //
            workbook = new XSSFWorkbook(inputStream);
            //获取 sheet表 页数
            int numberOfSheets = workbook.getNumberOfSheets();
            for (int i=0;i<numberOfSheets;i++){
                Sheet sheetTemp = workbook.getSheetAt(i);
                //获取sheet页内行数
                int physicalNumberOfRows = sheetTemp.getPhysicalNumberOfRows();
                for (int j=0;j<physicalNumberOfRows;j++){  //读取每行
                    XSSFRow row = (XSSFRow)sheetTemp.getRow(j);
                    if (row!=null){
                        List list = new ArrayList();
                        for (int k = 0; k < sheetTemp.getRow(j).getPhysicalNumberOfCells(); k++) { // 获取每个单元格
                            Cell cell = row.getCell(k);
                            if (cell == null) {
                                list.add("");
                                continue;
                            }else {
                                int cellType = cell.getCellType();
                                //CellType 类型 值
                                //CELL_TYPE_NUMERIC 数值型 0
                                //CELL_TYPE_STRING 字符串型 1
                                //CELL_TYPE_FORMULA 公式型 2
                                //CELL_TYPE_BLANK 空值 3
                                //CELL_TYPE_BOOLEAN 布尔型 4
                                //CELL_TYPE_ERROR 错误 5
                                switch (cellType){
                                    case HSSFCell.CELL_TYPE_NUMERIC:{ // excell cell 数字格式
                                        //TODO: 日期格式 处理
                                        double numericCellValue = cell.getNumericCellValue();
                                        list.add(numberOfSheets);
                                    }
                                    case HSSFCell.CELL_TYPE_STRING:{  // excell cell 字符串
                                        String stringCellValue = cell.getStringCellValue();
                                        list.add(stringCellValue);

                                    }
                                    default:{
                                    }

                                }

                            }
                        }
                        data.add(list);
                    }
                }
            }
            result.put("data",data);
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }




}
