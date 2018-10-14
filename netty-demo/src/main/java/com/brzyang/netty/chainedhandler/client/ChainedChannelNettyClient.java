package com.brzyang.netty.chainedhandler.client;

import com.brzyang.netty.base.BaseNettyClient;
import com.brzyang.netty.chainedhandler.PacketDecoder;
import com.brzyang.netty.chainedhandler.PacketEncoder;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

import java.util.ArrayList;
import java.util.List;

public class ChainedChannelNettyClient extends BaseNettyClient {
    public static void main(String[] args) throws InterruptedException {
        List<ChannelHandler> channels = new ArrayList<>();
        channels.add(new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 7, 4));

        channels.add(new PacketDecoder());
        channels.add(new LoginResponseHandler());
        channels.add(new MessageResponseHandler());
        channels.add(new PacketEncoder());
        Channel channel = initClient(channels);
        startProtocolConsoleThread(channel);
    }
}
