package org.jee.netty.guide.unite2;

public class NioTimeServer {

    public static void main(String[] args) {
        int port = 8888;
        NioMultiplexerTimeServer timeServer = new NioMultiplexerTimeServer(port);
        new Thread(timeServer,"NIO-TIMESERVER-01").start();
    }

}
