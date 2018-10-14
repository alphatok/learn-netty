package com.brzyang.netty.chainedhandler.server;

import com.brzyang.netty.protocol.request.LoginRequestPacket;
import com.brzyang.netty.protocol.response.LoginResponsePacket;
import com.brzyang.netty.util.LoginUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Date;
import java.util.Random;
import java.util.UUID;

public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequestPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginRequestPacket msg) throws Exception {
//        LoginResponsePacket loginResponsePacket = login(msg);
//        ByteBuf responseByteBuf = PacketCodec.INSTANCE.encode(ctx.alloc(), loginResponsePacket);
        // auto encode
        System.out.println(new Date() + ": 收到客户端登录请求……");
        ctx.channel().writeAndFlush(login(msg));
        LoginUtil.markAsLogin(ctx.channel());
    }

    private LoginResponsePacket login(LoginRequestPacket msg) {
        LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
        loginResponsePacket.setReason("succ");
        loginResponsePacket.setSuccess(true);
//        loginResponsePacket.setVersion(Login);
        return loginResponsePacket;
    }


}
