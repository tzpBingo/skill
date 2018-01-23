package org.designpattern.templatemethod;

public class StringDisplay extends AbstractDisplay {

    private String string;
    private int with;

    public StringDisplay(String string, int with) {
        this.string = string;
        this.with = with;
    }

    @Override
    public void open() {
        printLine();
    }

    @Override
    public void print() {
        System.out.println("|"+string+"|");
    }

    @Override
    public void close() {
        printLine();
    }

    public void printLine(){
        System.out.print("+");
        for (int i=1;i<=with;i++){
            System.out.print("-");
        }
        System.out.println("+");

    }
}
