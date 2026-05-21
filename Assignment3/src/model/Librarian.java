package Assignment3.src.model;
import java.time.LocalDate;
import Assignment3.src.util.ReaderValidation;
 

public class Librarian {
    
    private String librarianId;
    private String fullName;
    private String email;
    private String phone;
    
    public Librarian(String fullName, String email, String phone) {
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
    }

    // Getters and setters
    public String getLibrarianId() {return librarianId;}   
    public void setLibrarianId(String librarianId) {this.librarianId = librarianId;}
    public String getFullName() {return fullName;}
    public void setFullName(String fullName) {
        if (fullName == null || fullName.trim().isEmpty()) {
            throw new IllegalArgumentException("Full name cannot be null or empty.");
        }
        this.fullName = fullName;
    }
    public String getEmail() {return email;}
    public void setEmail(String email) {
        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException("Invalid email format.");
        }
        this.email = email;
    }
    public String getPhone() {return phone;}
    public void setPhone(String phone) {
        if (phone == null || phone.trim().isEmpty()) {
            throw new IllegalArgumentException("Phone cannot be null or empty.");
        }
        this.phone = phone;
    }

    // Cho mượn sách
    public void borrowBook(Reader reader, Book book) {
        if (book.getStock() > 0) {
            book.setStock(book.getStock() - 1);
            BorrowSlip slip = new BorrowSlip(reader, book, LocalDate.now().toString(), LocalDate.now().plusDays(14).toString());
            System.out.println("Doc gia " + reader.getFullName() + " da muon sach: " + book.getTitle());
            slip.displayInfo();
        } else {
            System.out.println("Sach " + book.getTitle() + " da het hang.");
        }
    }

    // Trả sách
    public void returnBook(BorrowSlip slip) {
        Book book = slip.getBook();
        book.setStock(book.getStock() + 1);
        if (slip.isOverdue()) {
            long lateDays = ReaderValidation.calculateLateDays(slip.getBorrowDate(), slip.getReturnDate());
            double fine = ReaderValidation.calculateFine(lateDays);
            System.out.println("Doc gia " + slip.getReader().getFullName() + " phai tra tien phat: " + fine);
        } else {
            System.out.println("Tra sach thanh cong. Khong co tien phat.");
        }
    }

 


    public void displayInfo() {
        System.out.println("╔════════════════════════════════════════════════════╗");
        System.out.printf("║ Full Name: %-15s | Email: %-15s ║\n", fullName, email);
        System.out.printf("║ Phone: %-15s ║\n", phone);
        System.out.println("╚════════════════════════════════════════════════════╝");
    }
}
