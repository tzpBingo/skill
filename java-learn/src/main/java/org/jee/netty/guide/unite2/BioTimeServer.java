package org.jee.netty.guide.unite2;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class BioTimeServer {

    public static void main(String[] args) throws IOException{
        int port = 8888;
        ServerSocket server = null;
        try{
            server = new ServerSocket(port);
            System.out.println("时间服务器启动，端口："+port);
            Socket socket = null;
            while(true){
                socket = server.accept();
                new Thread(new BioTimeServerHandler(socket)).start();
            }
        }finally {
            if(server != null){
                System.out.println("时间服务器关闭");
                server.close();
                server = null;
            }
        }
    }

}
