package com.brzyang.netty.im.command;

import com.brzyang.netty.protocol.request.CreateGroupRequestPacket;
import com.brzyang.netty.protocol.request.JoinGroupRequestPacket;
import com.brzyang.netty.util.StringUtil;
import io.netty.channel.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Scanner;

public class JoinGroupConsoleCommand implements ConsoleCommand{

    private static final String USER_ID_SPLITER = ",";

    private static Logger logger = LoggerFactory.getLogger(JoinGroupConsoleCommand.class);


    @Override
    public void exec(Scanner scanner, Channel channel) {
        JoinGroupRequestPacket joinGroupRequestPacket = new JoinGroupRequestPacket();

        logger.info("joinGroup, groupId:");
        String groupId = scanner.next();
        joinGroupRequestPacket.setGroupId(StringUtil.nonNullTrim(groupId));
        channel.writeAndFlush(joinGroupRequestPacket);
    }

}
