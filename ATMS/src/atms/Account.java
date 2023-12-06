package atms;

/**
 *
 * @author 6205857
 */


/**
 * The `Account` abstract class represents an c account in the ATM system.
 * It serves as the base class for specific types of accounts (e.g., ChequingAccount, SavingsAccount).
 * The class defines common fields and methods for all account types
 *
 * - Abstract class: Cannot be instantiated directly; serves as a blueprint for concrete account types.
 * - Fields: Stores account information, including an account ID and balance.
 * - Constructor: Initializes an account with a unique ID and an initial balance.
 * - Getter methods: Provides access to the account ID and balance.
 * - Setter method: Updates the account balance with validation to ensure it's not set to a negative value.
 * - Abstract method: Represents the withdrawal operation, to be implemented by subclasses.
 **/

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
