package Lesson12;

public class HomeWork1 {
    public static void main(String[] args) {
        BookManager bookManager = new BookManager();
        bookManager.setAuthor("Charles Darwin");
        bookManager.setTitle("On the Origin of Species");
        BookDisplay bookDisplay = new BookDisplay(bookManager);
        bookDisplay.displayBook();
    }
}

class BookManager {
    private String title;
    private String author;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}

class BookDisplay{
    private final BookManager bookManager;
    public BookDisplay(BookManager bookManager) {
       this.bookManager = bookManager;
    }
    public void displayBook(){
        System.out.println("Book's title is : " + bookManager.getTitle());
        System.out.println("Book's author is : " + bookManager.getAuthor());
    }
}
