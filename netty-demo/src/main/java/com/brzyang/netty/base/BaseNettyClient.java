package com.brzyang.netty.base;

import com.brzyang.netty.protocol.PacketCodec;
import com.brzyang.netty.protocol.request.MessageRequestPacket;
import com.brzyang.netty.util.LoginUtil;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class BaseNettyClient {

    private final static int MAX_RETRY = 10;

    public static Channel initClient(List<ChannelHandler> channels){
        Bootstrap bootstrap = new Bootstrap();
        NioEventLoopGroup group = new NioEventLoopGroup();

        bootstrap.group(group)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) {
                        for (ChannelHandler channel : channels) {
                            ch.pipeline().addLast(channel);
                        }
                    }
                });

        bootstrap
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .option(ChannelOption.TCP_NODELAY, true);


        return connect(bootstrap, "127.0.0.1", 8000, MAX_RETRY);
    }

    private static Channel connect(Bootstrap bootstrap, String host, int port, int retry) {
        Channel channel = bootstrap.connect(host, port).addListener(future -> {
            if (future.isSuccess()) {
                System.out.println("连接成功!");
            } else if (retry == 0) {
                System.err.println("重试次数已用完，放弃连接！");
            } else {
                // 第几次重连
                int order = (MAX_RETRY - retry) + 1;
                // 本次重连的间隔
                int delay = 1 << order;
                System.err.println(new Date() + ": 连接失败，第" + order + "次重连……");
                bootstrap.config().group().schedule(() -> connect(bootstrap, host, port, retry - 1),
                        delay, TimeUnit.SECONDS);
            }
        }).channel();

        return channel;
    }

    protected static void startStringConsoleThread(Channel channel) {
        new Thread(() -> {
            while (!Thread.interrupted()) {
                    System.out.println("输入消息发送至服务端: ");
                    Scanner sc = new Scanner(System.in);
                    String line = sc.nextLine();

                    ByteBuf buffer = channel.alloc().buffer();
                    buffer.writeBytes(line.getBytes(StandardCharsets.UTF_8));

                    channel.writeAndFlush(buffer);
            }
        }).start();
    }
}
