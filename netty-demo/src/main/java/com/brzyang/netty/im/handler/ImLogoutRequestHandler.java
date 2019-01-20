package com.brzyang.netty.im.handler;

import com.brzyang.netty.im.bean.Session;
import com.brzyang.netty.protocol.Packet;
import com.brzyang.netty.protocol.request.LoginRequestPacket;
import com.brzyang.netty.protocol.request.LogoutRequestPacket;
import com.brzyang.netty.protocol.response.LoginResponsePacket;
import com.brzyang.netty.protocol.response.LogoutResponsePacket;
import com.brzyang.netty.util.LoginUtil;
import com.brzyang.netty.util.SessionUtil;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Sharable
public class ImLogoutRequestHandler extends SimpleChannelInboundHandler<LogoutRequestPacket> {

    public static final ImLogoutRequestHandler INSTANCE = new ImLogoutRequestHandler();
    private static Logger logger = LoggerFactory.getLogger(ImLogoutRequestHandler.class);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LogoutRequestPacket msg) throws Exception {

        LogoutResponsePacket logoutResponse = new LogoutResponsePacket();
        logoutResponse.setUserId(msg.getUserId());
        logoutResponse.setUsername(msg.getUsername());
        logoutResponse.setSuccess(true);
        logoutResponse.setReason("succ)");

        boolean loginNow = LoginUtil.hasLogin(ctx.channel());

        LoginUtil.clearLoginStatus(ctx.channel());

        logger.info("userId:{}  username:{} loginNow:{} logout succ", msg.getUserId(), msg.getUsername(), loginNow);
        ctx.channel().writeAndFlush(logoutResponse);
    }


    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Session session = SessionUtil.getSession(ctx.channel());
        if (session != null) {
            logger.info("userId:{}  username:{} session cleared", session.getUserId(), session.getUsername());
            SessionUtil.unBindSession(ctx.channel());
        }
        super.channelInactive(ctx);
    }
}
