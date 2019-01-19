package com.brzyang.netty.protocol;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestLog4j {
    private static Logger logger_ = LoggerFactory.getLogger(TestLog4j.class);


    public static void main(String[] args) {
//        BasicConfigurator.configure();
        logger_.info("info");
        logger_.debug("debug");
        logger_.trace("trace");
        logger_.error("error");


    }
}
