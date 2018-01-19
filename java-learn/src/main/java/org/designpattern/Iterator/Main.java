package org.designpattern.Iterator;

public class Main {

    public static void main(String[] args) {
        BookShelf bs = new BookShelf(4);
        bs.appendBook(new Book("Java"));
        bs.appendBook(new Book("Scala"));
        bs.appendBook(new Book("Go"));
        bs.appendBook(new Book("Python"));
        Iterator it = bs.iterator();
        while (it.hasNext()){
            Book book = (Book)it.next();
            System.out.println(book.getName());
        }
    }
}
