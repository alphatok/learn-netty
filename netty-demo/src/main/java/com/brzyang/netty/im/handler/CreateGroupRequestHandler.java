package com.brzyang.netty.im.handler;

import com.brzyang.netty.protocol.request.CreateGroupRequestPacket;
import com.brzyang.netty.protocol.response.CreateGroupResponsePacket;
import com.brzyang.netty.util.IdUtil;
import com.brzyang.netty.util.SessionUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class CreateGroupRequestHandler extends SimpleChannelInboundHandler<CreateGroupRequestPacket> {

    private static Logger logger = LoggerFactory.getLogger(CreateGroupRequestHandler.class);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, CreateGroupRequestPacket createGroupRequestPacket) throws Exception {
        List<String> userIdList = createGroupRequestPacket.getUserIds();

        List<String> userNameList = new ArrayList<>();
        // 1. 创建一个 channel 分组
        ChannelGroup channelGroup = new DefaultChannelGroup(ctx.executor());
        String groupId = IdUtil.randomId();

        // 2. 筛选出待加入群聊的用户的 channel 和 userName
        for (String userId : userIdList) {
            Channel channel = SessionUtil.getChannel(userId);
            if (channel != null) {
                channelGroup.add(channel);
                userNameList.add(SessionUtil.getSession(channel).getUsername());
            }else{
                logger.warn("groupId:{} userId:{} offline", groupId, userId);
            }
        }

        // ???
        SessionUtil.bindChannelGroup(groupId, channelGroup);

        // 3. 创建群聊创建结果的响应
        CreateGroupResponsePacket createGroupResponsePacket = new CreateGroupResponsePacket();
        createGroupResponsePacket.setSucc(true);
        createGroupResponsePacket.setGroupId(groupId);
        createGroupResponsePacket.setUsernames(userNameList);

        // 4. 给每个客户端发送拉群通知
        channelGroup.writeAndFlush(createGroupResponsePacket);

        logger.info("groupId:{} created succ, members:{}", groupId, userIdList);
    }
}
