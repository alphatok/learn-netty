package com.brzyang.netty.im.handler;

import com.brzyang.netty.im.bean.Session;
import com.brzyang.netty.protocol.request.LoginRequestPacket;
import com.brzyang.netty.protocol.response.LoginResponsePacket;
import com.brzyang.netty.util.LoginUtil;
import com.brzyang.netty.util.SessionUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Date;

public class ImLoginRequestHandler extends SimpleChannelInboundHandler<LoginRequestPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginRequestPacket msg) throws Exception {
        // auto encode
        LoginUtil.markAsLogin(ctx.channel());
        LoginResponsePacket loginResponse = login(msg);

        Session session = new Session(loginResponse.getUserId(), loginResponse.getUsername());
        SessionUtil.bindSession(session, ctx.channel());
        System.out.println(new Date() + "【" + msg.getUsername() + "】登录成功");
        System.out.println(new Date() + "【" + msg.getUsername() + "】session saved……");
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
        System.out.println(new Date() + "【" + session.getUsername() + "】session cleared……");
        SessionUtil.unBindSession(ctx.channel());
        super.channelInactive(ctx);
    }
}
