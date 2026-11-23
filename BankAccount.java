
import java.util.ArrayList;

public class BankAccount {

    private String accountNumber;
    private String accountHolderName;
    private double balance;

    private ArrayList<String> transactions = new ArrayList<>();

    // Constructor
    public BankAccount(String accountNumber, String accountHolderName) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.balance = 0.0;

        transactions.add("Account created with balance Rs. 0.00");
    }

    // Deposit money
    public boolean deposit(double amount) {

        if (!Double.isFinite(amount) || amount <= 0) {
            return false;
        }

        balance += amount;

        transactions.add(String.format(
                "Deposited: Rs. %.2f | Balance: Rs. %.2f",
                amount, balance));

        return true;
    }

    // Withdraw money
    public boolean withdraw(double amount) {

        if (!Double.isFinite(amount) || amount <= 0 || amount > balance) {
            return false;
        }

        balance -= amount;

        transactions.add(String.format(
                "Withdrawn: Rs. %.2f | Balance: Rs. %.2f",
                amount, balance));

        return true;
    }

    // Display account details
    public void displayAccountDetails() {

        System.out.println("\nAccount Number: " + accountNumber);
        System.out.println("Account Holder: " + accountHolderName);

        System.out.printf("Current Balance: Rs. %.2f%n", balance);
    }

    // Display transaction history
    public void displayTransactionHistory() {

        System.out.println("\nTransaction History");
        System.out.println("----------------------------");

        for (String transaction : transactions) {
            System.out.println(transaction);
        }
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public double getBalance() {
        return balance;
    }
}
