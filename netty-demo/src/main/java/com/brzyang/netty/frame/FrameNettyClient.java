package com.brzyang.netty.frame;

import com.brzyang.netty.base.BaseNettyClient;
import io.netty.channel.ChannelHandler;

import java.util.Collections;
import java.util.List;

public class FrameNettyClient extends BaseNettyClient {
    public static void main(String[] args) throws InterruptedException {
        List<ChannelHandler> channels = Collections.singletonList(new FrameClientHandler());
        initClient(channels);
    }
}
