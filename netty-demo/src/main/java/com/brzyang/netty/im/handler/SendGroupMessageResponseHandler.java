package com.brzyang.netty.im.handler;

import com.brzyang.netty.protocol.response.GroupMessageResponsePacket;
import com.brzyang.netty.protocol.response.QuitGroupResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SendGroupMessageResponseHandler extends SimpleChannelInboundHandler<GroupMessageResponsePacket> {

    private static Logger logger = LoggerFactory.getLogger(SendGroupMessageResponseHandler.class);


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, GroupMessageResponsePacket responsePacket) throws Exception {
        logger.info("send msg to groupId:{} result:{}", responsePacket.getGroupId(), responsePacket.getResult());
    }
}
