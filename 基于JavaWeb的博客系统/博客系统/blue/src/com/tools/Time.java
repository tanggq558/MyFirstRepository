package com.tools;
//ʱ�䴦����
import java.util.Date;
import java.text.SimpleDateFormat;

public class Time {
	public static String getTime(){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time=sdf.format(new Date());
		return time;
	}
	public static String getNoFormatTime(){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmsshh");
		String time=sdf.format(new Date());
		return time;
	}
}
