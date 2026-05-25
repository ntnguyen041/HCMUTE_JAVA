package Assignment4.src;

import java.util.Scanner;
import java.time.LocalDate;
import java.util.ArrayList;
import Assignment3.src.model.Book;
import Assignment3.src.model.Reader;
import Assignment3.src.model.BorrowSlip;
import Assignment3.src.model.Library;
import Assignment3.src.util.ReaderValidation;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Library library = new Library();

		boolean running = true;
		while (running) {
			clearScreen();
			System.out.println("\n===== LIBRARY MENU =====");
			System.out.println("1. Add book");
			System.out.println("2. List books");
			System.out.println("3. Add reader");
			System.out.println("4. List readers");
			System.out.println("5. Borrow book");
			System.out.println("6. Return book");
			System.out.println("7. Search books");
			System.out.println("8. List overdue slips");
			System.out.println("9. Statistics");
			System.out.println("10. Exit");
			System.out.print("Choose an option: ");
			String choice = sc.nextLine().trim();

			switch (choice) {
				case "1":
					addBook(sc, library);
					break;
				case "2":
					listBooks(library);
					break;
				case "3":
					addReader(sc, library);
					break;
				case "4":
					listReaders(library);
					break;
				case "5":
					borrowBook(sc, library);
					break;
				case "6":
					returnBook(sc, library);
					break;
				case "7":
					searchBooks(sc, library);
					break;
				case "8":
					listOverdueSlips(sc, library);
					break;
				case "9":
					showStats(library);
					break;
				case "10":
					running = false;
					break;
				default:
					System.out.println("Invalid option. Try again.");
			}
		}

		sc.close();
		System.out.println("Exiting. Goodbye!");
	}

	private static void clearScreen() {
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}

	private static void addBook(Scanner sc, Library library) {
		try {
			System.out.print("Title: ");
			String title = sc.nextLine();
			System.out.print("Author: ");
			String author = sc.nextLine();
			System.out.print("Year: ");
			int year = Integer.parseInt(sc.nextLine().trim());
			System.out.print("Stock: ");
			int stock = Integer.parseInt(sc.nextLine().trim());
			Book b = new Book(title, author, year, stock);
			library.addBook(b);
			System.out.println("Book added.");
		} catch (Exception e) {
			System.out.println("Failed to add book: " + e.getMessage());
		}
	}

	private static void listBooks(Library library) {
		ArrayList<Book> books = library.getBooks();
		if (books.isEmpty()) {
			System.out.println("No books available.");
			return;
		}
		for (int i = 0; i < books.size(); i++) {
			System.out.println((i + 1) + ".");
			books.get(i).displayInfo();
		}
	}

	private static void addReader(Scanner sc, Library library) {
		try {
			System.out.print("Full name: ");
			String name = sc.nextLine();
			System.out.print("Email: ");
			String email = sc.nextLine();
			System.out.print("Phone: ");
			String phone = sc.nextLine();
			System.out.print("Card type (1 - Sinh viên, 2 - Giáo viên, 3 -  Khác): ");
			String type = sc.nextLine();

            if (type.equals("1")) {
                type = "Sinh viên";
            } else if (type.equals("2")) {
                type = "Giáo viên";
            } else if (type.equals("3")) {
                type = "Khác";
            } else {
                System.out.println("Invalid card type.");
                return;
            }

			Reader r = new Reader(name, email, phone, type);
			library.addReader(r);
			System.out.println("Reader added.");
		} catch (Exception e) {
			System.out.println("Failed to add reader: " + e.getMessage());
		}
	}

	private static void listReaders(Library library) {
		ArrayList<Reader> readers = library.getReaders();
		if (readers.isEmpty()) {
			System.out.println("No readers available.");
			return;
		}
		for (int i = 0; i < readers.size(); i++) {
			System.out.println((i + 1) + ".");
			readers.get(i).displayInfo();
		}
	}

	private static void borrowBook(Scanner sc, Library library) {
		if (library.getReaders().isEmpty()) {
			System.out.println("No readers available. Add a reader first.");
			return;
		}
		if (library.getBooks().isEmpty()) {
			System.out.println("No books available. Add a book first.");
			return;
		}

		listReaders(library);
		System.out.print("Select reader (number): ");
		int rIndex = Integer.parseInt(sc.nextLine().trim()) - 1;
		if (rIndex < 0 || rIndex >= library.getReaders().size()) {
			System.out.println("Invalid reader.");
			return;
		}
		Reader reader = library.getReaders().get(rIndex);
		int activeBorrowCount = library.getActiveBorrowCount(reader);
		if (activeBorrowCount >= reader.getMaxBooksAllowed()) {
			System.out.println("Reader has reached the maximum allowed books: " + reader.getMaxBooksAllowed());
			return;
		}

		listBooks(library);
		System.out.print("Select book (number): ");
		int bIndex = Integer.parseInt(sc.nextLine().trim()) - 1;
		if (bIndex < 0 || bIndex >= library.getBooks().size()) {
			System.out.println("Invalid book.");
			return;
		}
		Book book = library.getBooks().get(bIndex);

		if (book.getStock() <= 0) {
			System.out.println("Book out of stock.");
			return;
		}

		book.setStock(book.getStock() - 1);
		BorrowSlip slip = new BorrowSlip(reader, book, LocalDate.now().toString(), LocalDate.now().plusDays(14).toString());
		library.addBorrowSlip(slip);
		System.out.println("Borrow slip created:");
		slip.displayInfo();
	}

	private static void returnBook(Scanner sc, Library library) {
		ArrayList<BorrowSlip> slips = library.getBorrowSlips();
		if (slips.isEmpty()) {
			System.out.println("No active borrow slips.");
			return;
		}
		for (int i = 0; i < slips.size(); i++) {
			System.out.println((i + 1) + ". " + slips.get(i).getSlipId() + " - " + slips.get(i).getBook().getTitle());
		}
		System.out.print("Select slip to return (number): ");
		int sIndex = Integer.parseInt(sc.nextLine().trim()) - 1;
		if (sIndex < 0 || sIndex >= slips.size()) {
			System.out.println("Invalid selection.");
			return;
		}
		BorrowSlip slip = slips.get(sIndex);

		try {
			System.out.print("Actual return date (YYYY-MM-DD, press Enter for today): ");
			String actualReturnDateInput = sc.nextLine().trim();
			String actualReturnDate = actualReturnDateInput.isEmpty() ? LocalDate.now().toString() : actualReturnDateInput;
			boolean overdue = LocalDate.parse(actualReturnDate).isAfter(LocalDate.parse(slip.getDueDate()));
			if (overdue) {
				long lateDays = ReaderValidation.calculateLateDays(slip.getBorrowDate(), actualReturnDate);
				double fine = ReaderValidation.calculateFine(lateDays);
				System.out.println("Reader " + slip.getReader().getFullName() + " must pay fine: " + fine);
			} else {
				System.out.println("Returned on time. No fine.");
			}
		} catch (Exception e) {
			System.out.println("Invalid return date: " + e.getMessage());
			return;
		}

		// update stock and remove slip
		Book book = slip.getBook();
		book.setStock(book.getStock() + 1);
		library.removeBorrowSlip(slip);
	}

	private static void searchBooks(Scanner sc, Library library) {
		System.out.print("Search by (1 - title/2 - author): ");
		String type = sc.nextLine().trim();
		System.out.print("Search query: ");
		String q = sc.nextLine().trim();

        if (type.equals("1")) {
            type = "title";
        } else if (type.equals("2")) {
            type = "author";
        } else {
            System.out.println("Invalid search type.");
            return;
        }

		ArrayList<Book> results = library.searchBooks(q, type);
		if (results.isEmpty()) {
			System.out.println("No results.");
			return;
		}
		for (Book b : results) b.displayInfo();
	}

	private static void listOverdueSlips(Scanner sc, Library library) {
		if (library.getBorrowSlips().isEmpty()) {
			System.out.println("No active borrow slips.");
			return;
		}

		System.out.print("Check overdue against date (YYYY-MM-DD): ");
		String currentDate = sc.nextLine().trim();
		try {
			ArrayList<BorrowSlip> overdueSlips = library.getOverdueBorrowSlips(currentDate);
			if (overdueSlips.isEmpty()) {
				System.out.println("No overdue slips.");
				return;
			}
			for (BorrowSlip slip : overdueSlips) {
				slip.displayInfo();
				long lateDays = ReaderValidation.calculateLateDays(slip.getBorrowDate(), currentDate);
				double fine = ReaderValidation.calculateFine(lateDays);
				System.out.println("Late days: " + lateDays + " | Fine: " + fine);
			}
		} catch (Exception e) {
			System.out.println("Invalid date: " + e.getMessage());
		}
	}

	private static void showStats(Library library) {
		System.out.println("Total books: " + library.getTotalBooks());
		System.out.println("Total readers: " + library.getTotalReaders());
		System.out.println("Total borrow slips: " + library.getTotalBorrowSlips());

		BorrowSlip topBookSlip = library.getMostBorrowedBookSlip();
		if (topBookSlip != null) {
			System.out.println("Most borrowed book: " + topBookSlip.getBook().getTitle());
		} else {
			System.out.println("Most borrowed book: N/A");
		}

		Reader topReader = library.getMostBorrowedReader();
		if (topReader != null) {
			System.out.println("Most active reader: " + topReader.getFullName());
		} else {
			System.out.println("Most active reader: N/A");
		}
	}
}
