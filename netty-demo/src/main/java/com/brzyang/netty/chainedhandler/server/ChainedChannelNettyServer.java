package com.brzyang.netty.chainedhandler.server;

import com.brzyang.netty.base.BaseNettyServer;
import com.brzyang.netty.chainedhandler.PacketDecoder;
import com.brzyang.netty.chainedhandler.PacketEncoder;
import io.netty.channel.ChannelHandler;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

import java.util.ArrayList;
import java.util.List;

public class ChainedChannelNettyServer extends BaseNettyServer {

    public static void main(String[] args) {
        List<ChannelHandler> channels = new ArrayList<>();
        channels.add(new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 7, 4));
        channels.add(new PacketDecoder());
        channels.add(new LoginRequestHandler());
        channels.add(new MessageRequestHandler());
        channels.add(new PacketEncoder());
        initNettyServer(channels);
    }
}
