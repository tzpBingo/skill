package org.jee.netty.guide.unite2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class BioTimeClient {
    public static void main(String[] args) {
        int port = 8888;
        Socket socket = null;
        BufferedReader in  = null;
        PrintWriter out = null;
        try{
            socket = new Socket("127.0.0.1",port);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream());
            out.println("当前时间");
            out.flush();
            System.out.println("发送命令成功");
            String resp = in.readLine();
            System.out.println("当前时间："+resp);
        }catch (Exception e){

        }finally {

            if(out != null){
                out.close();
                out = null;
            }
            if (in !=null){
                try{
                    in.close();
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
            if(socket!=null){
                try {
                    socket.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
                socket=null;
            }
        }
    }
}
