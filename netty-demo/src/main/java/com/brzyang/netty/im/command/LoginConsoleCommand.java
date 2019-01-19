package com.brzyang.netty.im.command;

import com.brzyang.netty.protocol.request.LoginRequestPacket;
import io.netty.channel.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class LoginConsoleCommand implements ConsoleCommand{

    private static Logger logger = LoggerFactory.getLogger(LoginConsoleCommand.class);

    @Override
    public void exec(Scanner scanner, Channel channel) {
        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();

        logger.info("login(username or id):");
        String username = scanner.next();
        loginRequestPacket.setUserId(username);
        loginRequestPacket.setUsername(username);
        loginRequestPacket.setPassword("123456");
        channel.writeAndFlush(loginRequestPacket);
    }

}
