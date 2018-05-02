package com.daily.web.service;

import com.daily.mybatis.dao.ShopMapper;
import com.daily.mybatis.entity.Shop;
import com.daily.mybatis.entity.ShopExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by json on 2018/5/2.
 * Describe: 商品详情表
 */
@Service
@Transactional
public class ShopService {

@Autowired
    private ShopMapper shopMapper;

    public Map getShopDetailById(Map paraMap){
        String id = paraMap.get("id").toString();
        ShopExample shopExample=new ShopExample();
        ShopExample.Criteria criteria = shopExample.createCriteria().andIdEqualTo(id).andDeleteStatusEqualTo("1");
        List<Shop> shops = shopMapper.selectByExample(shopExample);
        Map resultMap=new HashMap();
        resultMap.put("list",shops);
        return resultMap;
    }




}
