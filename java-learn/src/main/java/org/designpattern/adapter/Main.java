package org.designpattern.adapter;

public class Main {
    public static void main(String[] args) {
        Print p = new PringBanner("Java");
        p.printStrong();
        p.printWeak();
    }
}
