package org.javacore.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by admin on 2017/4/19.
 */
public class FileCopy {

    public static void main(String[] args) throws IOException {
        String fromFile ="H:\\下载\\CentOS-7-x86_64-DVD-1511.iso";
        String toFile ="H:\\下载\\CentOS-7.iso";

        FileInputStream is = new FileInputStream(fromFile);
        FileOutputStream os = new FileOutputStream(toFile);

        FileChannel ic = is.getChannel();
        FileChannel oc = os.getChannel();

        ByteBuffer bb = ByteBuffer.allocateDirect(1024);//分配JVM之外内存 大文件适用
        //ByteBuffer bb = ByteBuffer.allocate(1024);//分配JVM内存

        long st = System.currentTimeMillis();
        while(true){
            bb.clear();//必须清空，否则文件会变得很大
            int r = ic.read(bb);
            if(r==-1)
                break;
            bb.flip();//将Buffer从写模式切换到读模式
            oc.write(bb);
        }
        System.out.println(System.currentTimeMillis()-st);
    }
}
