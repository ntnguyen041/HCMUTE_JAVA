package Assignment4.src.model;
public class Student extends Reader {

    public Student(String fullName, String email, String phone, String ReaderType) {
        super(fullName, email, phone, ReaderType);
    }

    public int getMaxBorrow() {
        return 3;
    }

    public String toString() {
        return super.toString() + ", Type: Student | Max Borrow: " + getMaxBorrow() + " books";
    }

   
  
}
