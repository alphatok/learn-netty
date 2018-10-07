package com.brzyang.netty.base;

import com.brzyang.netty.server.ServerHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.AttributeKey;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

import java.util.List;

public class BaseNettyServer {

    public static ServerBootstrap initNettyServer(List<ChannelHandler> handlers){
        ServerBootstrap serverBootstrap = new ServerBootstrap();

        NioEventLoopGroup boss = new NioEventLoopGroup();
        NioEventLoopGroup worker = new NioEventLoopGroup();
        serverBootstrap
                .group(boss, worker)
                // BIO OioServerSocketChannel
                // NIO NioServerSocketChannel NioSocketChannel SocketChannel
                .channel(NioServerSocketChannel.class)
                .handler(new ChannelInitializer<NioServerSocketChannel>() {
                    @Override
                    protected void initChannel(NioServerSocketChannel ch) throws Exception {
                        System.out.println("服务端启动中, 通常不需要");
                    }
                });

        serverBootstrap.attr(AttributeKey.newInstance("serverName"), "nettyServer");

        serverBootstrap.childHandler(new ChannelInitializer<NioSocketChannel>() {
            protected void initChannel(NioSocketChannel ch) {
                for (ChannelHandler handler : handlers) {
                    ch.pipeline().addLast(handler);
                }
            }
        });
        serverBootstrap.childAttr(AttributeKey.newInstance("clientKey"), "clientValue");
        serverBootstrap
                // 开启TCP底层心跳机制
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                // 禁用Nagle算法（合并多个TCP包，MSS）
                .childOption(ChannelOption.TCP_NODELAY, true);


        // TODO https://blog.csdn.net/linuu/article/list/3
        bind(serverBootstrap, 8000);

        return serverBootstrap;
    }

    private static void bind(final ServerBootstrap serverBootstrap, final int port) {
        serverBootstrap.bind(port).addListener(new GenericFutureListener<Future<? super Void>>() {
            public void operationComplete(Future<? super Void> future) {
                if (future.isSuccess()) {
                    System.out.println("端口[" + port + "]绑定成功!");
                } else {
                    System.err.println("端口[" + port + "]绑定失败!");
                    bind(serverBootstrap, port + 1);
                }
            }
        });
    }
}
