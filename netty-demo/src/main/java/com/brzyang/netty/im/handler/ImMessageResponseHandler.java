package com.brzyang.netty.im.handler;

import com.brzyang.netty.protocol.response.MessageResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

public class ImMessageResponseHandler extends SimpleChannelInboundHandler<MessageResponsePacket> {
    private static Logger logger = LoggerFactory.getLogger(ImMessageResponseHandler.class);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageResponsePacket msg) throws Exception {
        logger.info("msg deliver dstUserId:{} result:{}", msg.getDstUserId(), msg.getResult());
    }

}
