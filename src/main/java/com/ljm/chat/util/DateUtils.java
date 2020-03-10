package com.ljm.chat.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Dominick Li
 * @createTime 2020/3/6 20:54
 * @description 自定义日期工具类
 **/
public class DateUtils {

    private DateUtils(){

    }

    private  final static SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");

    public static String getDayNow(long lastTime){
        DateFormat df=new SimpleDateFormat("dd");
        Integer dd=Integer.parseInt(df.format(new Date()));
        Integer d2=Integer.parseInt(df.format(lastTime));
        DateFormat d3=new SimpleDateFormat("HH:mm");
        String date3=d3.format(lastTime);
        String lastDay=null;
        if(dd-d2==1){
            lastDay="昨天  "+date3;
        }else if(dd-d2==2){
            lastDay="前天  "+date3;
        }else if(dd-d2==0){
            lastDay=date3;
        }else{
            DateFormat d=new SimpleDateFormat("MM/dd");
            lastDay=d.format(lastTime)+" "+date3;
        }
        return lastDay;
    }

    public static String timesToDateStr(long time){
        return sdf.format(new Date(time));
    }
}
