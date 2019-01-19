package com.brzyang.netty.util;

import io.netty.channel.Channel;
import io.netty.util.Attribute;

public class LoginUtil {
    public static void markAsLogin(Channel channel) {
        channel.attr(Attributes.LOGIN).set(true);
    }

    public static void clearLoginStatus(Channel channel) {
        channel.attr(Attributes.LOGIN).set(false);
    }

    public static boolean hasLogin(Channel channel) {
        Attribute<Boolean> loginAttr = channel.attr(Attributes.LOGIN);
        return loginAttr.get() != null;
    }
}
