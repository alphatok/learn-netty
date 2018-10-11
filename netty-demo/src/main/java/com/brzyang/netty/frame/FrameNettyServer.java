package com.brzyang.netty.frame;

import com.brzyang.netty.base.BaseNettyServer;
import io.netty.channel.ChannelHandler;

import java.util.ArrayList;
import java.util.List;

public class FrameNettyServer extends BaseNettyServer {

    public static void main(String[] args) {
        List<ChannelHandler> channels = new ArrayList();
        channels.add(new FrameServerHandler());

        initNettyServer(channels);
    }
}
