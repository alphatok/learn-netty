package com.brzyang.netty.frame;

import com.brzyang.netty.base.BaseNettyClient;
import com.brzyang.netty.frame.handler.FrameClientHandler;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class StringNettyClient extends BaseNettyClient {
    public static void main(String[] args) throws InterruptedException {
        List<ChannelHandler> channels = new ArrayList<>();
//        channels.add(new LineBasedFrameDecoder());
//        channels.add(new FixedLengthFrameDecoder());
//        channels.add(new DelimiterBasedFrameDecoder());
        channels.add(new FrameClientHandler());
        Channel channel = initClient(channels);

        while (true) {
            channel.writeAndFlush(new Date() + ": hello world!");
            Thread.sleep(2000);
        }
    }
}
