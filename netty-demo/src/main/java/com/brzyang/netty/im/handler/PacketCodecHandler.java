package com.brzyang.netty.im.handler;

import com.brzyang.netty.protocol.Packet;
import com.brzyang.netty.protocol.PacketCodec;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageCodec;

import java.util.List;

/**
 * 合并 PacketDecoder 和 PacketEncoder
 */
@Sharable
public class PacketCodecHandler  extends MessageToMessageCodec<ByteBuf, Packet> {
    public static final PacketCodecHandler INSTANCE = new PacketCodecHandler();

    public PacketCodecHandler() {

    }


    @Override
    protected void encode(ChannelHandlerContext ctx, Packet packet, List<Object> list) throws Exception {
        ByteBuf byteBuf = ctx.channel().alloc().ioBuffer();
        PacketCodec.INSTANCE.encode(byteBuf, packet);
        list.add(byteBuf);
    }

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        list.add(PacketCodec.INSTANCE.decode(byteBuf));
    }
}
