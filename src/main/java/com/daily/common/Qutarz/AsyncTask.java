package com.daily.common.Qutarz;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by json on 2018/5/7.
 * Describe: Spring  异步任务，@Async
 */
//交由spring factory 管理
@Configuration
@EnableAsync
public class AsyncTask {

    @Async
    public void dosomething() {
        Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {
                Date now=new Date();
                System.out.println("当前时间："+now.toString()+"线程已经启动");
                long l = System.currentTimeMillis();
                System.out.println(l);
            }
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.start();

    }


}
