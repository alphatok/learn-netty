package com.brzyang.netty.im.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.handler.timeout.IdleStateHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class ImIdleStateHandler  extends IdleStateHandler {
    private static final int READER_IDLE_TIME = 15;


    private static Logger logger = LoggerFactory.getLogger(ImIdleStateHandler.class);


    public ImIdleStateHandler() {
        super(READER_IDLE_TIME, 0, 0, TimeUnit.SECONDS);
    }

    @Override
    protected void channelIdle(ChannelHandlerContext ctx, IdleStateEvent evt) throws Exception {
        logger.info("no read data in {} seconds", READER_IDLE_TIME);
        ctx.channel().close();
    }
}
