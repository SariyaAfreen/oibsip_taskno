package ATMSystem;

import java.util.ArrayList;
import java.util.List;

public class Account {
    private String accountId;
    private String pin;
    private double balance;
    private List<Transaction> transactions;

    public Account(String accountId, String pin, double initialBalance) {
        this.accountId = accountId;
        this.pin = pin;
        this.balance = initialBalance;
        this.transactions = new ArrayList<>();
    }

    public boolean authenticate(String pin) {
        return this.pin.equals(pin);
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
        transactions.add(new Transaction("Deposit", amount));
    }

    public boolean withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            transactions.add(new Transaction("Withdraw", amount));
            return true;
        } else {
            return false;
        }
    }

    public boolean transfer(Account targetAccount, double amount) {
        if (withdraw(amount)) {
            targetAccount.deposit(amount);
            transactions.add(new Transaction("Transfer", amount));
            return true;
        } else {
            return false;
        }
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }
}
