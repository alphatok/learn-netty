package com.brzyang.netty.im.client;

import com.brzyang.netty.base.BaseNettyClient;
import com.brzyang.netty.chainedhandler.PacketDecoder;
import com.brzyang.netty.chainedhandler.PacketEncoder;
import com.brzyang.netty.im.command.ConsoleCommandManager;
import com.brzyang.netty.im.command.LoginConsoleCommand;
import com.brzyang.netty.im.handler.*;
import com.brzyang.netty.util.LoginUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ImNettyClient extends BaseNettyClient {

    private static Logger logger = LoggerFactory.getLogger(ImNettyClient.class);


    public static void main(String[] args) {
        List<ChannelHandler> channels = new ArrayList<>();
        channels.add(new Splitter());
        channels.add(new PacketDecoder());
        channels.add(new ImLoginResponseHandler());
        channels.add(new ImLogoutResponseHandler());
        channels.add(new ImMessageResponseHandler());
        channels.add(new ImMessageForwardResponseHandler());
        channels.add(new CreateGroupResponseHandler());
        channels.add(new JoinGroupResponseHandler());
        channels.add(new QuitGroupResponseHandler());
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
             try {
                 if (!LoginUtil.hasLogin(channel)) {
                     loginConsoleCommand.exec(scanner, channel);
                     try {
                         Thread.sleep(5000);
                     } catch (InterruptedException e) {
                         e.printStackTrace();
                     }
                 } else {
                     consoleCommandManager.exec(scanner, channel);
                 }
             }catch (Exception e){
                 logger.error("", e);
             }
            }
        }).start();
    }

}
