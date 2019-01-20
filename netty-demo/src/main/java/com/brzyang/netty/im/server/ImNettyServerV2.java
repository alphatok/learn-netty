package com.brzyang.netty.im.server;

import com.brzyang.netty.im.handler.*;
import io.netty.channel.ChannelHandler;

import java.util.ArrayList;
import java.util.List;

public class ImNettyServerV2 extends ImNettyServer {
    public static void main(String[] args) {
        List<ChannelHandler> channels = new ArrayList<>();
        //  需要维持每个 channel 当前读到的数据，是有状态的
        channels.add(new Splitter());

        channels.add(PacketCodecHandler.INSTANCE);
        channels.add(ImLoginRequestHandler.INSTANCE);
        channels.add(ImServerHandler.INSTANCE);
        channels.add(ImAuthHandler.INSTANCE);
        initNettyServer(channels);
    }
}
