package com.daily.web.controller;

import com.daily.util.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by Azir on 2017/11/21.
 */

@Controller
@RequestMapping("/files")
public class FileController {

    /**
     * 文件上传
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public void uploadFiles(HttpServletRequest request, HttpServletResponse response) {
        FileUtils fileUtils=new FileUtils();
        Map map = fileUtils.FileUpload(request);
    }
    /**
    *
    * 作者  json
    * 时间  2018/4/2 10:49
    * 描述  图片压缩/上传
    *
    **/
    @RequestMapping(value ="/uploadImage",method = RequestMethod.POST)
    public void  uoloadImage(HttpServletRequest request,HttpServletResponse response){
        FileUtils fileUtils=new FileUtils();
        fileUtils.ImageZip(request,response);
    }
    
}
