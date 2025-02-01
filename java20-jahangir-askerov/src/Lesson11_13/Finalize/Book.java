package Lesson11_13.Finalize;

public class Book {
    private String bookName;

    public Book(String name) {
        this.bookName = name;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

//    @Override
//    protected void finalize() throws Throwable {
//        System.out.println("Object was deleted " + this);
//    }

}
