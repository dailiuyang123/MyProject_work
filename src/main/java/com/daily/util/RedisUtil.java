package com.daily.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * Created by json on 2018/3/30.
 * Describe:
 */
public class RedisUtil {

    /**
    *
    * 作者  json
    * 时间  2018/3/30 17:52
    * 描述 获取Jedis实例
    *
    **/
    public static Jedis getJedis(){
        ApplicationContext context = new ClassPathXmlApplicationContext("/spring/spring-redis.xml");
        JedisPool bean = context.getBean(JedisPool.class);
        Jedis jedis = bean.getResource();    //获取 redis 实例对象
        return jedis;
    }
}
