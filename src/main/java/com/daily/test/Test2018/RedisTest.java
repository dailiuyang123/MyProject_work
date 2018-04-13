package com.daily.test.Test2018;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * Created by json on 2018/3/26.
 * Describe:redis 测试
 */
public class RedisTest {
    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("/spring/spring-redis.xml");
        JedisPool bean = context.getBean(JedisPool.class);
        Jedis jedis = bean.getResource();    //获取 redis 实例对象
        //jedis.append("tom","jerry");
        jedis.set("beijing","chaoyang");
        jedis.expire("tom",5);
        String tom = jedis.get("tom");
        System.out.println(tom);
        jedis.close();
        boolean closed = bean.isClosed();       //获取连接池 是否关闭
        System.out.println(closed);


    }


}
