package com.brzyang.netty.im.handler;

import com.brzyang.netty.im.bean.Session;
import com.brzyang.netty.protocol.Packet;
import com.brzyang.netty.protocol.request.CreateGroupRequestPacket;
import com.brzyang.netty.protocol.request.QuitGroupRequestPacket;
import com.brzyang.netty.protocol.response.CreateGroupResponsePacket;
import com.brzyang.netty.protocol.response.QuitGroupResponsePacket;
import com.brzyang.netty.util.IdUtil;
import com.brzyang.netty.util.SessionUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

@Sharable
public class QuitGroupRequestHandler extends SimpleChannelInboundHandler<QuitGroupRequestPacket> {

    public static final QuitGroupRequestHandler INSTANCE = new QuitGroupRequestHandler();
    private static Logger logger = LoggerFactory.getLogger(QuitGroupRequestHandler.class);


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, QuitGroupRequestPacket requestPacket) throws Exception {
        // 1. 获取群对应的 channelGroup，然后将当前用户的 channel 移除
        String groupId = requestPacket.getGroupId();
        ChannelGroup channelGroup = SessionUtil.getChannelGroup(groupId);
        channelGroup.remove(ctx.channel());

        Session session = SessionUtil.getSession(ctx.channel());

        logger.info("userId:{} quit groupId:{} succ", session.getUserId(), groupId);

        // 2. 构造退群响应发送给客户端
        QuitGroupResponsePacket responsePacket = new QuitGroupResponsePacket();

        responsePacket.setGroupId(requestPacket.getGroupId());
        responsePacket.setReason("?");
        responsePacket.setSucc(true);
        ctx.channel().writeAndFlush(responsePacket);
    }
}
