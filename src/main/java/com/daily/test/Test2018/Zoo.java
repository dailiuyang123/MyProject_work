package com.daily.test.Test2018;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by json on 2018/5/17.
 * Describe:
 */
public class Zoo {
    @Autowired(required=false)
    private Tiger tiger;
    @Autowired(required=false)
    private Monkey monkey;

//    public Tiger getTiger() {
//        return tiger;
//    }
//
//    public void setTiger(Tiger tiger) {
//        this.tiger = tiger;
//    }
//
//    public Monkey getMonkey() {
//        return monkey;
//    }
//
//    public void setMonkey(Monkey monkey) {
//        this.monkey = monkey;
//    }

    public String toString() {
        return tiger + "\n" + monkey;
    }

}
