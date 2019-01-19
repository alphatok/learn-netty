package com.brzyang.netty.protocol.request;

import com.brzyang.netty.protocol.Packet;
import com.brzyang.netty.protocol.command.Command;

public class LogoutRequestPacket extends Packet {

    private String userId;
    private String username;


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public Byte getCommand() {
        return Command.LOGOUT_REQUEST;
    }
}
