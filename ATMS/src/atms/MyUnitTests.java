package atms;

/**
 *
 * @author natha
 */

/*
 * Unit tests for the ATMS application.
 */
public class MyUnitTests {

    // Test for the withdrawal of a valid amount from a ChequingAccount
    public void testWithdrawValidAmount() {  // method1
        // Arrange
        ChequingAccount account = new ChequingAccount(1, 1000.0);

        // Act
        account.withdraw(500.0);

        // Assert
        if (account.getBalance() == 500.0) {
            System.out.println("Test passed: Withdrawal of a valid amount succeeded.");
        } else {
            throw new AssertionError("Test failed: Withdrawal of a valid amount did not succeed.");
        }
    }

    // Test for the selection of an account by a user
    public void testSelectAccount() { // method2
        // Arrange
        User user = new User(123456, 1234);
        Account checkingAccount = new ChequingAccount(1, 1000.0);
        Account savingsAccount = new SavingsAccount(2, 2000.0);
        user.addAccount(checkingAccount);
        user.addAccount(savingsAccount);

        // Act
        user.selectAccount(2);

        // Assert
        if (user.getCurrentAccount().getAccountId() == 2) {
            System.out.println("Test passed: Account selection succeeded.");
        } else {
            throw new AssertionError("Test failed: Account selection did not succeed.");
        }
    }
}
