package C5_D1_Assignment.src.model;

import C5_D1_Assignment.src.service.Returnable;

public class BorrowSlip implements Returnable {
    private String slipId;
    private String readerId;
    private String bookId;
    private String dueDate;
    private String returnDate;

    public BorrowSlip(String slipId, String readerId, String bookId, String dueDate) {
        this.slipId = slipId;
        this.readerId = readerId;
        this.bookId = bookId;
        this.dueDate = dueDate;
    }

    @Override
    public void confirmReturn(String date) {
        this.returnDate = date;
        System.out.println("Slip " + slipId + " confirmed return on " + date);
    }

    @Override
    public String getReturnDate() {
        return returnDate;
    }

    @Override
    public boolean isReturned() {
        return returnDate != null;
    }

}