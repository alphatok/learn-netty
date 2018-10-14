package com.brzyang.netty.client;

import com.brzyang.netty.base.BaseNettyClient;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;

import java.util.Collections;
import java.util.List;

public class NettyClient extends BaseNettyClient {

    private final static int MAX_RETRY = 10;
    public static void main(String[] args) throws InterruptedException {
//        ch.pipeline().addLast(new StringEncoder());
//                        ch.pipeline().addLast(new FrameClientHandler());

        List<ChannelHandler> channels = Collections.singletonList(new ClientHandler());
        Channel channel = initClient(channels);
        // 连接成功之后，启动控制台线程
        startStringConsoleThread(channel);
    }
}
