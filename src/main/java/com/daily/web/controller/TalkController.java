package com.daily.web.controller;

import com.daily.mybatis.entity.JsonMessage;
import com.daily.util.ParamUtils;
import com.daily.web.service.TalkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by json on 2018/5/3.
 * Describe: 用户评论表
 */
@RestController
@RequestMapping("/talk")
public class TalkController {

    @Autowired
    private TalkService talkService;
    /**
    *
    * 作者  json
    * 时间  2018/5/3 11:26
    * 描述
    *
    **/
    @RequestMapping(value = "/createTalk",method = RequestMethod.POST)
    public JsonMessage  createTalk(HttpServletRequest request, HttpServletResponse response){
        JsonMessage jsonMessage=new JsonMessage();
        Map param = ParamUtils.getParam(request);
        try {
            talkService.createTalk(param);
            jsonMessage.setResponseCode("0");
        }catch (Exception e){
            e.printStackTrace();
            jsonMessage.setResponseCode("999");
            jsonMessage.setErrorMessage("系统内部错误，请联系管理员");
        }
        return jsonMessage;
    }

    /**
    *
    * 作者  json
    * 时间  2018/5/3 11:26
    * 描述
    *
    **/
    @RequestMapping(value = "talkList",method = RequestMethod.POST)
    public JsonMessage talkList(HttpServletRequest request,HttpServletResponse response){
        JsonMessage jsonMessage=new JsonMessage();
        Map param = ParamUtils.getParam(request);
        try {
            Map map = talkService.talkList(param);
            jsonMessage.setData(map);
            jsonMessage.setResponseCode("0");
        }catch (Exception e){
            e.printStackTrace();
            jsonMessage.setResponseCode("999");
            jsonMessage.setErrorMessage("error occure");
        }

        return jsonMessage;
    }



}
