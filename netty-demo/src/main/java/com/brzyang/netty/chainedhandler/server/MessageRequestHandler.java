package com.brzyang.netty.chainedhandler.server;

import com.brzyang.netty.protocol.request.MessageRequestPacket;
import com.brzyang.netty.protocol.response.MessageResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Date;

public class MessageRequestHandler extends SimpleChannelInboundHandler<MessageRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageRequestPacket msg) throws Exception {
//        MessageResponsePacket messageResponsePacket = receiveMessage(msg);
//        ByteBuf responseByteBuf = PacketCodec.INSTANCE.encode(ctx.alloc(), messageResponsePacket);
        // auto encode
        System.out.println(new Date() + ": 收到客户端消息: " + msg.getMessage());
        ctx.channel().writeAndFlush(receiveMessage(msg));
    }

    private MessageResponsePacket receiveMessage(MessageRequestPacket msg) {
        MessageResponsePacket messageResponsePacket = new MessageResponsePacket();
        messageResponsePacket.setMessage("服务端回复【" + msg.getMessage() + "】 ack");
        return messageResponsePacket;
    }
}
