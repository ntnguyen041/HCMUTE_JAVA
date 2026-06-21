package C5_D1_Assignment.src.model;

import C5_D1_Assignment.src.service.Borrowable;

public class Book implements Borrowable {
    private String bookId;
    private String title;
    private String author;
    private String currentBorrowerId; 
    private String borrowDate;

    public Book(String bookId, String title, String author) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.currentBorrowerId = null;
    }

    @Override
    public void borrowBy(String readerId, String date) {
        if (!isAvailable()) {
            System.out.println("Book " + title + " is not available!");
            return;
        }

        this.currentBorrowerId = readerId;
        this.borrowDate = date;
        System.out.println("Book '" + title + "' borrowed by " + readerId);
    }

    @Override
    public void returnBook(String date) {
        System.out.println("Book " + title + " return on " + date);
        this.currentBorrowerId = null;
        this.borrowDate = null;
    }

    @Override
    public boolean isAvailable() {
        return currentBorrowerId == null;
    }

    @Override
    public String toString() {
        return "Book{BookId='" + bookId + "', title='" + title + "', author='" + author + "'}";
    }

    @Override
    public String getBorrowId() {
        return currentBorrowerId;
    }

    public String getBookId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

}