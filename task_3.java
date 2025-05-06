import java.util.Scanner;

// BankAccount class to store and manage account balance
class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        if (initialBalance >= 0)
            this.balance = initialBalance;
        else
            this.balance = 0;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0)
            balance += amount;
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            return true;
        }
        return false;
    }
}

// ATM class to represent ATM operations
class ATM {
    private BankAccount account;
    private Scanner scanner;

    public ATM(BankAccount account) {
        this.account = account;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        while (true) {
            System.out.println("\n===== ATM MENU =====");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Exit");
            System.out.print("Select an option (1-4): ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    checkBalance();
                    break;
                case 2:
                    deposit();
                    break;
                case 3:
                    withdraw();
                    break;
                case 4:
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid option. Please select again.");
            }
        }
    }

    private void checkBalance() {
        System.out.printf("💰 Current Balance: $%.2f\n", account.getBalance());
    }

    private void deposit() {
        System.out.print("Enter amount to deposit: $");
        double amount = scanner.nextDouble();
        if (amount > 0) {
            account.deposit(amount);
            System.out.println("✅ Deposit successful.");
        } else {
            System.out.println("❌ Invalid deposit amount.");
        }
    }

    private void withdraw() {
        System.out.print("Enter amount to withdraw: $");
        double amount = scanner.nextDouble();
        if (amount > 0) {
            boolean success = account.withdraw(amount);
            if (success) {
                System.out.println("✅ Withdrawal successful.");
            } else {
                System.out.println("❌ Insufficient balance or invalid amount.");
            }
        } else {
            System.out.println("❌ Invalid withdrawal amount.");
        }
    }
}

// Main class to run the ATM interface
public class ATMInterface {
    public static void main(String[] args) {
        BankAccount userAccount = new BankAccount(1000.0); // Starting with $1000
        ATM atm = new ATM(userAccount);
        atm.start();
    }
}
