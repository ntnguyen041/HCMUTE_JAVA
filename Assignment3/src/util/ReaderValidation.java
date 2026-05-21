package Assignment3.src.util;
import java.time.temporal.ChronoUnit;
public class ReaderValidation {
    private static final double FINE_PER_DAY = 5000;
    public static long calculateLateDays(String borrowDate, String returnDate) {
        try {
            java.time.LocalDate borrow = java.time.LocalDate.parse(borrowDate);
            java.time.LocalDate returnD = java.time.LocalDate.parse(returnDate);
            long daysLate = ChronoUnit.DAYS.between(borrow, returnD) - 14;
            return Math.max(0, daysLate);
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid date format. Please use YYYY-MM-DD.");
        }   
    }

    public static double calculateFine(long lateDays) {
        return lateDays * FINE_PER_DAY;
    }

}
