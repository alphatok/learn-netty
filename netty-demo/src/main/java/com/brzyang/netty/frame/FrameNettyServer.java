package com.brzyang.netty.frame;

import com.brzyang.netty.server.ServerHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

public class FrameNettyServer {

    public static void main(String[] args) {
        ServerBootstrap serverBootstrap = new ServerBootstrap();

        NioEventLoopGroup boss = new NioEventLoopGroup();
        NioEventLoopGroup worker = new NioEventLoopGroup();
        serverBootstrap
                .group(boss, worker)
                .channel(NioServerSocketChannel.class);
        serverBootstrap.childHandler(new ChannelInitializer<NioSocketChannel>() {
            protected void initChannel(NioSocketChannel ch) {
                ch.pipeline().addLast(new FrameServerHandler());
            }
        });

        serverBootstrap.bind(8000).addListener(new GenericFutureListener<Future<? super Void>>() {
            public void operationComplete(Future<? super Void> future) {
                if (future.isSuccess()) {
                    System.out.println("端口[" + 8000 + "]绑定成功!");
                } else {
                    System.err.println("端口[" + 8000 + "]绑定失败!");
                }
            }
        });
    }
}
