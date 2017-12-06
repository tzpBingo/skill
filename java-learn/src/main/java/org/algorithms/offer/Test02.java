package org.algorithms.offer;

public class Test02 {

    /**
     * 单例模式 饿汉式  线程安全
     */

    public static class Singleton{
         private final static Singleton instance = new Singleton();
         private Singleton(){}
         public static Singleton getInstance(){
             return instance;
         }
    }

    /**
     * 单例模式 懒汉式 非线程安全
     */

    public static class Singleton1{
        private static Singleton1 instance=null;
        private Singleton1(){}
        public static Singleton1 getInstance(){
            if(instance==null){
                instance = new Singleton1();
            }
            return instance;
        }
    }

    /**
     * 懒汉式 线程安全 多线程环境下效率不高
     */

    public static class Singleton2{
        private static Singleton2 instance=null;
        private Singleton2(){}
        public static synchronized Singleton2 getInstance(){
            if(instance==null){
                instance = new Singleton2();
            }
            return instance;
        }
    }

    /**
     * 单例模式，懒汉式，变种，线程安全
     */
    public static class Singleton3 {
        private static Singleton3 instance = null;

        static {
            instance = new Singleton3();
        }

        private Singleton3() {

        }

        public static Singleton3 getInstance() {
            return instance;
        }
    }

    /**
     * 使用静态内部类 线程安全
     */

    public static class Singleton4{
        private final static class SingletonHolder{
            private static Singleton4  instance = new Singleton4();
        }
        private Singleton4(){}
        public static Singleton4 getInstance(){
            return SingletonHolder.instance;
        }

    }

    /**
     * 使用枚举 线程安全
     */

    public enum Singleton5{
        INSTANCE;
    }

    /**
     * 实现接口的形式写枚举单例模式
     */

    // 定义单例模式中需要完成的代码逻辑
    public interface MySingleton {
        public void doSomething();
    }

    public enum Singleton7 implements MySingleton {
        INSTANCE {
            @Override
            public void doSomething() {
                System.out.println("complete singleton");
            }
        };

        public static MySingleton getInstance() {
            return Singleton7.INSTANCE;
        }
    }


    /**
     * 使用双重校验锁，线程安全【推荐】
     */
    public static class Singleton6 {
        private volatile static Singleton6 instance = null;

        private Singleton6() {}

        public static Singleton6 getInstance() {
            if (instance == null) {
                synchronized (Singleton6.class) {
                    if (instance == null) {
                        instance = new Singleton6();
                    }
                }
            }

            return instance;
        }
    }

    public static void main(String[] args) {
        System.out.println(Singleton.getInstance() == Singleton.getInstance());
        System.out.println(Singleton1.getInstance() == Singleton1.getInstance());
        System.out.println(Singleton2.getInstance() == Singleton2.getInstance());
        System.out.println(Singleton3.getInstance() == Singleton3.getInstance());
        System.out.println(Singleton4.getInstance() == Singleton4.getInstance());
        System.out.println(Singleton5.INSTANCE == Singleton5.INSTANCE);
        System.out.println(Singleton6.getInstance() == Singleton6.getInstance());
    }

}
