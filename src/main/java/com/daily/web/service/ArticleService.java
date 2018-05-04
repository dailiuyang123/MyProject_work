package com.daily.web.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.daily.mybatis.dao.ArticleMapper;
import com.daily.mybatis.entity.*;
import com.daily.util.IdGenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Azir on 2018/5/5.
 */
@Transactional
@Service
public class ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    /**
    *  Author: 代刘洋
    *  Time：  2018/5/5  0:11
    *  Title:
    *  Param: 
    *  Response:    
    *
    */
    public void createArticle(Map param) {
        String jsonString = JSON.toJSONString(param);
        Article article = JSON.parseObject(jsonString, Article.class);
        String uuid = IdGenUtils.UUID();
        article.setId(uuid);
        article.setCreateTime(new Date());
        articleMapper.insertSelective(article);
    }

    /**
    *  Author: 代刘洋
    *  Time：  2018/5/5  0:11
    *  Title: 
    *  Param: 
    *  Response:    
    *
    */
    public Map ArticleList(Map param) {
        ArticleExample articleExample=new ArticleExample();
        articleExample.setOrderByClause("create_time desc");
        List<Article> articles = articleMapper.selectByExample(articleExample);
        Map resultMap = new HashMap();
        resultMap.put("list", articles);
        return resultMap;
    }


}
