package org.jcip;

/**
 * NoVisibility
 * <p/>
 * Sharing variables without synchronization
 *
 * @author Brian Goetz and Tim Peierls
 */

public class NoVisibility {
    private static boolean ready;
    private static int number;

    private static class ReaderThread extends Thread {
        public void run() {
            while (!ready)
                Thread.yield();//当前线程让资源
            System.out.println(number);
        }
    }

    public static void main(String[] args) {
        new ReaderThread().start();
        new ReaderThread().start();
        new ReaderThread().start();
        new ReaderThread().start();
        new ReaderThread().start();
        new ReaderThread().start();



        ready = true;
        number = 42;
    }
}
