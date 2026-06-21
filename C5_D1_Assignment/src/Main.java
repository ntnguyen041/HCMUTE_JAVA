package C5_D1_Assignment.src;
import java.util.ArrayList;
import java.util.List;

import C5_D1_Assignment.src.utils.LibraryUtils;
import C5_D1_Assignment.src.model.Book;
import C5_D1_Assignment.src.model.LibraryManager;
 
import C5_D1_Assignment.src.model.Reader;
import C5_D1_Assignment.src.service.Borrowable;
import C5_D1_Assignment.src.service.Notifiable;

public class Main {
    public static void main(String[] args) {
		Borrowable book1 = new Book("B001", "Clean Code", "Robert Martin");
        Borrowable book2 = new Book("B002", "Design Patterns", "GoF");
        book1.borrowBy("R001", "2024-09-01");
        System.out.println("Available: " + book2.isAvailable()); // true
        System.out.println(Borrowable.isValidBorrowDuration(10)); // true
        System.out.println(Borrowable.isValidBorrowDuration(20)); // false
        System.out.println(book1.calculateFine(3)); // 15000.0
        book1.returnBook("2024-09-15");
		////////////////////////
 		LibraryManager mgr = new LibraryManager();
 		List<Borrowable> items = new ArrayList<>();
        Book b1 = new Book("B001", "Clean Code", "Robert Martin");
        Book b2 = new Book("B002", "Design Patterns", "GoF");
        b1.borrowBy("R001", "2024-09-01");
		items.add(b1); items.add(b2);
        mgr.processAllBorrowable(items);
        List<Notifiable> readers = new ArrayList<>();
        readers.add(new Reader("R001", "Nguyen Van A"));
        readers.add(new Reader("R002", "Tran Thi B"));
        mgr.notifyAll(readers, "Thu vien se dong cua ngay 20/9.");
    
    }

}