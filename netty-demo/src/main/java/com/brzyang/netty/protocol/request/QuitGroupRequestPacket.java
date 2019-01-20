package com.brzyang.netty.protocol.request;

import com.brzyang.netty.protocol.Packet;
import com.brzyang.netty.protocol.command.Command;

import java.util.List;

public class QuitGroupRequestPacket extends Packet {
    private String groupId;

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    @Override
    public Byte getCommand() {
        return Command.QUIT_GROUP_REQUEST;
    }
}
