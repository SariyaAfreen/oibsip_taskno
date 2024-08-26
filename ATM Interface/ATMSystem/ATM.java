package ATMSystem;

import java.util.Scanner;

public class ATM {

    private static User currentUser;

    public static void main(String[] args) {
        Account userAccount = new Account("123456", "1234", 1000.00); // Example account
        currentUser = new User(userAccount);

        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the ATM");

        if (authenticateUser(scanner)) {
            boolean running = true;
            while (running) {
                System.out.println("\n1. View Transactions History");
                System.out.println("2. Withdraw");
                System.out.println("3. Deposit");
                System.out.println("4. Transfer");
                System.out.println("5. Quit");

                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        TransactionHistory.printHistory(currentUser.getAccount());
                        break;
                    case 2:
                        performWithdrawal(scanner);
                        break;
                    case 3:
                        performDeposit(scanner);
                        break;
                    case 4:
                        performTransfer(scanner);
                        break;
                    case 5:
                        running = false;
                        System.out.println("Thank you for using the ATM. Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        } else {
            System.out.println("Authentication failed. Exiting...");
        }

        scanner.close();
    }

    private static boolean authenticateUser(Scanner scanner) {
        System.out.print("Enter user ID: ");
        String userId = scanner.next(); // Assuming user ID is not used further in this example

        System.out.print("Enter PIN: ");
        String pin = scanner.next();

        return currentUser.authenticate(pin);
    }

    private static void performWithdrawal(Scanner scanner) {
        System.out.print("Enter amount to withdraw: ");
        double amount = scanner.nextDouble();
        if (currentUser.getAccount().withdraw(amount)) {
            System.out.println("Withdrawal successful. New balance: " + currentUser.getAccount().getBalance());
        } else {
            System.out.println("Insufficient funds.");
        }
    }

    private static void performDeposit(Scanner scanner) {
        System.out.print("Enter amount to deposit: ");
        double amount = scanner.nextDouble();
        currentUser.getAccount().deposit(amount);
        System.out.println("Deposit successful. New balance: " + currentUser.getAccount().getBalance());
    }

    private static void performTransfer(Scanner scanner) {
        System.out.print("Enter amount to transfer: ");
        double amount = scanner.nextDouble();
        // For simplicity, transfer to the same account; you might want to handle multiple accounts.
        if (currentUser.getAccount().transfer(currentUser.getAccount(), amount)) {
            System.out.println("Transfer successful. New balance: " + currentUser.getAccount().getBalance());
        } else {
            System.out.println("Transfer failed. Check the amount or balance.");
        }
    }
}
