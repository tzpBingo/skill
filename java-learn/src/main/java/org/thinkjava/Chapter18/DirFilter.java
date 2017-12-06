package org.thinkjava.Chapter18;

import java.io.File;
import java.io.FilenameFilter;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2016/6/17.
 */
public class DirFilter implements FilenameFilter{

        private Pattern pattern;
        public DirFilter(String regex){
            pattern = Pattern.compile(regex);
        }
        @Override
        public boolean accept(File dir, String name) {
            return pattern.matcher(name).matches();
        }
}
