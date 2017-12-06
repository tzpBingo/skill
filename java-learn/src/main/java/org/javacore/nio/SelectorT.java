package org.javacore.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.channels.Selector;

/**
 * Created by admin on 2017/4/20.
 */
public class SelectorT {
    public static void main(String[] args)  throws IOException {

        String fromFile ="C:\\Users\\admin\\Desktop\\1181248_all.sql";
        String toFile ="C:\\Users\\admin\\Desktop\\all.sql";

        FileInputStream is = new FileInputStream(fromFile);
        FileOutputStream os = new FileOutputStream(toFile);

        FileChannel ic = is.getChannel();
        FileChannel oc = os.getChannel();

        Selector selector = Selector.open();


    }
}
