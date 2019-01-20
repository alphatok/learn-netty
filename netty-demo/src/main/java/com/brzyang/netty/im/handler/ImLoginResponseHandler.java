package com.brzyang.netty.im.handler;

import com.brzyang.netty.protocol.response.LoginResponsePacket;
import com.brzyang.netty.util.LoginUtil;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Sharable
public class ImLoginResponseHandler extends SimpleChannelInboundHandler<LoginResponsePacket> {

    private static Logger logger = LoggerFactory.getLogger(ImLoginResponseHandler.class);

    public static final ImLoginResponseHandler INSTANCE = new ImLoginResponseHandler();

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginResponsePacket msg) throws Exception {

        if (msg.getSuccess()) {
            LoginUtil.markAsLogin(ctx.channel());
            logger.info("userId:{}  username:{} login succ", msg.getUserId(), msg.getUsername());
        } else {
            logger.error("userId:{}  username:{} login failed, reason:{}",
                    msg.getUserId(), msg.getUsername(), msg.getReason());
        }
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        logger.info("客户端连接被关闭!");
        super.channelInactive(ctx);
    }
}
