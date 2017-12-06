package org.thinkjava.Chapter18;

import java.io.File;
import java.io.FilenameFilter;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2016/6/17.
 */
public class DirList3 {


    public static void main(String[] args) {
        File file = new File(".");
        for(String f : file.list(new FilenameFilter() {
            private Pattern pattern = Pattern.compile("src");
            @Override
            public boolean accept(File dir, String name) {
                return pattern.matcher(name).matches();
            }
        })){
            System.out.println(f);
        }
    }
}
