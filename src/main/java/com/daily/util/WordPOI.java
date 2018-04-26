package com.daily.util;

import org.apache.poi.POIXMLDocument;
import org.apache.poi.POIXMLProperties;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.*;
import org.apache.xmlbeans.XmlException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by json on 2018/4/24.
 * Describe:根据word 模板导出
 */
public class WordPOI {


    /**
     * 作者  json
     * 时间  2018/4/24 9:45
     * 描述  通过XWPFWordExtractor访问XWPFDocument的内容
     **/
    public void ReadWordValue(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String baseUrl = WordPOI.class.getResource("/").getPath() + "templet/wordDemo.docx";
        File file = new File(baseUrl);
        FileInputStream in = new FileInputStream(file);
        XWPFDocument xwpfDocument = new XWPFDocument(in);
        try {
            XWPFWordExtractor xwpfWordExtractor = new XWPFWordExtractor(xwpfDocument);
            POIXMLProperties.CoreProperties coreProperties = xwpfWordExtractor.getCoreProperties();
            this.printCoreProperties(coreProperties);
            String text = xwpfWordExtractor.getText();
            System.out.println(text);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            in.close();
        }

    }

    /**
     * 作者  json
     * 时间  2018/4/24 11:11
     * 描述 读取 CoreProperties文件
     **/

    private void printCoreProperties(POIXMLProperties.CoreProperties coreProps) {
        System.out.println(coreProps.getCategory());   //分类
        System.out.println(coreProps.getCreator()); //创建者
        System.out.println(coreProps.getCreated()); //创建时间
        System.out.println(coreProps.getTitle());   //标题
    }

    /**
     * 作者  json
     * 时间  2018/4/24 11:24
     * 描述 通过XWPFDocument对内容进行访问。对于XWPF文档而言，用这种方式进行读操作更佳。
     **/
    public void testReadByDoc() throws IOException {
        String baseUrl = WordPOI.class.getResource("/").getPath() + "templet/wordDemo.docx";
        File file = new File(baseUrl);
        FileInputStream in = null;
        try {
            in = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        XWPFDocument doc = new XWPFDocument(in);
        List<XWPFParagraph> paragraphs = doc.getParagraphs();
        for (XWPFParagraph paragraph : paragraphs) {
            String text = paragraph.getText();
            System.out.println("遍历输出每段的内容:" + text);
        }
        //获取文档中所有的表格
        List<XWPFTable> tables = doc.getTables();
        List<XWPFTableRow> rows;
        List<XWPFTableCell> cells;
        for (XWPFTable table : tables) {
            //表格属性
//              CTTblPr pr = table.getCTTbl().getTblPr();
            //获取表格对应的行
            rows = table.getRows();
            for (XWPFTableRow row : rows) {
                //获取行对应的单元格
                cells = row.getTableCells();
                for (XWPFTableCell cell : cells) {
                    System.out.println(cell.getText());
                }

            }

        }
        in.close();
    }

    /**
    *
    * 作者  json
    * 时间  2018/4/24 14:42
    * 描述 用一个docx文档作为模板，然后替换其中的内容，再写入目标文档中。
    *
    **/
    public void TemplateWrite(Map params,HttpServletRequest request,HttpServletResponse response){

        String baseUrl = WordPOI.class.getResource("/").getPath() + "templet/wordDemo.docx";
        File file = new File(baseUrl);
        FileInputStream in = null;
        try {
            in = new FileInputStream(file);
            XWPFDocument doc = new XWPFDocument(in);
            //替换段落里面的变量
            this.replaceInPara(doc, params);
            //替换表格里面的变量
            this.replaceInTable(doc, params);
            FileOutputStream outputStream=new FileOutputStream("D:\\poi.doc");
            doc.write(outputStream);
            outputStream.close();
            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    /**
     * 替换段落里面的变量
     * @param doc 要替换的文档
     * @param params 参数
     */
    private void replaceInPara(XWPFDocument doc, Map<String, Object> params) {
        Iterator<XWPFParagraph> iterator = doc.getParagraphsIterator();
        XWPFParagraph para;
        while (iterator.hasNext()) {
            para = iterator.next();
            this.replaceInPara(para, params);
        }
    }

    /**
     * 替换段落里面的变量
     * @param para 要替换的段落
     * @param params 参数
     */
    private void replaceInPara(XWPFParagraph para, Map<String, Object> params) {
        List<XWPFRun> runs;
        Matcher matcher;
        if (this.matcher(para.getParagraphText()).find()) {
            runs = para.getRuns();
            for (int i=0; i<runs.size(); i++) {
                XWPFRun run = runs.get(i);
                String runText = run.toString();
                matcher = this.matcher(runText);
                if (matcher.find()) {
                    while ((matcher = this.matcher(runText)).find()) {
                        runText = matcher.replaceFirst(String.valueOf(params.get(matcher.group(1))));
                    }
                    //直接调用XWPFRun的setText()方法设置文本时，在底层会重新创建一个XWPFRun，把文本附加在当前文本后面，
                    //所以我们不能直接设值，需要先删除当前run,然后再自己手动插入一个新的run。
                    para.removeRun(i);
                    para.insertNewRun(i).setText(runText);
                }
            }
        }
    }

    /**
     * 替换表格里面的变量
     * @param doc 要替换的文档
     * @param params 参数
     */
    private void replaceInTable(XWPFDocument doc, Map<String, Object> params) {
        Iterator<XWPFTable> iterator = doc.getTablesIterator();
        XWPFTable table;
        List<XWPFTableRow> rows;
        List<XWPFTableCell> cells;
        List<XWPFParagraph> paras;
        while (iterator.hasNext()) {
            table = iterator.next();
            rows = table.getRows();
            for (XWPFTableRow row : rows) {
                cells = row.getTableCells();
                for (XWPFTableCell cell : cells) {
                    paras = cell.getParagraphs();
                    for (XWPFParagraph para : paras) {
                        this.replaceInPara(para, params);
                    }
                }
            }
        }
    }

    /**
     * 正则匹配字符串
     * @param str
     * @return
     */
    private Matcher matcher(String str) {
        Pattern pattern = Pattern.compile("\\$\\{(.+?)\\}", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(str);
        return matcher;
    }


}
