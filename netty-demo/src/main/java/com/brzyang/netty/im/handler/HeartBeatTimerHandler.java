package com.brzyang.netty.im.handler;

import com.brzyang.netty.protocol.request.HeartBeatRequestPacket;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.concurrent.TimeUnit;

@Sharable
public class HeartBeatTimerHandler  extends ChannelInboundHandlerAdapter {
    private static final int HEARTBEAT_INTERVAL = 5;
    public final static HeartBeatTimerHandler INSTANCE = new HeartBeatTimerHandler();

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        scheduleSendHeartBeat(ctx);
        super.channelActive(ctx);
    }

    private void scheduleSendHeartBeat(ChannelHandlerContext ctx) {
        ctx.executor().schedule(() -> {

            if (ctx.channel().isActive()) {
                ctx.writeAndFlush(new HeartBeatRequestPacket());
                scheduleSendHeartBeat(ctx);
            }

        }, HEARTBEAT_INTERVAL, TimeUnit.SECONDS);
    }
}
