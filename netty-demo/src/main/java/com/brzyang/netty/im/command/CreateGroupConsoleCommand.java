package com.brzyang.netty.im.command;

import com.brzyang.netty.im.client.ImNettyClient;
import com.brzyang.netty.protocol.request.CreateGroupRequestPacket;
import io.netty.channel.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Scanner;

public class CreateGroupConsoleCommand implements ConsoleCommand{

    private static final String USER_ID_SPLITER = ",";

    private static Logger logger = LoggerFactory.getLogger(CreateGroupConsoleCommand.class);


    @Override
    public void exec(Scanner scanner, Channel channel) {
        CreateGroupRequestPacket createGroupRequestPacket = new CreateGroupRequestPacket();

        logger.info("createGroup, userId(separeted by comma):");
        String userIds = scanner.next();
        createGroupRequestPacket.setUserIds(Arrays.asList(userIds.split(USER_ID_SPLITER)));
        channel.writeAndFlush(createGroupRequestPacket);
    }

}
