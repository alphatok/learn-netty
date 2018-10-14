package com.brzyang.netty.one2one;


import com.brzyang.netty.base.BaseNettyServer;
import io.netty.channel.ChannelHandler;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

import java.util.ArrayList;
import java.util.List;

public class ProtocolOne2OneNettyServer extends BaseNettyServer {

    public static void main(String[] args) {
        List<ChannelHandler> channels = new ArrayList<>();
        channels.add(new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 7, 4));
        channels.add(new ProtocolServerHandler());
       initNettyServer(channels);

    }
}
