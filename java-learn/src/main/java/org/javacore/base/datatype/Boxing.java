package org.javacore.base.datatype;

/**
 * Created by admin on 2017/5/
 * 装箱和拆箱
 * 装箱Integer.valueOf(i)
 * 拆箱i.intValue()
 */
public class Boxing {

    public static void main(String[] args) {
        Integer a = 1;
        Integer b = 2;
        Integer c = 3;
        Integer d = 3;
        Integer e = 321;
        Integer f = 321;
        Long g = 3L;
        System.out.println(c == d);
        System.out.println(e == f);
        System.out.println(c == (a + b));
        System.out.println(c.equals(a + b));
        System.out.println(g == (a + b));
        System.out.println(g.equals(a + b));
        System.out.println(new Integer(2) == new Integer(2));

    }
}
