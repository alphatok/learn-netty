package io;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class IOServer {
    public static void main(String[] args) throws Exception {

        try (ServerSocket serverSocket = new ServerSocket(8000)) {

            // (1) 接收新连接线程
            new Thread(() -> {
                while (true) {
                    try (Socket socket = serverSocket.accept()) {
                        InetSocketAddress remoteSocketAddress = (InetSocketAddress) socket.getRemoteSocketAddress();
                        System.out.println(remoteSocketAddress.getPort());
                        System.out.println(remoteSocketAddress.getHostName());
                        System.out.println(remoteSocketAddress.getAddress());
                        // (2) 每一个新的连接都创建一个线程，负责读取数据
                        new Thread(() -> {
                            try {
                                int len;
                                byte[] data = new byte[1024];
                                InputStream inputStream = socket.getInputStream();
                                // (3) 按字节流方式读取数据
                                while ((len = inputStream.read(data)) != -1) {
                                    System.out.println(new String(data, 0, len));
                                }
                            } catch (IOException e) {
                            }
                        }).start();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }
}