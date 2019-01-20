package com.brzyang.netty.im.handler;

import com.brzyang.netty.im.bean.Session;
import com.brzyang.netty.protocol.request.MessageRequestPacket;
import com.brzyang.netty.protocol.response.MessageForwardResponsePacket;
import com.brzyang.netty.protocol.response.MessageResponsePacket;
import com.brzyang.netty.util.LoginUtil;
import com.brzyang.netty.util.SessionUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ImMessageRequestHandler extends SimpleChannelInboundHandler<MessageRequestPacket> {

    private static Logger logger = LoggerFactory.getLogger(ImMessageRequestHandler.class);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageRequestPacket msg) throws Exception {
        // auto encode

        Channel requestChannel = ctx.channel();
        Session session = SessionUtil.getSession(requestChannel);
        logger.info("received client msg from:{} to:{} msg:{}", session.getUserId(), msg.getToUserId(), msg.getMessage());
        // 1.拿到消息发送方的会话信息

        // 2.通过消息发送方的会话信息构造要发送的消息
        MessageResponsePacket responsePacket = receiveMessage(msg, session);

        // 3.拿到消息接收方的 channel
        Channel toUserChannel = SessionUtil.getChannel(msg.getToUserId());

        // 4.将消息发送给消息接收方
        if (toUserChannel != null && LoginUtil.hasLogin(toUserChannel)) {
            Session toSession = SessionUtil.getSession(toUserChannel);
            MessageForwardResponsePacket forwardResponsePacket = forwardMessage(msg, session);
            logger.info("msg delivered from:{} to:{} msg:{}", session.getUserId(), toSession.getUserId(), msg.getMessage());
            requestChannel.writeAndFlush(responsePacket);
            toUserChannel.writeAndFlush(forwardResponsePacket);
        } else {
            responsePacket.setResult("delivered failed(offline)");
            requestChannel.writeAndFlush(responsePacket);
            logger.info("client msg ignored from:{} to:{}(offline) msg:{}", session.getUserId(), msg.getToUserId(), msg.getMessage());
        }
    }

    private MessageForwardResponsePacket forwardMessage(MessageRequestPacket msg, Session session) {
        MessageForwardResponsePacket response = new MessageForwardResponsePacket();
        response.setFromUserId(session.getUserId());
        response.setFromUsername(session.getUsername());
        response.setDstUserId(msg.getToUserId());
        response.setMessage(msg.getMessage());
        return response;
    }

    private MessageResponsePacket receiveMessage(MessageRequestPacket msg, Session session) {
        MessageResponsePacket response = new MessageResponsePacket();
        response.setFromUserId(session.getUserId());
        response.setFromUsername(session.getUsername());
        response.setDstUserId(msg.getToUserId());
        response.setResult("delivered succ");
        return response;
    }
}
