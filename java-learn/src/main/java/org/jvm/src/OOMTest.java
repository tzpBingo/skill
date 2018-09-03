package org.jvm.src;

import java.util.ArrayList;
import java.util.List;

public class OOMTest {

    public static void main(String[] args) {
        List list = new ArrayList<Object>();
        long i = 0;
        while (true){
            Object o = new Object();
            list.add(o);
            o = null;
            if(i % 999999==0){//定时释放内存
                list=null;
                list = new ArrayList<Object>();
            }
            i++;
        }
    }
}
