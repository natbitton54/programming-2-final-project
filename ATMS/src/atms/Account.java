package atms;

/**
 *
 * @author 6205857
 */

// Abstract class representing a generic account
public abstract class Account {

    // Fields to store account information
    private int accountId;
    private double balance;

    // Constructor to initialize account with an ID and balance
    public Account(int accountId, double balance) {
        this.accountId = accountId;
        this.balance = balance;
    }

    // Getter method to retrieve the account ID
    public int getAccountId() {
        return accountId;
    }

    // Getter method to retrieve the account balance
    public double getBalance() {
        return balance;
    }

    // Setter method to update the account balance, with validation
    public void setBalance(double balance) {
        // Ensure balance is not set to a negative value
        if (balance < 0) {
            throw new IllegalArgumentException("Balance cannot be set to a negative value.");
        }
        this.balance = balance;
    }

    // Abstract method representing the withdrawal operation, to be implemented by subclasses
    public abstract void withdraw(double amount);
}
