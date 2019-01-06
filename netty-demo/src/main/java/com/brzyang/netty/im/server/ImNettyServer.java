package com.brzyang.netty.im.server;

import com.brzyang.netty.base.BaseNettyServer;
import com.brzyang.netty.chainedhandler.PacketDecoder;
import com.brzyang.netty.chainedhandler.PacketEncoder;
import com.brzyang.netty.im.handler.ImAuthHandler;
import com.brzyang.netty.im.handler.ImLoginRequestHandler;
import com.brzyang.netty.im.handler.ImMessageRequestHandler;
import com.brzyang.netty.im.handler.Splitter;
import io.netty.channel.ChannelHandler;

import java.util.ArrayList;
import java.util.List;

public class ImNettyServer extends BaseNettyServer {
    public static void main(String[] args) {
        List<ChannelHandler> channels = new ArrayList<>();
        channels.add(new Splitter());
        channels.add(new PacketDecoder());
        channels.add(new ImLoginRequestHandler());
        channels.add(new ImAuthHandler());
        channels.add(new ImMessageRequestHandler());
        channels.add(new PacketEncoder());
        initNettyServer(channels);
    }
}
