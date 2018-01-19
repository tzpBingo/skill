package org.designpattern.adapter;

public class PringBanner extends Banner implements Print{

    public PringBanner(String s){
        super(s);
    }

    @Override
    public void printWeak() {
        showWithParen();
    }

    @Override
    public void printStrong() {
        showWithAster();
    }
}
