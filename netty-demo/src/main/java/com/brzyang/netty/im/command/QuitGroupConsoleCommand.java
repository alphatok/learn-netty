package com.brzyang.netty.im.command;

import com.brzyang.netty.protocol.request.JoinGroupRequestPacket;
import com.brzyang.netty.protocol.request.QuitGroupRequestPacket;
import com.brzyang.netty.util.StringUtil;
import io.netty.channel.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class QuitGroupConsoleCommand implements ConsoleCommand{

    private static final String USER_ID_SPLITER = ",";

    private static Logger logger = LoggerFactory.getLogger(QuitGroupConsoleCommand.class);


    @Override
    public void exec(Scanner scanner, Channel channel) {
        QuitGroupRequestPacket quitGroupRequestPacket = new QuitGroupRequestPacket();

        logger.info("quitGroup, groupId:");
        String groupId = scanner.next();
        quitGroupRequestPacket.setGroupId(StringUtil.nonNullTrim(groupId));
        channel.writeAndFlush(quitGroupRequestPacket);
    }

}
