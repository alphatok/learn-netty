package com.brzyang.netty.im.handler;

import com.brzyang.netty.im.bean.Session;
import com.brzyang.netty.protocol.request.QuitGroupRequestPacket;
import com.brzyang.netty.protocol.response.QuitGroupResponsePacket;
import com.brzyang.netty.util.SessionUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QuitGroupResponseHandler extends SimpleChannelInboundHandler<QuitGroupResponsePacket> {

    private static Logger logger = LoggerFactory.getLogger(QuitGroupResponseHandler.class);


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, QuitGroupResponsePacket responsePacket) throws Exception {
        logger.info("quit groupId:{} result:{}", responsePacket.getGroupId(), responsePacket.isSucc());
    }
}
