package org.jee.netty.guide.unite2;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class BioSyncTimeServer {
    public static void main(String[] args) throws IOException{
        int port = 8888;
        ServerSocket server = null;
        try{
            server = new ServerSocket(port);
            System.out.println("时间服务器启动，端口："+port);
            Socket socket = null;
            BioTimeServerHandlerExecutePool singleExecutor = new BioTimeServerHandlerExecutePool(50,1000);
            while(true){
                socket = server.accept();
                singleExecutor.execute(new BioTimeServerHandler(socket));
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
