package com.brzyang.netty.lifecycle.server;

import com.brzyang.netty.base.BaseNettyServer;
import com.brzyang.netty.chainedhandler.PacketDecoder;
import com.brzyang.netty.chainedhandler.PacketEncoder;
import com.brzyang.netty.chainedhandler.server.LoginRequestHandler;
import com.brzyang.netty.chainedhandler.server.MessageRequestHandler;
import com.brzyang.netty.lifecycle.handler.LifeCyCleTestHandler;
import io.netty.channel.ChannelHandler;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

import java.util.ArrayList;
import java.util.List;

public class ChannelLifeCycleTestNettyServer extends BaseNettyServer {

    public static void main(String[] args) {
        List<ChannelHandler> channels = new ArrayList<>();
        channels.add(new LifeCyCleTestHandler());
        channels.add(new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 7, 4));
        channels.add(new PacketDecoder());
        channels.add(new LoginRequestHandler());
        channels.add(new MessageRequestHandler());
        channels.add(new PacketEncoder());
        initNettyServer(channels);
        // ChannelHandler 回调方法的执行顺序

        /*
        逻辑处理器被添加：handlerAdded()
        channel 绑定到线程(NioEventLoop)：channelRegistered()
        channel 准备就绪：channelActive()
        channel 有数据可读：channelRead()
        Tue Jan 01 17:00:45 CST 2019: 收到客户端登录请求……
        channel 某次数据读完：channelReadComplete()
        channel 某次数据读完：channelReadComplete()

        关闭客户端时
        channel 被关闭：channelInactive()
        channel 取消线程(NioEventLoop) 的绑定: channelUnregistered()
        逻辑处理器被移除：handlerRemoved()
        */
    }
}
