/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package atms;

/**
 *
 * @author 6205857
 */

import junit.framework.TestCase;
import org.junit.Test;
// Import statements...

public class UserTest extends TestCase {

    // Existing setup and teardown methods...

    /**
     * Test selecting an account.
     */
    @Test
    public void testSelectAccount() {
        // Create a user
        User user = new User(123456, 1234);

        // Create two accounts
        Account checkingAccount = new ChequingAccount(1, 1000.0);
        Account savingsAccount = new SavingsAccount(2, 2000.0);

        // Add the accounts to the user
        user.addAccount(checkingAccount);
        user.addAccount(savingsAccount);

        // Select the savings account with ID 2
        user.selectAccount(2);

        // Check if the selected account is the savings account
        assertEquals(savingsAccount, user.getCurrentAccount());
    }
}
