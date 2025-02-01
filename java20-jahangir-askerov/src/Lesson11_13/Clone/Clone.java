package Lesson11_13.Clone;

public class Clone {
    public static void main(String[] args) {
        Book book1 = new Book("Treasure Island");
        try {
            Book book2 = (Book) (book1.clone());

            System.out.println("Book1 is the same as book2 : " + book1.equals(book2));

            String book2Name = book2.getBookName();
            System.out.println(book2Name);
        } catch (CloneNotSupportedException e) {
            e.getStackTrace();
        }
    }
}
