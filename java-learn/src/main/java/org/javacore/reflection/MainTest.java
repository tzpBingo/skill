package org.javacore.reflection;

import java.lang.reflect.Method;

public class MainTest {


    public static void main(String[] args) throws Exception{

        System.out.println("开始");
        Class main = Class.forName("org.javacore.reflection.MainTest2");
        Method m = main.getMethod("main",String[].class);
        m.invoke(main,(Object)new String[]{});
        System.out.println("结束");
    }


}
