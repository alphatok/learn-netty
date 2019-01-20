package com.brzyang.netty.im.command;

import com.brzyang.netty.protocol.request.JoinGroupRequestPacket;
import com.brzyang.netty.protocol.request.ListGroupMembersRequestPacket;
import com.brzyang.netty.util.StringUtil;
import io.netty.channel.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class ListGroupMembersConsoleCommand implements ConsoleCommand{


    private static Logger logger = LoggerFactory.getLogger(ListGroupMembersConsoleCommand.class);


    @Override
    public void exec(Scanner scanner, Channel channel) {
        ListGroupMembersRequestPacket listGroupMembersRequestPacket = new ListGroupMembersRequestPacket();

        logger.info("listGroupMembers, groupId:");
        String groupId = scanner.next();
        listGroupMembersRequestPacket.setGroupId(StringUtil.nonNullTrim(groupId));
        channel.writeAndFlush(listGroupMembersRequestPacket);
    }

}
