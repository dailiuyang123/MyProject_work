package com.daily.web.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.daily.mybatis.dao.ShopMapper;
import com.daily.mybatis.dao.TalkMapper;
import com.daily.mybatis.entity.Shop;
import com.daily.mybatis.entity.Talk;
import com.daily.mybatis.entity.TalkExample;
import com.daily.util.IdGenUtils;
import org.bouncycastle.asn1.cms.PasswordRecipientInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by json on 2018/5/3.
 * Describe:
 */
@Transactional
@Service
public class TalkService {

    @Autowired
    private TalkMapper talkMapper;
    @Autowired
    private ShopMapper shopMapper;


    public void createTalk(Map param){
        String jsonString = JSON.toJSONString(param);
        Talk talk = JSON.parseObject(jsonString, Talk.class);
        String uuid = IdGenUtils.UUID();
        talk.setId(uuid);
        talk.setCreateTime(new Date());
        talkMapper.insertSelective(talk);
    }

    public Map talkList(Map param){
        TalkExample talkExample=new TalkExample();
        TalkExample.Criteria criteria = talkExample.createCriteria();
        if (param.containsKey("userName")){
            criteria.andCreateUserNameEqualTo(param.get("userName").toString());
        }
        List<Talk> talks = talkMapper.selectByExample(talkExample);
        String jsonString = JSONArray.toJSONString(talks);
        List<Map> list = JSONArray.parseArray(jsonString, Map.class);
        for (Map map : list) {
            String shopId = map.get("shopId").toString();
            Shop shop = shopMapper.selectByPrimaryKey(shopId);
            map.put("shopName",shop.getShopName());
            map.put("shopPictrue",shop.getShopPictrue());
        }
        Map resultMap=new HashMap();
        resultMap.put("list",list);
        return  resultMap;
    }


}
