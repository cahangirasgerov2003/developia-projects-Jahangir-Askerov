package Lesson11_13.Finalize;

public class Finalize {
    public static void main(String[] args) {

        Book book1 = new Book("Don Quixote");
        Book book2 = new Book("Alice's Adventures in Wonderland");

        book1 = null;
        book2 = null;

        System.gc();

    }
}
