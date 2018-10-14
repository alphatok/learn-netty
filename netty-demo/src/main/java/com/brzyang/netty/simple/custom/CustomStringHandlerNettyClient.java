package com.brzyang.netty.simple.custom;

import com.brzyang.netty.base.BaseNettyClient;
import com.brzyang.netty.simple.custom.handler.CustomStringClientHandler;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;

import java.util.Collections;
import java.util.List;

public class CustomStringHandlerNettyClient extends BaseNettyClient {

    public static void main(String[] args) throws InterruptedException {
        List<ChannelHandler> channels = Collections.singletonList(new CustomStringClientHandler());
        Channel channel = initClient(channels);
        // 连接成功之后，启动控制台线程
        startStringConsoleThread(channel);
    }


}
