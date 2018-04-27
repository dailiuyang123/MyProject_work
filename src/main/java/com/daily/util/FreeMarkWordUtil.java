package com.daily.util;

import freemarker.template.Configuration;
import freemarker.template.Template;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.Map;

/**
 * Created by json on 2018/4/27.
 * Describe: 利用 apache 的 freemark 模板导出 word文档
 */
public class FreeMarkWordUtil {


    /**
    *
    * 作者  json
    * 时间  2018/4/27 15:00
    * 描述 data + templet+ io
    * 导出工具类
    **/

    public void  FreeMarkFactory(HttpServletRequest request, HttpServletResponse response, Map data){
        //模拟路径
        //注意只需要获取 ftl模板所在的文件夹的位置，并不需要获取这个ftl文件的具体位置
        String temURL=FreeMarkWordUtil.class.getResource("/").getPath()+"templet/";
       //D:\home\MyProject\target\classes\templet\
        //读取模板
        File tempfile=new File(temURL);
        Configuration configuration=new Configuration();
        configuration.setDefaultEncoding("utf-8");

        //文件初始化
        File file = null;
        InputStream fin = null;
        ServletOutputStream out = null;
        String title="freemark导出";
        try {
            configuration.setDirectoryForTemplateLoading(tempfile);
            Template freeMarker = configuration.getTemplate("freeMarker.ftl");
            file = createDoc(data,freeMarker);
            fin = new FileInputStream(file);
            response.setCharacterEncoding("utf-8");
            response.setContentType("application/msword");
            String fileName = title+".doc";
            response.setHeader("Content-Disposition", "attachment;filename="+fileName.concat(String.valueOf(URLEncoder.encode(fileName, "UTF-8"))));
            out = response.getOutputStream();
            //创建一个缓冲区
            byte buffer[] = new byte[1024];
            //判断输入流中的数据是否已经读完的标识
            int len = 0;
            //循环将输入流读入到缓冲区当中，(len=in.read(buffer))>0就表示in里面还有数据
            while ((len = fin.read(buffer)) > 0) {
                //使用FileOutputStream输出流将缓冲区的数据写入到指定的目录(savePath + "\\" + filename)当中
                out.write(buffer, 0, len);
            }
            fin.close();
            out.close();
            file.delete(); //删除临时文件
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    private static File createDoc(Map<?, ?> dataMap, Template template) {
        String name =  "sellPlan.doc";
        File f = new File(name);
        Template t = template;
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




}
