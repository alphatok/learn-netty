package com.brzyang.netty.util;

public class StringUtil {
    private final static String EMPTY_STR = "";

    public static String nonNullTrim(String str){
        if (str == null) {
            return EMPTY_STR;
        }

        return str.trim();
    }
}
