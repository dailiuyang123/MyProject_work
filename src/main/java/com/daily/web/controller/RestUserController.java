package com.daily.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.daily.mybatis.entity.JsonMessage;
import com.daily.mybatis.entity.User;
import com.daily.util.ParamUtils;
import com.daily.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by json on 2018/4/16.
 * Describe: 遵从restful 风格的 spring mvc 的controller
 */
@RestController
@RequestMapping("/restUser")
public class RestUserController {

    @Autowired
    private UserService userService;

    /**
     * 查找所有用户
     * @return
     */
    @RequestMapping(value = "/findAll.do", method = RequestMethod.POST)
    public JsonMessage findAll(HttpServletRequest request, HttpServletResponse response, User user) {
        JsonMessage jsonMessage = new JsonMessage();
        //工具类
        Map paramMap = ParamUtils.getParam(request);
        System.out.println(user.toString());
        Map<String, Object> map = new HashMap<String, Object>();
        jsonMessage.setData(map);
        String requestURI = request.getRequestURI();
        jsonMessage.setResponseCode("0");
        return jsonMessage;

    }

    /**
     * 用户登录
     *
     * @param request
     * @param response
     */
    @RequestMapping(value = "login.do", method = RequestMethod.POST)
    public JsonMessage login(HttpServletRequest request, HttpServletResponse response) {
        Map map = ParamUtils.getParam(request);
        JsonMessage jsonMessage=new JsonMessage();
        String userstring = JSONObject.toJSONString(map);
        User user = JSONObject.parseObject(userstring, User.class);
        User userByUser = userService.getUserByUser(user);
        if (userByUser!=null){
            Map resultMap=new HashMap();
            resultMap.put("data",userByUser);
            jsonMessage.setResponseCode("0");
        }else {
            jsonMessage.setResponseCode("998");
            jsonMessage.setErrorMessage("内部错误");
        }
        return jsonMessage;
    }


    /**
     *  Author: 代刘洋
     *  Time：  2018/2/3  14:51
     *  Title:   用户注册
     *  Param:
     *  Response:
     *
     */

    @RequestMapping(value = "addUser",method = RequestMethod.POST)
    public JsonMessage addUser(HttpServletRequest request,HttpServletResponse response){
        JsonMessage jsonMessage=new JsonMessage();
        Map map = ParamUtils.getParam(request);
        try {
            userService.addUser(map);
            jsonMessage.setResponseCode("0");
        }catch (Exception e){
            e.printStackTrace();
            jsonMessage.setResponseCode("998");
            jsonMessage.setErrorMessage("内部错误");
        }
        return jsonMessage;
    }

    /**
     *
     * 作者  json
     * 时间  2018/4/2 17:32
     * 描述  获取用户
     *
     **/
    @RequestMapping(value = "findUserById",method = RequestMethod.POST)
    public JsonMessage findUserById(HttpServletRequest request,HttpServletResponse response){
        JsonMessage jsonMessage=new JsonMessage();
        String id = request.getParameter("id");
        Map param = ParamUtils.getParams(request);
        try {
            Map  result = userService.getUserById(param);
            jsonMessage.setData(result);
            jsonMessage.setErrorMessage("0");
            return jsonMessage;
        }catch (Exception e){
            e.printStackTrace();
            jsonMessage.setResponseCode("998");
            jsonMessage.setErrorMessage("内部错误");
        }
        return jsonMessage;
    }


}

