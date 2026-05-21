
package Assignment3.src.model;

public class Book {

    private String title;
    private String author;
    private int year;
    private int stock;
    
    public Book(String title, String author, int year, int stock) {
        this.title = title;
        this.author = author;
        this.year = year;
        this.stock = stock;
    }

    // Getters and setters
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

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }   

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }


    /// Tìm kiếm gần đúng theo tên hoặc tác giả, không phân biệt hoa thường
    public boolean searchByTitle(String searchString, String type) {
        if (searchString == null || type == null) {
            return false;
        }

        String query = searchString.trim().toLowerCase();
        String searchType = type.trim().toLowerCase();

        if (searchType.equals("title")) {
            return title != null && title.toLowerCase().contains(query);
        }
        if (searchType.equals("author")) {
            return author != null && author.toLowerCase().contains(query);
        }
        if (searchType.equals("year")) {
            try {
                return year == Integer.parseInt(query);
            } catch (NumberFormatException e) {
                return false;
            }
        }
        if (searchType.equals("all")) {
            boolean byTitle = title != null && title.toLowerCase().contains(query);
            boolean byAuthor = author != null && author.toLowerCase().contains(query);
            boolean byYear = false;
            try {
                byYear = year == Integer.parseInt(query);
            } catch (NumberFormatException e) {
                byYear = false;
            }
            return byTitle || byAuthor || byYear;
        }
        return false;
    }

    public int editStock(int newStock, String type) {
        if(newStock < 0 && type.equals("add")){ 
            throw new IllegalArgumentException("Stock cannot be negative");
        }
        if (newStock < 0 && type.equals("remove") && this.stock - newStock < 0) {
            throw new IllegalArgumentException("Stock cannot be negative");
        }
        if(type.equals("add")){
            this.stock += newStock;
        }
        if(type.equals("remove")){
            this.stock -= newStock;
        }
        return this.stock;
    }
    public void displayInfo() {
        System.out.println("╔════════════════════════════════════════════════════╗");
        System.out.printf("║ Title: %-15s | Author: %-15s ║\n", title, author);
        System.out.printf("║ Year : %-15d | Stock : %-15d ║\n", year, stock);
        System.out.println("╚════════════════════════════════════════════════════╝");
    }
}