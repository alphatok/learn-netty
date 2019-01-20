package com.brzyang.netty.im.command;

import com.brzyang.netty.protocol.request.GroupMessageRequestPacket;
import com.brzyang.netty.protocol.request.MessageRequestPacket;
import com.brzyang.netty.util.StringUtil;
import io.netty.channel.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

/**
 * 单聊
 */
public class SendToGroupConsoleCommand implements ConsoleCommand{

    private static Logger logger = LoggerFactory.getLogger(SendToGroupConsoleCommand.class);

    @Override
    public void exec(Scanner scanner, Channel channel) {
        GroupMessageRequestPacket messageRequest = new GroupMessageRequestPacket();

        String comp = StringUtil.nonNullTrim(scanner.nextLine());
        int blankIndex = comp.indexOf(" ");
        if (blankIndex <= 0) {
            throw new RuntimeException("no blank found");
        }

        messageRequest.setToGroupId(StringUtil.nonNullTrim(comp.substring(0, blankIndex)));
        messageRequest.setMessage(StringUtil.nonNullTrim(comp.substring(blankIndex)));
        channel.writeAndFlush(messageRequest);
    }

}
