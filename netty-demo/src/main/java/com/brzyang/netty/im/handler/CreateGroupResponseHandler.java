package com.brzyang.netty.im.handler;

import com.brzyang.netty.protocol.response.CreateGroupResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CreateGroupResponseHandler extends SimpleChannelInboundHandler<CreateGroupResponsePacket> {

    private static Logger logger = LoggerFactory.getLogger(CreateGroupResponseHandler.class);

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, CreateGroupResponsePacket packet) throws Exception {
        logger.info("groupId:{} created succ, members:{}", packet.getGroupId(), packet.getUsernames());
    }
}
