package ATMSystem;

public class User {
    private Account account;

    public User(Account account) {
        this.account = account;
    }

    public boolean authenticate(String pin) {
        return account.authenticate(pin);
    }

    public Account getAccount() {
        return account;
    }
}
