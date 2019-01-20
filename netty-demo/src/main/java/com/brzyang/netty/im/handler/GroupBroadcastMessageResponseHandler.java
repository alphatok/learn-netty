package com.brzyang.netty.im.handler;

import com.brzyang.netty.protocol.response.GroupBroadcastMessageResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GroupBroadcastMessageResponseHandler extends SimpleChannelInboundHandler<GroupBroadcastMessageResponsePacket> {

    private static Logger logger = LoggerFactory.getLogger(GroupBroadcastMessageResponseHandler.class);


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, GroupBroadcastMessageResponsePacket msg) throws Exception {
        logger.info("groupId:{} userId:{} say:{}", msg.getFromGroupId(), msg.getFromUserId(),  msg.getMessage());
    }
}
