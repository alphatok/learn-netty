package com.brzyang.netty.im.handler;

import com.brzyang.netty.protocol.response.LogoutResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ImLogoutResponseHandler extends SimpleChannelInboundHandler<LogoutResponsePacket> {

    private static Logger logger = LoggerFactory.getLogger(ImLogoutResponseHandler.class);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LogoutResponsePacket msg) throws Exception {
        logger.info("userId:{}  username:{} logout succ", msg.getUserId(), msg.getUsername());
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        logger.info("客户端连接被关闭!");
        super.channelInactive(ctx);
    }
}
