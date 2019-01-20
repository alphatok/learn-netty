package com.brzyang.netty.im.handler;

import com.brzyang.netty.im.bean.Session;
import com.brzyang.netty.protocol.request.ListGroupMembersRequestPacket;
import com.brzyang.netty.protocol.response.ListGroupMembersResponsePacket;
import com.brzyang.netty.util.SessionUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class ListGroupMembersResponseHandler extends SimpleChannelInboundHandler<ListGroupMembersResponsePacket> {

    private static Logger logger = LoggerFactory.getLogger(ListGroupMembersResponseHandler.class);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ListGroupMembersResponsePacket requestPacket) throws Exception {
        // 1. 获取群对应的 channelGroup，然后将当前用户的 channel 添加进去
        String groupId = requestPacket.getGroupId();
        logger.info("groupId:{} members:{}", groupId, requestPacket.getSessions());
    }
}
