package com.brzyang.netty.multichannel;

import com.brzyang.netty.base.BaseNettyServer;
import com.brzyang.netty.multichannel.inbound.InBoundHandlerA;
import com.brzyang.netty.multichannel.inbound.InBoundHandlerB;
import com.brzyang.netty.multichannel.inbound.InBoundHandlerC;
import com.brzyang.netty.multichannel.outbount.OutBoundHandlerA;
import com.brzyang.netty.multichannel.outbount.OutBoundHandlerB;
import com.brzyang.netty.multichannel.outbount.OutBoundHandlerC;
import io.netty.channel.ChannelHandler;

import java.util.ArrayList;
import java.util.List;

public class MultiChannelNettyServer extends BaseNettyServer {

    public static void main(String[] args) {
        List<ChannelHandler> channels = new ArrayList<>();
        channels.add(new InBoundHandlerA());
        channels.add(new InBoundHandlerB());
        channels.add(new InBoundHandlerC());
        channels.add(new OutBoundHandlerA());
        channels.add(new OutBoundHandlerB());
        channels.add(new OutBoundHandlerC());

        initNettyServer(channels);
    }
}
