package org.designpattern.templatemethod;

public class Main {
    public static void main(String[] args) {
        AbstractDisplay a1 = new CharDisplay('H');
        AbstractDisplay a2 = new StringDisplay("Java",4);
        a1.dispaly();
        a2.dispaly();
    }
}
