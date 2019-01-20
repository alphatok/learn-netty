package com.brzyang.netty.im.handler;

import com.brzyang.netty.protocol.request.HeartBeatRequestPacket;
import com.brzyang.netty.protocol.response.HeartBeatResponsePacket;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.LoggerFactory;

import java.util.logging.Logger;

@Sharable
public class HeartBeatResponseHandler extends SimpleChannelInboundHandler<HeartBeatResponsePacket> {
    public static final HeartBeatResponseHandler INSTANCE = new HeartBeatResponseHandler();


    private static org.slf4j.Logger logger = LoggerFactory.getLogger(HeartBeatResponseHandler.class);

    public HeartBeatResponseHandler() {

    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HeartBeatResponsePacket requestPacket) {
        logger.info("heart beating...");
    }
}
