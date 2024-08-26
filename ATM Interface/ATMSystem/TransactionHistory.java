package ATMSystem;

public class TransactionHistory {

    public static void printHistory(Account account) {
        System.out.println("Transaction History:");
        for (Transaction transaction : account.getTransactions()) {
            System.out.println(transaction);
        }
    }
}
