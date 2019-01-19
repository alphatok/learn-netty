package com.brzyang.netty.im.handler;

import com.brzyang.netty.protocol.request.LoginRequestPacket;
import com.brzyang.netty.protocol.response.LoginResponsePacket;
import com.brzyang.netty.util.LoginUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.Random;

public class ImLoginResponseHandler extends SimpleChannelInboundHandler<LoginResponsePacket> {

    private static Logger logger = LoggerFactory.getLogger(ImLoginResponseHandler.class);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginResponsePacket msg) throws Exception {

        if (msg.getSuccess()) {
            logger.info("userId:{}  username:{} login succ", msg.getUserId(), msg.getUsername());
            LoginUtil.markAsLogin(ctx.channel());
        } else {
            logger.error("userId:{}  username:{} login failed, reason:{}",
                    msg.getUserId(), msg.getUsername(), msg.getReason());
        }
    }

//
//    /**抽象为单独的Handler**/
//    @Override
//    public void channelActive(ChannelHandlerContext ctx) {
//        // 创建登录对象
//        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();
//        loginRequestPacket.setUserId(String.valueOf(new Random().nextInt()));
//        loginRequestPacket.setUsername("flash");
//        loginRequestPacket.setPassword("pwd");
//
//        // 写数据
//        ctx.channel().writeAndFlush(loginRequestPacket);
//    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        logger.info("客户端连接被关闭!");
        super.channelInactive(ctx);
    }
}
