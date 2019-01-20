package com.brzyang.netty.im.handler;

import com.brzyang.netty.protocol.response.MessageForwardResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ImMessageForwardResponseHandler extends SimpleChannelInboundHandler<MessageForwardResponsePacket> {
    private static Logger logger = LoggerFactory.getLogger(ImMessageForwardResponseHandler.class);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageForwardResponsePacket msg) throws Exception {
        logger.info("msg received fromUser:{} message:{}", msg.getFromUserId(), msg.getMessage());
    }

}
