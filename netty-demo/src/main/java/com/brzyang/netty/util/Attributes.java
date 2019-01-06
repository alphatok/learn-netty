package com.brzyang.netty.util;

import com.brzyang.netty.im.bean.Session;
import io.netty.util.AttributeKey;

public interface Attributes {
    AttributeKey<Boolean> LOGIN = AttributeKey.newInstance("one2one");
    AttributeKey<Session> SESSION = AttributeKey.newInstance("im-session");
}
