package com.brzyang.netty.channel;

import com.brzyang.netty.base.BaseNettyClient;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.handler.codec.string.StringEncoder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class MultiChannelNettyClient extends BaseNettyClient {
    public static void main(String[] args) throws InterruptedException {
        List<ChannelHandler> channels = new ArrayList<>();
//        channels.add(new InBoundHandlerA());
//        channels.add(new InBoundHandlerB());
//        channels.add(new InBoundHandlerC());
//        channels.add(new OutBoundHandlerA());
//        channels.add(new OutBoundHandlerB());
//        channels.add(new OutBoundHandlerC());
        channels.add(new StringEncoder());
        Channel channel = initClient(channels);

        while (true) {
            channel.writeAndFlush(new Date() + ": hello world!");
            Thread.sleep(2000);
        }
    }
}
