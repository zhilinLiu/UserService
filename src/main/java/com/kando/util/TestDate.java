package com.kando.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TestDate {
	public Date getDate() {
		Date now = new Date(); 
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");//可以方便地修改日期格式
		dateFormat.format( now ); 
		return now; 
	}
	
}
