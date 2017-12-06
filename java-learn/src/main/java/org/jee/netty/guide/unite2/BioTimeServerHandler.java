package org.jee.netty.guide.unite2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;

public class BioTimeServerHandler implements Runnable {

    private Socket socket;
    public BioTimeServerHandler(Socket socket){

        this.socket = socket;
    }
    @Override
    public void run() {

        BufferedReader in = null;
        PrintWriter out = null;
        try{
            in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            out = new PrintWriter(this.socket.getOutputStream());
            String currentTime = null;
            String body = null;
            while (true){
                body = in.readLine();
                if(body == null)
                    break;
                System.out.println("服务器收到请求命令: "+body);
                currentTime = "当前时间".equals(body) ? new Date(System.currentTimeMillis()).toString() : "unknow";
                Thread.sleep(3000);//模拟耗时操作
                out.println(currentTime);
                out.flush();
            }
        }catch (Exception e){
            if(in!=null){
                try{
                    in.close();
                }catch (Exception e1){
                    e1.printStackTrace();
                }
            }
            if(out != null){
                out.close();
                out = null;
            }
            if(this.socket != null){
                try{
                    this.socket.close();
                }catch (Exception e1){
                    e1.printStackTrace();
                }
                this.socket = null;
            }
        }

    }
}
