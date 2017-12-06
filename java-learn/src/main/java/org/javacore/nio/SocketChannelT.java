package org.javacore.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * Created by admin on 2017/4/20.
 */
public class SocketChannelT {

    public static void main(String[] args) throws IOException {
        SocketChannel socketChannel = SocketChannel.open();

        socketChannel.connect(new InetSocketAddress("jenkov.com", 80));

        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        int size = socketChannel.read(byteBuffer);
    }
}
