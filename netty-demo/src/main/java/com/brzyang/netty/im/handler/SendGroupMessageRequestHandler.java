package com.brzyang.netty.im.handler;

import com.brzyang.netty.im.bean.Session;
import com.brzyang.netty.protocol.Packet;
import com.brzyang.netty.protocol.request.GroupMessageRequestPacket;
import com.brzyang.netty.protocol.request.JoinGroupRequestPacket;
import com.brzyang.netty.protocol.response.GroupBroadcastMessageResponsePacket;
import com.brzyang.netty.protocol.response.GroupMessageResponsePacket;
import com.brzyang.netty.protocol.response.JoinGroupResponsePacket;
import com.brzyang.netty.util.SessionUtil;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Sharable
public class SendGroupMessageRequestHandler extends SimpleChannelInboundHandler<GroupMessageRequestPacket> {

    public static final SendGroupMessageRequestHandler INSTANCE = new SendGroupMessageRequestHandler();
    private static Logger logger = LoggerFactory.getLogger(SendGroupMessageRequestHandler.class);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, GroupMessageRequestPacket requestPacket) throws Exception {
        // 1.拿到 groupId 构造群聊消息的响应
        String groupId = requestPacket.getToGroupId();
        GroupBroadcastMessageResponsePacket responsePacket = new GroupBroadcastMessageResponsePacket();
        responsePacket.setFromGroupId(groupId);
        responsePacket.setMessage(requestPacket.getMessage());
        Session session = SessionUtil.getSession(ctx.channel());
        responsePacket.setFromUserId(session.getUserId());

        // 2. 拿到群聊对应的 channelGroup，写到每个客户端
        ChannelGroup channelGroup = SessionUtil.getChannelGroup(groupId);
        channelGroup.writeAndFlush(responsePacket);

        GroupMessageResponsePacket groupMessageResponsePacket = new GroupMessageResponsePacket();
        groupMessageResponsePacket.setGroupId(groupId);
        groupMessageResponsePacket.setResult("send group msg succ");
        ctx.channel().writeAndFlush(groupMessageResponsePacket);
    }
}
