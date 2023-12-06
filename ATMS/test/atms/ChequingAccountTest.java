package atms;

/**
 *
 * @author 6205857
 */

import junit.framework.TestCase;
import org.junit.Test;

public class ChequingAccountTest extends TestCase {

    @Test
    public void testWithdrawValidAmount() {
        // Arrange
        ChequingAccount account = new ChequingAccount(1, 1000.0);

        // Act
        account.withdraw(500.0);

        // Assert
        assertEquals(500.0, account.getBalance());
    }
}
