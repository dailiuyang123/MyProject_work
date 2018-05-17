package com.daily.test.ThreadTask;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

/**
 * Created by json on 2018/5/7.
 * Describe:
 */
public class ThreadOne {

    private static int c = 0;
    public synchronized static void increment() {
        c++;
    }
    public synchronized static void decrement() {
        c--;
    }
    public synchronized static int value() {
        return c;
    }

    public static void main(String[] args) {
//        TestSleep();
//        TestIntercept();
        int value = value();
        System.out.println(value);
        ACIDThread();
    }




    /**
     * 作者  json
     * 时间  2018/5/7 14:57
     * 描述 sleep()方法 由于操作系统的限制休眠时间并不能保证十分精确。休眠周期可以被interrups所终止，
     **/
    public static void TestSleep() {
        String[] data = {"孙悟空", "猪八戒", "沙悟净", "白龙马"};

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0;
                     i < data.length;
                     i++) {
                    //Pause for 4 seconds
                    try {
                        Thread.sleep(4000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    //Print a message
                    System.out.println(data[i]);
                    System.out.println(System.currentTimeMillis());
                }
            }

        });
        thread.start();

    }


    /**
     * 作者  json
     * 时间  2018/5/7 15:00
     * 描述
     **/
    public static void TestIntercept() {
        String[] data = {"孙悟空", "猪八戒", "沙悟净", "白龙马"};

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0;
                     i < data.length;
                     i++) {
                    //Pause for 4 seconds
                    try {
                        Thread.sleep(4000);
                    } catch (InterruptedException e) {
                        return;
                    }
                    //Print a message
                    System.out.println(data[i]);
                    System.out.println(System.currentTimeMillis());
                }
            }

        });
        thread.start();

    }


    public  static void ACIDThread(){
        for (int i=0;i<100;i++){
            Thread thread1=new Thread(new Runnable() {
                @Override
                public synchronized void run() {
                    increment();
                    int value = value();
                    System.out.println("线程A +1= "+value);
                }
            });
            Thread thread2=new Thread(new Runnable() {
                @Override
                public synchronized void run() {
                    decrement();
                    int value = value();
                    System.out.println("线程B -1= "+value);
                }
            });

            thread1.start();

            //thread2.start();
        }

    }



}
