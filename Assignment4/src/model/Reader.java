package Assignment4.src.model;
public class Reader {
    private String readerId;
    private String fullName;
    private String email;
    private String phone;
    private String ReaderType;

 
    protected int    currentBorrowCount;
 
    // Các abstract method hiện có (từ B1-B3):
    public abstract String getInfo();
    public abstract double calculateLateFee(int daysLate);
    public abstract int    getMaxBorrowLimit();

    public final BorrowResult processBorrow(Book book) {
        // Bước 1: kiểm tra độc giả còn quyền mượn không (cố định)
        if (!checkBorrowQuota()) {
            return new BorrowResult(false, "Da dat gioi han muon: " + getMaxBorrowLimit() + " cuon");
        }
        // Bước 2: kiểm tra điều kiện đặc thù của từng loại độc giả (abstract)
        if (!checkSpecialCondition(book)) {
            return new BorrowResult(false, getSpecialConditionMessage());
        }
        // Bước 3: trừ tồn kho sách (cố định)
        book.decreaseStock();
        currentBorrowCount++;
        // Bước 4: ghi nhận và thông báo (có thể override — Hook method)
        onBorrowSuccess(book);
        return new BorrowResult(true, "Muon thanh cong: " + book.getTitle());
    }
        private boolean checkBorrowQuota() {
        return currentBorrowCount < getMaxBorrowLimit();
    }
        protected abstract boolean checkSpecialCondition(Book book);
    protected abstract String  getSpecialConditionMessage();
 
    // Bước 4 — Hook: có thể override để thêm hành động sau khi mượn thành công
    protected void onBorrowSuccess(Book book) {
        System.out.println(getFullName() + " muon: " + book.getTitle());
    }
 
    public String getFullName() { return fullName; }

public class BorrowResult {
    private boolean success;
    private String  message;
    public BorrowResult(boolean success, String message) {
        this.success = success;
        this.message = message;
    }
    public boolean isSuccess() { return success; }
    public String  getMessage() { return message; }
}


    public Reader(String fullName, String email, String phone, String ReaderType) {
        setFullName(fullName);
        setEmail(email);
        setPhone(phone);
        setReaderType(ReaderType);
    }

    // Getters and setters

    public String getReaderId() {
        return readerId;
    }

    public void setReaderId(String readerId) {
        this.readerId = readerId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        if (fullName == null || fullName.trim().isEmpty()) {
            throw new IllegalArgumentException("Full name cannot be null or empty.");
        }
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException("Invalid email format.");
        }
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        if (phone == null || phone.trim().isEmpty()) {
            throw new IllegalArgumentException("Phone cannot be null or empty.");
        }
        this.phone = phone;
    }

    public String getReaderType() {
        return ReaderType;
    }

    public void setReaderType(String ReaderType) {
        if (ReaderType == null || ReaderType.trim().isEmpty()) {
            throw new IllegalArgumentException("Reader type cannot be null or empty.");
        }
        String normalizedType = ReaderType.trim();
        if (!normalizedType.equalsIgnoreCase("Sinh viên") && !normalizedType.equalsIgnoreCase("Giáo viên")
                && !normalizedType.equalsIgnoreCase("Khác")) {
            throw new IllegalArgumentException("Invalid card type. Please choose 'Sinh viên', 'Giáo viên', or 'Khác'.");
        }
        this.ReaderType = normalizedType;
    }

    public int getMaxBooksAllowed() {
        switch (ReaderType.toLowerCase()) {
            case "sinh viên":
                return 3;
            case "giáo viên":
                return 5;
            case "khác":
                return 3;
            default:
                throw new IllegalArgumentException("Invalid card type: " + ReaderType);
        }
    }
    public void displayInfo() {
        System.out.println("╔════════════════════════════════════════════════════╗");
        System.out.printf("║ Full Name: %-20s ║\n", fullName);
        System.out.printf("║ Email: %-22s ║\n", email);
        System.out.printf("║ Phone: %-22s ║\n", phone);
        System.out.printf("║ Card Type: %-18s ║\n", ReaderType);
        System.out.printf("║ Max Books Allowed: %-10d ║\n", getMaxBooksAllowed());
        System.out.println("╚════════════════════════════════════════════════════╝");
    }

   
  
}
