package com.brzyang.netty.util;

import java.util.Random;

public class IdUtil {
    public static String randomId() {
        return String.valueOf(Math.abs(new Random().nextLong()));
    }
}
