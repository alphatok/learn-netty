package com.brzyang.netty.frame;

import com.brzyang.netty.base.BaseNettyServer;
import com.brzyang.netty.frame.handler.FrameServerHandler;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.string.StringDecoder;

import java.util.ArrayList;
import java.util.List;

public class StringNettyServer extends BaseNettyServer {

    public static void main(String[] args) {
        List<ChannelHandler> channels = new ArrayList();
//        channels.add(new StringDecoder());
        channels.add(new FrameServerHandler());

        initNettyServer(channels);
    }

/**
 * Sun Oct 14 15:34:07 CST 2018: 服务端读到数据 -> 你好，欢迎关注我的微信公众号，《闪电侠的博客》!
 * Sun Oct 14 15:34:07 CST 2018: 服务端读到数据 -> 你好，欢迎关注我的微信公众号，《闪电侠的博客》!
 * Sun Oct 14 15:34:07 CST 2018: 服务端读到数据 -> 你好，欢迎关注我的微信公众号，《闪电侠的博客》!
 * Sun Oct 14 15:34:07 CST 2018: 服务端读到数据 -> 你好，欢迎关注我的微信公众号，《闪电侠的博客》!你好，欢迎关注我�
 * Sun Oct 14 15:34:07 CST 2018: 服务端读到数据 -> �微信公众号，《闪电侠的博客》!你好，欢迎关注我的微信公众号，《闪�
 * Sun Oct 14 15:34:07 CST 2018: 服务端读到数据 -> ��侠的博客》!你好，欢迎关注我的微信公众号，《闪电侠的博客》!你好�
 * Sun Oct 14 15:34:07 CST 2018: 服务端读到数据 -> �欢迎关注我的微信公众号，《闪电侠的博客》!你好，欢迎关注我的微信�
 * Sun Oct 14 15:34:07 CST 2018: 服务端读到数据 -> ��众号，《闪电侠的博客》!你好，欢迎关注我的微信公众号，《闪电侠的
 * Sun Oct 14 15:34:07 CST 2018: 服务端读到数据 -> 博客》!你好，欢迎关注我的微信公众号，《闪电侠的博客》!你好，欢迎�
 * Sun Oct 14 15:34:07 CST 2018: 服务端读到数据 -> ��注我的微信公众号，《闪电侠的博客》!你好，欢迎关注我的微信公众号
 * Sun Oct 14 15:34:07 CST 2018: 服务端读到数据 -> ，《闪电侠的博客》!你好，欢迎关注我的微信公众号，《闪电侠的博客�
 * Sun Oct 14 15:34:07 CST 2018: 服务端读到数据 -> �!你好，欢迎关注我的微信公众号，《闪电侠的博客》!你好，欢迎关注我
 * Sun Oct 14 15:34:07 CST 2018: 服务端读到数据 -> 的微信公众号，《闪电侠的博客》!你好，欢迎关注我的微信公众号，《�
 * Sun Oct 14 15:34:07 CST 2018: 服务端读到数据 -> �电侠的博客》!
 * Sun Oct 14 15:34:07 CST 2018: 服务端读到数据 -> 你好，欢迎关注我的微信公众号，《闪电侠的博客》!
 * Sun Oct 14 15:34:07 CST 2018: 服务端读到数据 -> 你好，欢迎关注我的微信公众号，《闪电侠的博客》!
 * Sun Oct 14 15:34:07 CST 2018: 服务端读到数据 -> 你好，欢迎关注我的微信公众号，《闪电侠的博客》!
 * Sun Oct 14 15:34:07 CST 2018: 服务端读到数据 -> 你好，欢迎关注我的微信公众号，《闪电侠的博客》!
 * */
}
