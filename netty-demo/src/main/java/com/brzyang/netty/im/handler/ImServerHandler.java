package com.brzyang.netty.im.handler;

import com.brzyang.netty.chainedhandler.server.MessageRequestHandler;
import com.brzyang.netty.protocol.Packet;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.HashMap;
import java.util.Map;

import static com.brzyang.netty.protocol.command.Command.*;
/*
* 缩短事件传播路径
* */
@Sharable
public class ImServerHandler  extends SimpleChannelInboundHandler<Packet> {
    public static final  ImServerHandler INSTANCE = new ImServerHandler();

    private Map<Byte, SimpleChannelInboundHandler<? extends Packet>> handlerMap;


    public ImServerHandler() {
        handlerMap = new HashMap<>();

        handlerMap.put(LOGOUT_REQUEST, ImLogoutRequestHandler.INSTANCE);
        handlerMap.put(MESSAGE_REQUEST, ImMessageRequestHandler.INSTANCE);
        handlerMap.put(CREATE_GROUP_REQUEST, CreateGroupRequestHandler.INSTANCE);
        handlerMap.put(JOIN_GROUP_REQUEST, JoinGroupRequestHandler.INSTANCE);
        handlerMap.put(QUIT_GROUP_REQUEST, QuitGroupRequestHandler.INSTANCE);
        handlerMap.put(LIST_GROUP_REQUEST, ListGroupMembersRequestHandler.INSTANCE);
        handlerMap.put(GROUP_MESSAGE_REQUEST, SendGroupMessageRequestHandler.INSTANCE);
        // ...
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Packet packet) throws Exception {
        handlerMap.get(packet.getCommand()).channelRead(channelHandlerContext, packet);
    }
}
