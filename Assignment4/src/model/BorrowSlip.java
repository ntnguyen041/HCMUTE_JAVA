package Assignment4.src.model;
import java.time.LocalDate;

public class BorrowSlip {

    private String slipId;
    private Reader reader;
    private Book book;
    private String borrowDate;
    private String returnDate;

    public BorrowSlip(Reader reader2, Book book, String borrowDate, String returnDate) {
        this.slipId = "SLIP-" + System.currentTimeMillis();
        this.reader = reader2;
        this.book = book;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
    }






    // Getters and setters
    public String getSlipId() {
        return slipId;
    }

    public void setSlipId(String slipId) {
        this.slipId = slipId;
    }

    public Reader getReader() {
        return reader;
    }

    public void setReader(Reader reader) {
        this.reader = reader;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public String getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(String borrowDate) {
        this.borrowDate = borrowDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public String getDueDate() {
        return returnDate;
    }

    public boolean isOverdue() {
        return LocalDate.now().isAfter(LocalDate.parse(returnDate));
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }
 
    // Hiển thị thông tin phiếu mượn

    public void displayInfo() {
        System.out.println("╔════════════════════════════════════════════════════╗");
        System.out.printf("║ Title: %-15s | Author: %-15s ║\n", book.getTitle(), book.getAuthor());
        System.out.printf("║ Reader: %-15s | Borrow Date: %-10s ║\n", reader.getFullName(), borrowDate);
        System.out.printf("║ Due Date: %-16s ║\n", returnDate);
        System.out.println("╚════════════════════════════════════════════════════╝");
    }
}
