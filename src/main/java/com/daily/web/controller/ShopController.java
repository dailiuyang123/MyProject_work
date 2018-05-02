package com.daily.web.controller;

import com.daily.mybatis.entity.JsonMessage;
import com.daily.util.ParamUtils;
import com.daily.web.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by json on 2018/5/2.
 * Describe: 获取商品
 */
@RestController
@RequestMapping("/shop")
public class ShopController {

    @Autowired
    private ShopService shopService;

    @RequestMapping(value = "/getShopById",method = RequestMethod.POST)
    public JsonMessage getShopById(HttpServletRequest request, HttpServletResponse response){
        JsonMessage jsonMessage=new JsonMessage();
        Map param = ParamUtils.getParam(request);
        try {
            Map shopDetailById = shopService.getShopDetailById(param);
            jsonMessage.setData(shopDetailById);
            jsonMessage.setResponseCode("0");
        }catch (Exception e){
            e.printStackTrace();
            jsonMessage.setResponseCode("998");
            jsonMessage.setErrorMessage("内部错误");
        }
        return jsonMessage;
    }


}
