package com.daily.util;

import com.alibaba.fastjson.JSONObject;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by 11851 on 2017/11/11.
 * 将request 请求参数 转换成 Map 数据结构
 */
public class ParamUtils {

    public static Map getParam(HttpServletRequest request) {
        // 参数Map
        Map properties = request.getParameterMap();
        // 返回值Map
        Map returnMap = new HashMap();
        Iterator entries = properties.entrySet().iterator();
        Map.Entry entry;
        String name = "";
        String value = "";
        while (entries.hasNext()) {
            entry = (Map.Entry) entries.next();
            name = (String) entry.getKey();
            Object valueObj = entry.getValue();
            if (null == valueObj) {
                value = "";
            } else if (valueObj instanceof String[]) {
                String[] values = (String[]) valueObj;
                for (int i = 0; i < values.length; i++) {
                    value = values[i] + ",";
                }
                value = value.substring(0, value.length() - 1);
            } else {
                value = valueObj.toString();
            }
            returnMap.put(name, value);
        }
        return returnMap;
    }

    //获取参数
    public static Map getParams(HttpServletRequest request)  {
        Map paramMap=new HashMap();
        ServletInputStream inputStream = null;
        String contentType = request.getHeader("Content-Type").toString();
        if (contentType.contains("application/x-www-form-urlencoded")){
            Map param = getParam(request);
            return param;
        }
        else {
            try {
                inputStream = request.getInputStream();
                byte[] bytes = FileUtils.readFromStream(inputStream);
                String charEncoding = request.getCharacterEncoding();
                if (charEncoding == null) {
                    charEncoding = "UTF-8";
                }
                Map o = (Map)JSONObject.parseObject(bytes, Map.class);
                paramMap=o;
                return paramMap;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }



}
