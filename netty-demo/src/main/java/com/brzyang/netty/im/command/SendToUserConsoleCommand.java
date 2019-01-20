package com.brzyang.netty.im.command;

import com.brzyang.netty.protocol.request.MessageRequestPacket;
import com.brzyang.netty.util.StringUtil;
import io.netty.channel.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;
/**
 * 单聊
 */
public class SendToUserConsoleCommand implements ConsoleCommand{

    private static Logger logger = LoggerFactory.getLogger(SendToUserConsoleCommand.class);

    @Override
    public void exec(Scanner scanner, Channel channel) {
        MessageRequestPacket messageRequest = new MessageRequestPacket();

        logger.info("username/id+msg:");
        String comp = StringUtil.nonNullTrim(scanner.nextLine());
        int blankIndex = comp.indexOf(" ");
        if (blankIndex <= 0) {
            throw new RuntimeException("no blank found");
        }

        messageRequest.setToUserId(StringUtil.nonNullTrim(comp.substring(0, blankIndex)));
        messageRequest.setMessage(StringUtil.nonNullTrim(comp.substring(blankIndex)));
        channel.writeAndFlush(messageRequest);
    }

}
