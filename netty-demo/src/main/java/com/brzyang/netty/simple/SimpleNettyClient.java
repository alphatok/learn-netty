package com.brzyang.netty.simple;

import com.brzyang.netty.base.BaseNettyClient;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.handler.codec.string.StringEncoder;

import java.util.Collections;
import java.util.Date;
import java.util.List;

public class SimpleNettyClient extends BaseNettyClient {
    public static void main(String[] args) throws InterruptedException {
        List<ChannelHandler> channels = Collections.singletonList(new StringEncoder());
        Channel channel = initClient(channels);

        while (true) {
            channel.writeAndFlush(new Date() + ": hello world!");
            Thread.sleep(2000);
        }
    }
}
