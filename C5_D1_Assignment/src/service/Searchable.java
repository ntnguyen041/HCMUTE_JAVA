package C5_D1_Assignment.src.service;
import java.util.List;
import C5_D1_Assignment.src.model.Book;

public interface Searchable {
    List<Book> searchByTitle(String keyword);

    List<Book> searchByAuthor(String keyword);

    static String normalizeKeyword(String keyword) {
        if (keyword == null)
            return "";
        return keyword.trim().toLowerCase();
    }

}
