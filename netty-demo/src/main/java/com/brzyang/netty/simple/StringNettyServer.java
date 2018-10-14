package com.brzyang.netty.simple;

import com.brzyang.netty.base.BaseNettyServer;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.string.StringDecoder;

import java.util.ArrayList;
import java.util.List;

public class StringNettyServer extends BaseNettyServer {

    public static void main(String[] args) {
        List<ChannelHandler> channels = new ArrayList();
        channels.add(new StringDecoder());
        channels.add(new SimpleChannelInboundHandler<String>() {
            @Override
            protected void channelRead0(ChannelHandlerContext ctx, String msg) {
                System.out.println(msg);
            }
        });


        initNettyServer(channels);
    }
}
