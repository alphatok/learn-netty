package com.brzyang.netty.im.handler;

import com.brzyang.netty.im.bean.Session;
import com.brzyang.netty.protocol.request.MessageRequestPacket;
import com.brzyang.netty.protocol.response.MessageResponsePacket;
import com.brzyang.netty.util.LoginUtil;
import com.brzyang.netty.util.SessionUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Date;

public class ImMessageRequestHandler extends SimpleChannelInboundHandler<MessageRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageRequestPacket msg) throws Exception {
        // auto encode
        System.out.println(new Date() + ": 收到客户端消息: " + msg.getMessage());
        // 1.拿到消息发送方的会话信息
        Session session = SessionUtil.getSession(ctx.channel());

        // 2.通过消息发送方的会话信息构造要发送的消息
        MessageResponsePacket responsePacket = receiveMessage(msg, session);

        // 3.拿到消息接收方的 channel
        Channel toUserChannel = SessionUtil.getChannel(msg.getToUserId());

        // 4.将消息发送给消息接收方
        if (toUserChannel != null && LoginUtil.hasLogin(toUserChannel)) {
            Session toSession = SessionUtil.getSession(toUserChannel);
            System.out.println("【" + session.getUsername() + "】-->>【" + toSession.getUsername() + "】 " + msg.getMessage());
            toUserChannel.writeAndFlush(responsePacket);
        } else {
            System.err.println("[" + msg.getToUserId() + "] 不在线，发送失败!");
        }
    }

    private MessageResponsePacket receiveMessage(MessageRequestPacket msg, Session session) {
        MessageResponsePacket response = new MessageResponsePacket();
        response.setFromUserId(session.getUserId());
        response.setFromUsername(session.getUsername());
        response.setMessage(msg.getMessage());
        return response;
    }
}
