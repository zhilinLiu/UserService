package com.kando.util;

public class Random {
	public static String getRandom() {
		        String num = "";
		        for (int i = 0 ; i < 4 ; i ++) {
		            num = num + String.valueOf((int) Math.floor(Math.random() * 9 + 1));
		        }
		        return num;
		}
}

