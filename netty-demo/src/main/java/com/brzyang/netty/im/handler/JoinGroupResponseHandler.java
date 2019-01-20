package com.brzyang.netty.im.handler;

import com.brzyang.netty.protocol.response.CreateGroupResponsePacket;
import com.brzyang.netty.protocol.response.JoinGroupResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JoinGroupResponseHandler extends SimpleChannelInboundHandler<JoinGroupResponsePacket> {

    private static Logger logger = LoggerFactory.getLogger(JoinGroupResponseHandler.class);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, JoinGroupResponsePacket responsePacket) throws Exception {
        if (responsePacket.isSucc()) {
            logger.info("joinGroup succ groupId:{}", responsePacket.getGroupId());
        } else {
            logger.info("joinGroup failed groupId:{} reason:{}", responsePacket.getGroupId(), responsePacket.getReason());
        }
    }
}
