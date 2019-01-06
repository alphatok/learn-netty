package com.brzyang.netty.im.handler;

import com.brzyang.netty.im.bean.Session;
import com.brzyang.netty.protocol.response.MessageResponsePacket;
import com.brzyang.netty.util.SessionUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Date;

public class ImMessageResponseHandler extends SimpleChannelInboundHandler<MessageResponsePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageResponsePacket msg) throws Exception {
        String fromUserName = msg.getFromUsername();
        System.out.println(new Date() + ": 收到消息(from " + fromUserName + "): " + msg.getMessage());
    }

}
