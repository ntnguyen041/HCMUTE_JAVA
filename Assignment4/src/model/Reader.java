package Assignment3.src.model;
public class Reader {
    private String readerId;
    private String fullName;
    private String email;
    private String phone;
    private String ReaderType;

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
