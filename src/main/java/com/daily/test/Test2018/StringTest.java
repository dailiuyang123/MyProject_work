package com.daily.test.Test2018;

import com.daily.mybatis.entity.User;

import java.util.*;

/**
 * Created by json on 2018/4/4.
 * Describe:
 */
public class StringTest {

    public static void main(String[] args) {

        //list 集合取 交，并，补，差集
        List list1 =new ArrayList();
        list1.add("1111");
        list1.add("2222");
        list1.add("3333");

        List list2 =new ArrayList();
        list2.add("3333");
        list2.add("4444");
        list2.add("5555");

        //并集
        //list1.addAll(list2);
        //交集
        //list1.retainAll(list2);
        //差集
        list1.removeAll(list2);
        //无重复并集
//        list2.removeAll(list1);
//        list1.addAll(list2);

        Iterator<String> it=list1.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());

        }


        String userAgent="Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36 Edge/16.16299";
        if(userAgent.contains("Edge")){
            System.out.println("包含头");
        }


    }

}
