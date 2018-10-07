package com.brzyang.netty.server;


import com.brzyang.netty.base.BaseNettyServer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.AttributeKey;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

import java.util.Collections;

public class NettyServer extends BaseNettyServer {

      /*
                ch.pipeline().addLast(new StringDecoder());
                // 责任链-StringDecoder转为String，后续handler处理String
                ch.pipeline().addLast(new SimpleChannelInboundHandler<String>() {
                    @Override
                    protected void channelRead0(ChannelHandlerContext ctx, String msg) {
                        System.out.println(msg);
                    }
                });
                */
//                ch.pipeline().addLast(new FirstServerHandler());


    public static void main(String[] args) {
       initNettyServer(Collections.singletonList(new ServerHandler()));

    }
}
