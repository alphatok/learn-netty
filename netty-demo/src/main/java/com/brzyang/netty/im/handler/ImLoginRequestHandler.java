package com.brzyang.netty.im.handler;

import com.brzyang.netty.im.bean.Session;
import com.brzyang.netty.protocol.request.LoginRequestPacket;
import com.brzyang.netty.protocol.response.LoginResponsePacket;
import com.brzyang.netty.util.LoginUtil;
import com.brzyang.netty.util.SessionUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ImLoginRequestHandler extends SimpleChannelInboundHandler<LoginRequestPacket> {

    private static Logger logger = LoggerFactory.getLogger(ImLoginRequestHandler.class);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginRequestPacket msg) throws Exception {
        // auto encode
        LoginUtil.markAsLogin(ctx.channel());
        LoginResponsePacket loginResponse = login(msg);

        Session session = new Session(loginResponse.getUserId(), loginResponse.getUsername());
        SessionUtil.bindSession(session, ctx.channel());
        logger.info("userId:{}  username:{} login succ", msg.getUserId(), msg.getUsername());
        logger.info("userId:{}  username:{} session saved", msg.getUserId(), msg.getUsername());
        ctx.channel().writeAndFlush(loginResponse);
    }

    private LoginResponsePacket login(LoginRequestPacket msg) {
        LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
        loginResponsePacket.setReason("succ");
        loginResponsePacket.setSuccess(true);
        loginResponsePacket.setUserId(msg.getUserId());
        loginResponsePacket.setUsername(msg.getUsername());
//        loginResponsePacket.setVersion(Login);
        return loginResponsePacket;
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Session session = SessionUtil.getSession(ctx.channel());
        logger.info("userId:{}  username:{} session cleared", session.getUserId(), session.getUsername());
        SessionUtil.unBindSession(ctx.channel());
        super.channelInactive(ctx);
    }
}
