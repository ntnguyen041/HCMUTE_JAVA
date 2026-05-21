package Assignment3.src.model;
 
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.time.LocalDate;
public class Library {
    private ArrayList<Book> books;
    private ArrayList<Reader> readers;
    private ArrayList<BorrowSlip> borrowSlips;
    private ArrayList<BorrowSlip> borrowHistory;

    public Library() {
        this.books = new ArrayList<>();
        this.readers = new ArrayList<>();
        this.borrowSlips = new ArrayList<>();
        this.borrowHistory = new ArrayList<>();
    }

    public void addBook(Book book) {
        if(book == null) {
            throw new IllegalArgumentException("Book cannot be null.");
        }
        books.add(book);
    }
    public void removeBook(Book book) {
        if(book == null) {
            throw new IllegalArgumentException("Book cannot be null.");
        }
        books.remove(book);
    }

    public ArrayList<Book> searchBooks(String searchString, String type) {
        ArrayList<Book> results = new ArrayList<>();
        for (Book book : books) {
            if (book.searchByTitle(searchString, type)) {
                results.add(book);
            }
        }
        return results;
    }
    public void addReader(Reader reader) {
        if(reader == null) {
            throw new IllegalArgumentException("Reader cannot be null.");
        }
        readers.add(reader);
    }

    public void removeReader(Reader reader) {
        if(reader == null) {
            throw new IllegalArgumentException("Reader cannot be null.");
        }
        readers.remove(reader);
    }

    public void addBorrowSlip(BorrowSlip slip) {
        if(slip == null) {
            throw new IllegalArgumentException("Borrow slip cannot be null.");
        }
        borrowSlips.add(slip);
        borrowHistory.add(slip);
    }

    public void removeBorrowSlip(BorrowSlip slip) {
        if(slip == null) {
            throw new IllegalArgumentException("Borrow slip cannot be null.");
        }
        borrowSlips.remove(slip);
    }

    /// thống kê số lượng sách, độc giả, phiếu mượn
    public int getTotalBooks() {
        return books.size();
    }
    public int getTotalReaders() {
        return readers.size();
    }
    public int getTotalBorrowSlips() {
        return borrowSlips.size();
    }

    public int getActiveBorrowCount(Reader reader) {
        int count = 0;
        for (BorrowSlip slip : borrowSlips) {
            if (slip.getReader() == reader) {
                count++;
            }
        }
        return count;
    }

    public ArrayList<BorrowSlip> getOverdueBorrowSlips(String currentDate) {
        ArrayList<BorrowSlip> overdue = new ArrayList<>();
        LocalDate today = LocalDate.parse(currentDate);
        for (BorrowSlip slip : borrowSlips) {
            if (today.isAfter(LocalDate.parse(slip.getDueDate()))) {
                overdue.add(slip);
            }
        }
        return overdue;
    }

    public BorrowSlip getMostBorrowedBookSlip() {
        if (borrowHistory.isEmpty()) {
            return null;
        }

        Map<Book, Integer> counts = new HashMap<>();
        BorrowSlip bestSlip = null;
        int bestCount = 0;

        for (BorrowSlip slip : borrowHistory) {
            Book book = slip.getBook();
            int current = counts.getOrDefault(book, 0) + 1;
            counts.put(book, current);
            if (current > bestCount) {
                bestCount = current;
                bestSlip = slip;
            }
        }

        return bestSlip;
    }

    public Reader getMostBorrowedReader() {
        if (borrowHistory.isEmpty()) {
            return null;
        }

        Map<Reader, Integer> counts = new HashMap<>();
        Reader bestReader = null;
        int bestCount = 0;

        for (BorrowSlip slip : borrowHistory) {
            Reader reader = slip.getReader();
            int current = counts.getOrDefault(reader, 0) + 1;
            counts.put(reader, current);
            if (current > bestCount) {
                bestCount = current;
                bestReader = reader;
            }
        }

        return bestReader;
    }

    // Getters to expose internal collections for CLI usage
    public ArrayList<Book> getBooks() {
        return books;
    }

    public ArrayList<Reader> getReaders() {
        return readers;
    }

    public ArrayList<BorrowSlip> getBorrowSlips() {
        return borrowSlips;
    }

    public ArrayList<BorrowSlip> getBorrowHistory() {
        return borrowHistory;
    }
}
