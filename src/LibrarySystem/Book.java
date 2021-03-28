package LibrarySystem;

public class Book {
    String bookid;
    String isbn;
    String available;
    String reserve;
    String author;
    String year;
    String title;
    String place;
    String publisher;
    String copies;
    
    public Book(String bookid, String isbn, String title, String year, String author, String place, 
                String publisher, String available, String reserve, String copies){
        this.isbn = isbn;
        this.bookid = bookid;
        this.author = author;
        this.year = year;
        this.title = title;
        this.place = place;
        this.publisher = publisher;
        this.available = available;
        this.reserve = reserve;
        this.copies = copies;
    }
}