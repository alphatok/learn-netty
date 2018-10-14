package com.brzyang.netty.one2one;

import com.brzyang.netty.protocol.Packet;
import com.brzyang.netty.protocol.PacketCodec;
import com.brzyang.netty.protocol.request.LoginRequestPacket;
import com.brzyang.netty.protocol.request.MessageRequestPacket;
import com.brzyang.netty.protocol.response.LoginResponsePacket;
import com.brzyang.netty.protocol.response.MessageResponsePacket;
import com.brzyang.netty.util.LoginUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.Date;

public class ProtocolServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf requestByteBuf = (ByteBuf) msg;

        // 解码
        Packet packet = PacketCodec.INSTANCE.decode(requestByteBuf);

        // 判断是否是登录请求数据包

        // 模板方法, channel放入FastThreadLocal；反射；策略模式、状态模式、卫语句
        if (packet instanceof LoginRequestPacket) {
            LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
            loginResponsePacket.setVersion(packet.getVersion());
            LoginRequestPacket loginRequestPacket = (LoginRequestPacket) packet;
            // 登录校验
            if (valid(loginRequestPacket)) {
                // 校验成功
                loginResponsePacket.setSuccess(true);
                LoginUtil.markAsLogin(ctx.channel());
            } else {
                // 校验失败
                loginResponsePacket.setReason("账号密码校验失败");
                loginResponsePacket.setSuccess(false);
            }

            // 编码
            ByteBuf responseByteBuf = PacketCodec.INSTANCE.encode(ctx.alloc(), loginResponsePacket);
            ctx.channel().writeAndFlush(responseByteBuf);
        }else if (packet instanceof MessageRequestPacket) {
            // 处理消息
            MessageRequestPacket messageRequestPacket = ((MessageRequestPacket) packet);
            System.out.println(new Date() + ": 收到客户端消息: " + messageRequestPacket.getMessage());

            MessageResponsePacket messageResponsePacket = new MessageResponsePacket();
            messageResponsePacket.setMessage("服务端回复【" + messageRequestPacket.getMessage() + "】 ack");
            ByteBuf responseByteBuf = PacketCodec.INSTANCE.encode(ctx.alloc(), messageResponsePacket);
            ctx.channel().writeAndFlush(responseByteBuf);
        }

    }

    private boolean valid(LoginRequestPacket loginRequestPacket) {
        System.out.println(loginRequestPacket.getUserId() + " valid one2one");
        return true;
    }
}
