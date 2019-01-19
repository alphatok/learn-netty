package com.brzyang.netty.im.client;

import com.brzyang.netty.base.BaseNettyClient;
import com.brzyang.netty.chainedhandler.PacketDecoder;
import com.brzyang.netty.chainedhandler.PacketEncoder;
import com.brzyang.netty.im.command.ConsoleCommandManager;
import com.brzyang.netty.im.command.LoginConsoleCommand;
import com.brzyang.netty.im.handler.ImLoginResponseHandler;
import com.brzyang.netty.im.handler.ImLogoutResponseHandler;
import com.brzyang.netty.im.handler.Splitter;
import com.brzyang.netty.util.LoginUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ImNettyClient extends BaseNettyClient {
    public static void main(String[] args) {
        List<ChannelHandler> channels = new ArrayList<>();
        channels.add(new Splitter());
        channels.add(new PacketDecoder());
        channels.add(new ImLoginResponseHandler());
        channels.add(new ImLogoutResponseHandler());
//        channels.add(new ImMessageResponseHandler());
//        channels.add(new CreateGroupResponseHandler());
        channels.add(new PacketEncoder());
        Channel channel = initClient(channels);
        startConsoleThread(channel);
    }

    private static void startConsoleThread(Channel channel) {
        ConsoleCommandManager consoleCommandManager = new ConsoleCommandManager();
        LoginConsoleCommand loginConsoleCommand = new LoginConsoleCommand();
        Scanner scanner = new Scanner(System.in);

        new Thread(()  ->{
            while (!Thread.interrupted()) {
                if (!LoginUtil.hasLogin(channel)) {
                    loginConsoleCommand.exec(scanner, channel);
                } else {
                    consoleCommandManager.exec(scanner, channel);
                }
            }
        }).start();
    }

}
