package com.brzyang.netty.im.command;

import com.brzyang.netty.protocol.request.LogoutRequestPacket;
import io.netty.channel.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class LogoutConsoleCommand implements ConsoleCommand{

    private static Logger logger = LoggerFactory.getLogger(LogoutConsoleCommand.class);

    @Override
    public void exec(Scanner scanner, Channel channel) {
        LogoutRequestPacket logoutConsoleCommand = new LogoutRequestPacket();

        logger.info("login(username or id):");
        String username = scanner.next();
        logoutConsoleCommand.setUserId(username);
        logoutConsoleCommand.setUsername(username);
        channel.writeAndFlush(logoutConsoleCommand);
    }

}
