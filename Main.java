
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    static Scanner sc = new Scanner(System.in);

    static Map<String, BankAccount> accounts = new HashMap<>();

    static int nextAccountNumber = 1001;

    public static void main(String[] args) {

        int choice;

        do {
            System.out.println("\n================================");
            System.out.println(" BANK ACCOUNT MANAGEMENT SYSTEM");
            System.out.println("================================");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Check Balance");
            System.out.println("5. View All Accounts");
            System.out.println("6. Transaction History");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");

            choice = readChoice();

            switch (choice) {

                case 1 -> createAccount();

                case 2 -> depositMoney();

                case 3 -> withdrawMoney();

                case 4 -> checkBalance();

                case 5 -> viewAllAccounts();

                case 6 -> transactionHistory();

                case 7 -> System.out.println("Thank you for using our banking system!");

                default -> System.out.println("Invalid choice! Please try again.");
            }

        } while (choice != 7);

        sc.close();
    }

    // Create account
    static void createAccount() {

        System.out.print("Enter account holder name: ");
        String name = sc.nextLine().trim();

        if (name.isEmpty()) {
            System.out.println("Name cannot be empty!");
            return;
        }

        String accountNumber = String.valueOf(nextAccountNumber++);

        BankAccount account = new BankAccount(accountNumber, name);

        accounts.put(accountNumber, account);

        System.out.println("\nAccount created successfully!");
        System.out.println("Your Account Number: " + accountNumber);
    }

    // Deposit money
    static void depositMoney() {

        BankAccount account = findAccount();

        if (account == null) {
            return;
        }

        double amount = readAmount("Enter deposit amount: ");

        if (account.deposit(amount)) {
            System.out.println("Money deposited successfully!");
            System.out.printf("Updated Balance: Rs. %.2f%n",
                    account.getBalance());
        } else {
            System.out.println("Invalid deposit amount!");
        }
    }

    // Withdraw money
    static void withdrawMoney() {

        BankAccount account = findAccount();

        if (account == null) {
            return;
        }

        double amount = readAmount("Enter withdrawal amount: ");

        if (account.withdraw(amount)) {
            System.out.println("Withdrawal successful!");
            System.out.printf("Remaining Balance: Rs. %.2f%n",
                    account.getBalance());
        } else {
            System.out.println("Insufficient balance or invalid amount!");
        }
    }

    // Check balance
    static void checkBalance() {

        BankAccount account = findAccount();

        if (account != null) {
            account.displayAccountDetails();
        }
    }

    // View all accounts
    static void viewAllAccounts() {

        if (accounts.isEmpty()) {
            System.out.println("No accounts available!");
            return;
        }

        System.out.println("\n========== ALL BANK ACCOUNTS ==========");

        for (BankAccount account : accounts.values()) {

            account.displayAccountDetails();
            System.out.println("---------------------------------------");
        }
    }

    // Transaction history
    static void transactionHistory() {

        BankAccount account = findAccount();

        if (account != null) {
            account.displayTransactionHistory();
        }
    }

    // Find account
    static BankAccount findAccount() {

        System.out.print("Enter account number: ");
        String accountNumber = sc.nextLine().trim();

        BankAccount account = accounts.get(accountNumber);

        if (account == null) {
            System.out.println("Account not found!");
        }

        return account;
    }

    // Read menu choice
    static int readChoice() {

        try {
            return Integer.parseInt(sc.nextLine().trim());

        } catch (NumberFormatException e) {
            return -1;
        }
    }

    // Read amount
    static double readAmount(String message) {

        while (true) {

            System.out.print(message);

            try {
                double amount = Double.parseDouble(sc.nextLine().trim());

                if (Double.isFinite(amount) && amount > 0) {
                    return amount;
                }

                System.out.println("Please enter a valid positive amount.");

            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Enter a numeric amount.");
            }
        }
    }
}
