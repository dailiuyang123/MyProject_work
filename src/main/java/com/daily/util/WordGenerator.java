package com.daily.util;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

import com.lowagie.text.*;

import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.rtf.RtfWriter2;
import freemarker.template.Configuration;
import freemarker.template.Template;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class WordGenerator {
    private static Configuration configuration = null;
    private static Map<String, Template> allTemplates = null;

//    static {
//        configuration = new Configuration();
//        configuration.setDefaultEncoding("utf-8");
//        configuration.setClassForTemplateLoading(WordGenerator.class, "/com/daily/ftl");
//        allTemplates = new HashMap<String, Template>();   // Java 7 钻石语法
//        try {
//            Template template = configuration.getTemplate("resume.ftl");
//            allTemplates.put("resume", template);
//        } catch (IOException e) {
//            e.printStackTrace();
//            throw new RuntimeException(e);
//        }
//    }

    public WordGenerator(int a){

    }
    private WordGenerator() {
        throw new AssertionError();
    }

    public static File createDoc(Map<?, ?> dataMap, String type) {
        String name = "temp" + (int) (Math.random() * 100000) + ".doc";
        File f = new File(name);
        Template t = allTemplates.get(type);
        try {
            // 这个地方不能使用FileWriter因为需要指定编码类型否则生成的Word文档会因为有无法识别的编码而无法打开
            Writer w = new OutputStreamWriter(new FileOutputStream(f), "utf-8");
            t.process(dataMap, w);
            w.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        return f;
    }

    /**
    *
    * 作者  json
    * 时间  2018/4/9 18:13
    * 描述  Itext 技术实现先导出PDF后转为world文档 导出
    *
    **/

    public void  exportWorld(Map datamap, HttpServletRequest request, HttpServletResponse response){
        try {
            String templeUrl = "D:\\";

            //创建一个Document实例
            //页边距 上下左右
            Document document = new Document(PageSize.A4, 36, 72, 108, 180);

            //创建writer实例

            //生成doc文档
            RtfWriter2.getInstance(document, new FileOutputStream(templeUrl + "测试world" + ".doc"));

            document.open();
            //生成的报告名
            String fileName="测试world" + ".doc";
            // 设置中文字体
            BaseFont  bfChinese = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.WINANSI, BaseFont.NOT_EMBEDDED);

            // 标题字体风格
            Font titleFont = new Font(bfChinese, 14, Font.BOLD);

            //正文字体风格
            Font contFont=new Font(bfChinese,10);
            //标题名称
            Paragraph paragraph=new Paragraph("word 报告");
            paragraph.setAlignment(Element.ALIGN_CENTER);  //居中效果
            document.add(paragraph);
            // 换行
            Paragraph title1 = new Paragraph("\n");
            document.add(title1);

            //正文
            Paragraph content=new Paragraph("刺猬与🐍 刺猬与🐍刺猬与🐍刺猬与🐍 ",contFont);
            document.add(content);
            //插入图片
            //picUrl
            String picurl=WordGenerator.class.getResource("/")+"Pic/1.jpg";
            Image image = Image.getInstance(picurl);
            document.add(image);

            document.close();

            // 设置编码格式
            String userAgent = request.getHeader("User-Agent");
            if(userAgent.contains("Safari")){
                request.setCharacterEncoding("gbk");
                response.setContentType("application/octet-stream");
                response.setHeader("Content-Disposition", "attachment;fileName=" + new String(fileName.getBytes("UTF-8"), "iso-8859-1"));
            }else if(userAgent.contains("Windows NT")){
                request.setCharacterEncoding("UTF-8");
                response.setContentType("application/octet-stream");
                response.setHeader("Content-Disposition", "attachment;fileName=" + new String(fileName.getBytes("GBK"), "iso-8859-1"));
            }else{
                request.setCharacterEncoding("gbk");
                response.setContentType("text/html;charset=gbk");
                response.setHeader("Content-Disposition", "attachment;fileName=" + new String(fileName.getBytes("UTF-8"), "iso-8859-1"));
            }

            ServletOutputStream outputStream = response.getOutputStream();
            FileInputStream inputStream=new FileInputStream("D:\\"+"测试world" + ".doc");
            //创建一个缓冲区
            byte buffer[] = new byte[1024];
            //判断输入流中的数据是否已经读完的标识
            int len = 0;
            //循环将输入流读入到缓冲区当中，(len=in.read(buffer))>0就表示in里面还有数据
            while ((len = inputStream.read(buffer)) > 0) {
                //使用FileOutputStream输出流将缓冲区的数据写入到指定的目录(savePath + "\\" + filename)当中
                outputStream.write(buffer, 0, len);
            }
            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


}