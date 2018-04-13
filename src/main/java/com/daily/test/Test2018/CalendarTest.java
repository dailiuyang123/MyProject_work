package com.daily.test.Test2018;



import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by json on 2018/3/16.
 * Describe:java 日期处理包 calendar
 */
public class CalendarTest {

    public static void main(String[] args) throws ParseException {
        //当前日期
        Calendar rightnow=Calendar.getInstance();
        int i = rightnow.get(Calendar.YEAR);
        System.out.println("当前年份："+i);
        int i1 = rightnow.get(Calendar.MONTH)+1;
        System.out.println("当前月份："+i1);
        int i2 = rightnow.get(Calendar.DAY_OF_YEAR);
        System.out.println("今天是今年的第"+i2+"天");
        int i4 = rightnow.get(Calendar.DAY_OF_MONTH);
        System.out.println("按当前的日期："+i4);
        //Date 与 Calendar 相互转化
        //DATE --------> Calendar
        String time="2018-07-11";
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-mm-dd");
        Date date = sdf.parse(time);
        System.out.println(sdf.format(date));
        rightnow.setTime(date);
        int i3 = rightnow.get(Calendar.DAY_OF_MONTH);
        System.out.println(i3);
        //Calender----------->Date
        Date time1 = rightnow.getTime();
        System.out.println(time1);

    }


}
