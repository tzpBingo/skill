package org.javacore.base.constructor;

/**
 * Created by admin on 2017/5/23.
 * 构造方法调用问题
 * 子类构造方法会首先默认调用父类的无参构造方法，无论是否显式写了super();
 */
public class SonClass extends FatherClass {

    private static int countSon;

    static {
        System.out.println("子类可以访问父类的静态属性count " + count);
        System.out.println("子类的静态属性countSon初始化:" + countSon);
    }

    public SonClass(String name) {
        //super(name);
        System.out.println("执行了子类的构造方法SonClass(String name) " + name);
    }

    public SonClass() {
        System.out.println("执行了子类的无参构造方法");
    }

    public static void main(String[] args) {
        new SonClass("aaa");
        new SonClass();
    }
}
