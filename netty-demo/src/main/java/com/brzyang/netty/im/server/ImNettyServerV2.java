package com.brzyang.netty.im.server;

import com.brzyang.netty.im.handler.*;
import io.netty.channel.ChannelHandler;

import java.util.ArrayList;
import java.util.List;

public class ImNettyServerV2 extends ImNettyServer {
    public static void main(String[] args) {
        List<ChannelHandler> channels = new ArrayList<>();
        channels.add(new ImIdleStateHandler());
        //  需要维持每个 channel 当前读到的数据，是有状态的
        channels.add(new Splitter());
        channels.add(PacketCodecHandler.INSTANCE);
        channels.add(ImLoginRequestHandler.INSTANCE);
        channels.add(HeartBeatRequestHandler.INSTANCE);
        channels.add(ImServerHandler.INSTANCE);
        channels.add(ImAuthHandler.INSTANCE);
        initNettyServer(channels);

        // ctx.writeAndFlush() 是从 pipeline 链中的当前节点开始往前找到第一个 outBound 类型的 handler 把对象往前进行传播，
        // 如果这个对象确认不需要经过其他 outBound 类型的 handler 处理，就使用这个方法。

        // ctx.channel().writeAndFlush()，对象会从最后一个 outBound 类型的 handler 开始
        // https://juejin.im/book/5b4bc28bf265da0f60130116/section/5b4db131e51d4519634fb867
    }
}
