package com.brzyang.netty.simple.custom;


import com.brzyang.netty.base.BaseNettyServer;
import com.brzyang.netty.simple.custom.handler.CustomStringServerHandler;

import java.util.Collections;

public class CustomStringHandlerNettyServer extends BaseNettyServer {

    public static void main(String[] args) {
       initNettyServer(Collections.singletonList(new CustomStringServerHandler()));
    }
}
