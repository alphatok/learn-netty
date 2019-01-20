package com.brzyang.netty.protocol.response;

import com.brzyang.netty.im.bean.Session;
import com.brzyang.netty.protocol.Packet;
import com.brzyang.netty.protocol.command.Command;

import java.util.List;

public class ListGroupMembersResponsePacket extends Packet {
    private String groupId;
    private List<Session> sessions;

    public List<Session> getSessions() {
        return sessions;
    }

    public void setSessions(List<Session> sessions) {
        this.sessions = sessions;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    @Override
    public Byte getCommand() {
        return Command.LIST_GROUP_RESPONSE;
    }
}
