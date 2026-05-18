package Assignment2.src;
import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;

class Book {
    private String title;
    private String author;
    private double price;
    private int year;
    public Book() {
        this.title = "Unknown";
        this.author = "Unknown";
        this.price = 0;
        this.year = 2000;
    }
    public Book(String title, String author, double price, int year) {
        this.title = title;
        this.author = author;
        this.price = price;
        this.year = year;
    }
    public Book(String title, String author) {
        int year = LocalDate.now().getYear();
        this.title = title;
        this.author = author;
        this.price = 100000;
        this.year = year;
    }
    public double applyDiscount(double percent) {
        if (percent < 0 || percent > 100) {
            throw new IllegalArgumentException("Discount percentage must be between 0 and 100.");
        }
        double discountAmount = price * (percent / 100);
        price -= discountAmount;
        return price;
    }
    public void displayInfo() {
        System.out.println("Sách: " + title + " - Tác giả: " + author + " - Năm: " + year + " - Giá: " + price);
    }
}
public class Bai1 {
    public static void main(String[] args) {
        List<Book> books = new ArrayList<>();
        books.add(new Book());
        books.add(new Book("Java Programming", "John Doe", 29.99, 2020));
        books.add(new Book("Python Programming", "Jane Doe"));
        for (Book book : books) {
            book.applyDiscount(10);
            book.displayInfo();
            System.out.println();
        }
    }
}

