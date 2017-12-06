package org.thinkjava.Chapter18;

import java.io.File;
import java.util.Arrays;


/**
 * Created by Administrator on 2016/6/17.
 */
public class DirList {

    public static void main(String[] args) {

        File file = new File(".");
        String[] list;
        list = file.list(new DirFilter(".*?i.*?"));
        Arrays.sort(list,String.CASE_INSENSITIVE_ORDER);
        for(String f : list){
            System.out.println(f);
        }
    }

}
