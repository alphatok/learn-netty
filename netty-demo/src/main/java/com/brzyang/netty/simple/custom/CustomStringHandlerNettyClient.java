package com.brzyang.netty.simple.custom;

import com.brzyang.netty.base.BaseNettyClient;
import com.brzyang.netty.simple.custom.handler.CustomStringClientHandler;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;

import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class CustomStringHandlerNettyClient extends BaseNettyClient {

    public static void main(String[] args) throws InterruptedException {
        List<ChannelHandler> channels = Collections.singletonList(new CustomStringClientHandler());
        Channel channel = initClient(channels);
        // 连接成功之后，启动控制台线程
        startStringConsoleThread(channel);
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
