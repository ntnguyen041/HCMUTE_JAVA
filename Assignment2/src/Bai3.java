package Assignment2.src;
class BankAccount {
    private String accountNumber;
    private String nameAccount;
    private double balance;

    public String getNameAccount() {
        return nameAccount;
    }
    public String getAccountNumber() {
        return accountNumber;
    }
    public double getBalance() {
        return balance;
    }

    public void setNameAccount(String nameAccount) {
        if(nameAccount != null && !nameAccount.trim().isEmpty()) {
            this.nameAccount = nameAccount;
        }
    }
 
    public BankAccount(String nameAccount, String accountNumber, double initialBalance) {
        this.balance = initialBalance;
        if(this.balance < 0) {
            this.balance = 0;
            System.out.println("Initial balance cannot be negative. Setting balance to 0.");
        }
        this.nameAccount = nameAccount;
        this.accountNumber = accountNumber;
    }
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
        }else {
            System.out.println("Deposit amount must be positive.");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
        }else {
            System.out.println("Withdrawal amount exceeds balance or is invalid.");
        }
    }
    public void displayInfo() {
        String accStr = String.valueOf(accountNumber);
        String last4 = accStr.substring(accStr.length() - 4);
        System.out.println("Account Holder: " + nameAccount + " - Account Number: ****" + last4 + " - Balance: $" + balance);
    }
    public void transfer(BankAccount other, double amount) {
        if (amount > 0 && amount <= balance) {
            this.withdraw(amount);
            other.deposit(amount);
        }
    }
}
public class Bai3 {
    public static void main(String[] args) {
        BankAccount account1 = new BankAccount("CODE001", "1234567890", 1000.0);
        BankAccount account2 = new BankAccount("CODE002", "0987654321", 500.0);

        account1.displayInfo();
        account2.displayInfo();

        account1.transfer(account2, 200.0);

        System.out.println("\nAfter transfer:");
        account1.displayInfo();
        account2.displayInfo();


        // trường họp rut tiền vượt quá số dư
        account1.withdraw(2000.0);
        // trường hợp nạp tiền số âm
        account2.deposit(-100.0); 
        // Không nên có setter cho accountNumber
        // vì số tài khoản phải cố định sau khi tạo.
        // Nếu cho phép thay đổi sẽ dễ gây sai dữ liệu,
        // nhầm lẫn giao dịch và mất tính bảo mật.
    }
}
 
