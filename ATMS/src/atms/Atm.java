package atms;

import java.util.*;

/**
 *
 * @author 6205857
 */

 /**
 * The `Atm` class represents an Automated Teller Machine (ATM) in the banking system. It manages user interactions,
 * account transactions, and maintains a list of users. The class provides methods for user login, account selection,
 * withdrawals, and retrieval of account information. Additionally, it keeps track of users who have performed withdrawals.
 * 
 * - Store ATM information such as ID, current user, and lists of all users and users with withdrawals.
 * - Add, retrieve, and update information about users and their interactions with the ATM.
 * - Validate user login based on entered account number and PIN.
 * - Select an account for the current user.
 * - Process withdrawals, update user withdrawal status, and maintain a list of users who have made withdrawals.
 * - Provide methods to retrieve account information for a specified user.
 **/

public class Atm {

    // Fields to store ATM information
    private int atmId;
    private User currentUser;
    private ArrayList<User> users;
    private ArrayList<User> usersWithWithdrawal;

    // Constructor to initialize the ATM with an ID and a list of users
    public Atm(int atmId, ArrayList<User> users) {
        this.atmId = atmId;
        this.users = users;
        this.usersWithWithdrawal = new ArrayList<User>();
    }

    // Getter method to retrieve the ATM ID
    public int getAtmId() {
        return atmId;
    }

    // Getter method to retrieve the current user interacting with the ATM
    public User getCurrentUser() {
        return currentUser;
    }

    // Setter method to update the current user interacting with the ATM
    public void setCurrentUser(User user) {
        this.currentUser = user;
    }

    // Method to add a new user to the list of users
    public void addUser(User user) {
        users.add(user);
    }

    // Method to select an account for the current user
    public void selectAccount(int accountId) {
        if (currentUser != null) {
            currentUser.selectAccount(accountId);
        }
    }

    // Method to process a withdrawal for a specified amount and user
    public void withdraw(double amount, User user) {
        if (user != null && user.getCurrentAccount() != null) {
            user.withdraw(amount);
            user.setHasWithdrawn(true);
            usersWithWithdrawal.add(user);
        }
    }

    // Method to get account information for the specified user
    public Account getAccountInfo(User user) {
        if (user != null && user.getCurrentAccount() != null) {
            return user.getCurrentAccount();
        }
        return null;
    }

    // Method to validate user login based on entered account number and PIN
    public User validateLogin(int enteredAccountNumber, int enteredPin) {
        for (User user : users) {
            if (user.getAccountNumber() == enteredAccountNumber && user.getPin() == enteredPin) {
                return user;
            }
        }
        return null; // Return null if login validation fails
    }

    // Method to get a list of users who have made withdrawals
    public ArrayList<User> getUsersWithWithdrawal() {
        for (User user : users) {
            if (user.hasWithdrawn()) {
                usersWithWithdrawal.add(user);
            }
        }
        return usersWithWithdrawal;
    }
}
