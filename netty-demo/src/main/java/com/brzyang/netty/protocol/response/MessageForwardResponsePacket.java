package com.brzyang.netty.protocol.response;

import com.brzyang.netty.protocol.Packet;
import com.brzyang.netty.protocol.command.Command;

public class MessageForwardResponsePacket extends Packet {
    private String message;
    private String fromUserId;
    private String fromUsername;
    private String dstUserId;
    private String dstUsername;

    public String getDstUsername() {
        return dstUsername;
    }

    public void setDstUsername(String dstUsername) {
        this.dstUsername = dstUsername;
    }

    public String getDstUserId() {
        return dstUserId;
    }

    public void setDstUserId(String dstUserId) {
        this.dstUserId = dstUserId;
    }

    public String getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(String fromUserId) {
        this.fromUserId = fromUserId;
    }

    public String getFromUsername() {
        return fromUsername;
    }

    public void setFromUsername(String fromUsername) {
        this.fromUsername = fromUsername;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public Byte getCommand() {
        return Command.MESSAGE_FORWARD_RESPONSE;
    }
}
