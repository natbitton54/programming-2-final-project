package atms;

/**
 *
 * @author 6205857
 */

/**
 * The `SavingsAccount` class represents a specific type of account, namely a Savings Account. It extends the
 * `Account` class, inheriting its properties and behaviors. This class introduces specific logic for handling
 * withdrawals in the context of a savings account. The `withdraw` method is overridden to enforce rules related
 * to withdrawal amounts, ensuring they are positive and do not exceed the available balance.
 *
 * 
 * - Represent a Savings Account with an account ID and initial balance.
 * - Provide specific logic for handling withdrawals, ensuring they meet specified conditions.
 * - Override the `withdraw` method inherited from the `Account` superclass to enforce savings account rules.
 **/

// Represents a Savings Account, which is a type of Account
public class SavingsAccount extends Account {

    // Constructor to initialize the SavingsAccount with an account ID and initial balance
    public SavingsAccount(int accountId, double balance) {
        // Call the constructor of the superclass (Account) with provided values
        super(accountId, balance);
    }

    // Override the withdraw method to handle specific logic for SavingsAccount
    @Override
    public void withdraw(double amount) {
        // Check if the withdrawal amount is positive and doesn't exceed the current balance
        if (amount > 0 && getBalance() >= amount) {
            // If conditions are met, deduct the amount from the balance
            setBalance(getBalance() - amount);
        } else {
            // If conditions are not met, throw an exception with an error message
            throw new IllegalArgumentException("Invalid withdrawal amount.");
        }
    }
}
