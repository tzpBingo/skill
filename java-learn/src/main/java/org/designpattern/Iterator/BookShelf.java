package org.designpattern.Iterator;

public class BookShelf implements Aggregate {

    private Book[] books;

    private int last=0;

    public BookShelf(int maxsize){
        this.books = new Book[maxsize];
    }

    public Book getBookAt(int index){
        return this.books[index];
    }

    public void appendBook(Book book){
        this.books[last] = book;
        last ++ ;
    }

    public int getLength(){
        return last;
    }

    @Override
    public Iterator iterator() {
        return new Itr();
//        return new BookShelfIterator(this);
    }

    public class Itr implements Iterator {
        private int cursor;
        @Override
        public boolean hasNext() {
            if(cursor<last){
                return true;
            }else{
                return false;
            }
        }

        @Override
        public Object next() {
            Book book = books[cursor];
            cursor++;
            return book;
        }
    }


}
