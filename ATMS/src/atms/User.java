package atms;

import java.util.ArrayList;

/**
 *
 * @author 6205857
 */
public class User {

    // User attributes
    private int accountNumber;
    private int pin;
    private Account currentAccount;
    private ArrayList<Account> accounts;
    private boolean hasWithdrawn; // Track if the user has used the 'withdraw' function
    private static ArrayList<User> users = new ArrayList<>(); // Static list to maintain all users

    // Constructor to initialize a new user with an account number and PIN
    public User(int accountNumber, int pin) {
        this.accountNumber = accountNumber;
        this.pin = pin;
        this.accounts = new ArrayList<>();
        hasWithdrawn = false;
        users.add(this); // Add the user to the list
    }

    // Getter for the user's PIN
    public int getPin() {
        return pin;
    }

    // Add an account to the user's list of accounts
    public void addAccount(Account account) {
        accounts.add(account);
    }

    // Getter for the user's currently selected account
    public Account getCurrentAccount() {
        return currentAccount;
    }

    // Getter for the user's account number
    public int getAccountNumber() {
        return accountNumber;
    }

    // Select an account based on its ID
    public void selectAccount(int accountId) {
        for (Account account : accounts) {
            if (account.getAccountId() == accountId) {
                currentAccount = account;
                return;
            }
        }
        // Throw an exception if the account is not found
        throw new IllegalArgumentException("Account not found.");
    }

    // Withdraw money from the user's currently selected account
    public void withdraw(double amount) {
        // Check if the amount is greater than or equal to the minimum allowed value (20)
        if (amount >= 20) {
            // Proceed with the withdrawal
            if (currentAccount != null) {
                currentAccount.withdraw(amount);
                // Update the 'hasWithdrawn' attribute
                hasWithdrawn = true;
            }
        } else {
            // Throw an exception if the amount is below the minimum allowed value
            throw new IllegalArgumentException("Withdrawal amount must be at least $20.");
        }
    }

    // Check if the user has withdrawn money
    public boolean hasWithdrawn() {
        return hasWithdrawn;
    }

    // Static method to get the list of all users
    public static ArrayList<User> getUsers() {
        return users;
    }

    // Setter for the 'hasWithdrawn' attribute
    public void setHasWithdrawn(boolean hasWithdrawn) {
        this.hasWithdrawn = hasWithdrawn;
    }

}
