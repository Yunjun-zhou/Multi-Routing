package ToolUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeConvert {
    //将字符串转换为时间戳(秒)
    public static long getStringToDate(String time) {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Date date = new Date();

        try {
            date = sf.parse(time);

        } catch (ParseException e) {
            e.printStackTrace();

        }

        return (date.getTime()/1000);

    }

    public static String getDateToString(long time) {
        Date date = new Date(time*1000);
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sf.format(date);// 时间戳转换成时间
        //sf.format(new Date(Long.parseLong(String.valueOf(time))));
    }

}
