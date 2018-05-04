package com.daily.web.controller;

import com.daily.mybatis.entity.JsonMessage;
import com.daily.util.ParamUtils;
import com.daily.web.service.ArticleService;
import com.daily.web.service.TalkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by Azir on 2018/5/5.
 */

@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    /**
     * 作者  json
     * 时间  2018/5/3 11:26
     * 描述  cms /后台管理系统 添加文章。
     **/
    @RequestMapping(value = "/createArticle", method = RequestMethod.POST)
    public JsonMessage createArticle(HttpServletRequest request, HttpServletResponse response) {
        JsonMessage jsonMessage = new JsonMessage();
        Map param = ParamUtils.getParam(request);
        try {
            articleService.createArticle(param);
            jsonMessage.setResponseCode("0");
        } catch (Exception e) {
            e.printStackTrace();
            jsonMessage.setResponseCode("999");
            jsonMessage.setErrorMessage("系统内部错误，请联系管理员");
        }
        return jsonMessage;
    }

    /**
     * 作者  json
     * 时间  2018/5/3 11:26
     * 描述   cms /后台管理系统 添加文章。
     **/
    @RequestMapping(value = "ArticleList", method = RequestMethod.POST)
    public JsonMessage ArticleList(HttpServletRequest request, HttpServletResponse response) {
        JsonMessage jsonMessage = new JsonMessage();
        Map param = ParamUtils.getParam(request);
        try {
            Map map = articleService.ArticleList(param);
            jsonMessage.setData(map);
            jsonMessage.setResponseCode("0");
        } catch (Exception e) {
            e.printStackTrace();
            jsonMessage.setResponseCode("999");
            jsonMessage.setErrorMessage("error occure");
        }

        return jsonMessage;
    }

}
