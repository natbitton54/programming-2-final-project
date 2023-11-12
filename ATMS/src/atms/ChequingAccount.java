package atms;

/**
 *
 * @author 6205857
 */
// Represents a Chequing Account, which is a type of Account
public class ChequingAccount extends Account {

    // Constructor to initialize the ChequingAccount with an account ID and initial balance
    public ChequingAccount(int accountId, double balance) {
        // Call the constructor of the superclass (Account) with provided values
        super(accountId, balance);
    }

    // Override the withdraw method 
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
