package atms;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.text.Text;
import javafx.geometry.Pos;
import java.util.ArrayList;

/**
 *
 * @author 6205857
 */

/**
 * The `ATMS` class serves as the main class for the ATM application. It extends
 * the JavaFX `Application` class
 * and is responsible for initializing and managing the ATM system's user
 * interface, handling user login,
 * providing account selection, and facilitating transactions such as
 * withdrawals. The class interacts with
 * other classes such as `Atm`, `User`, `ChequingAccount`, and `SavingsAccount`
 * to simulate the functionality
 * of an ATM system.
 *
 *
 * - Initialize the ATM system with users, accounts, and configuration settings.
 * - Create and manage different scenes for login, account selection, and main
 * transaction activities.
 * - Validate user login credentials and provide feedback to the user.
 * - Handle user account selection and switch to the main transaction scene.
 * - Facilitate account transactions such as withdrawals and update the UI
 * accordingly.
 * - Display transaction receipts and account information to the user.
 * - Provide a logout mechanism to switch users or exit the application.
 *
 */

// Main class for the ATM application, extending Application from JAVAFX
public class ATMS extends Application {

    // Instance variables for the ATM, current user, transaction receipt text, and UI configuration reader
    private Atm atm;
    private User currentUser;
    private Text transactionReceiptText;
    private UIConfigReader configReader;

    // Main method to launch the application
    public static void main(String[] args) {
        launch(args);

        // Create an instance of MyUnitTests and run test methods
        MyUnitTests tester = new MyUnitTests();
        tester.testWithdrawValidAmount();
        tester.testSelectAccount();
    }

    // Override the start method from the Application class
    @Override
    public void start(Stage stage) {
        // Initialize the UI configuration reader and ATM
        configReader = new UIConfigReader();
        atm = initializeATM();

        // Set the initial scene to the login scene
        stage.setScene(createLoginScene(stage));
        stage.setTitle("ATM System");
        stage.setWidth(400);
        stage.setHeight(450);
        stage.show();

        // Customize UI based on configuration settings
        stage.getScene().getRoot().setStyle(configReader.getBaseStyle());
    }

    // Method to initialize the ATM with users and accounts
    private Atm initializeATM() {
        ArrayList<User> users = new ArrayList<>();
        User user1 = new User(123456, 1234);
        User user2 = new User(654321, 4321);

        Account chequing = new ChequingAccount(1, 1000.0);
        Account savingsAccount = new SavingsAccount(2, 2000.0);
        user1.addAccount(chequing);
        user1.addAccount(savingsAccount);
        user2.addAccount(chequing);
        user2.addAccount(savingsAccount);
        users.add(user1);
        users.add(user2);
        return new Atm(1, users);
    }

    // Method to create the login scene
    private Scene createLoginScene(Stage stage) {
        VBox loginLayout = new VBox(10);
        loginLayout.setPadding(new Insets(20));
        loginLayout.setAlignment(Pos.CENTER);

        // Label for the login scene title
        Label titleLabel = new Label("ATM Login");
        titleLabel.setStyle("-fx-font-size: 20;");

        // Check if dark mode is enabled and set text color accordingly
        if (configReader.isDarkModeEnabled()) {
            titleLabel.setStyle("-fx-font-size: 20; -fx-text-fill: #fff;");
        } else {
            titleLabel.setStyle("-fx-font-size: 20;");
        }

        // Text field for entering the account number
        TextField accountNumberField = new TextField();
        accountNumberField.setPromptText("Enter your account number");
        accountNumberField.setFocusTraversable(false);

        // Password field for entering the PIN
        PasswordField pinField = new PasswordField();
        pinField.setPromptText("Enter your PIN");
        pinField.setFocusTraversable(false);

        // Button to initiate the login process
        Button loginButton = new Button("Login");
        loginButton.setFocusTraversable(false);
        loginButton.setDefaultButton(true);

        // Event handler for the login button
        loginButton.setOnAction(e -> {
            // Variables to store entered account number and PIN
            int enteredAccountNumber;
            int enteredPin;

            try {
                // Parse entered values from text fields
                enteredAccountNumber = Integer.parseInt(accountNumberField.getText());
                enteredPin = Integer.parseInt(pinField.getText());
            } catch (NumberFormatException ex) {
                // Show an alert for invalid input
                showAlert(Alert.AlertType.ERROR, "Invalid Input", "Please enter a valid Account Number and PIN.");
                return;
            }

            // Validate login and get the logged-in user
            User loggedInUser = atm.validateLogin(enteredAccountNumber, enteredPin);

            if (loggedInUser != null) {
                // Set the current user, clear text fields, and show a login success alert
                currentUser = loggedInUser;
                accountNumberField.clear();
                pinField.clear();
                showAlert(Alert.AlertType.INFORMATION, "Login Successful", "Welcome, User: " + loggedInUser.getAccountNumber() + "!");
                stage.setScene(createAccountSelectionScene(stage));
            } else {
                // Show an alert for failed login
                showAlert(Alert.AlertType.ERROR, "Login Failed", "Invalid Account Number or PIN. Please try again.");
                accountNumberField.clear();
                pinField.clear();
            }
        });

        // Add UI components to the login layout
        loginLayout.getChildren().addAll(titleLabel, accountNumberField, pinField, loginButton);

        // Apply base style to the login layout
        loginLayout.setStyle(configReader.getBaseStyle());

        // Return the scene created with the login layout
        return new Scene(loginLayout);
    }

    // Method to create the account selection scene
    private Scene createAccountSelectionScene(Stage stage) {
        VBox selectionLayout = new VBox(10);
        selectionLayout.setPadding(new Insets(20));
        selectionLayout.setAlignment(Pos.CENTER);
        stage.setTitle("Account Selection");

        // Label for the account selection scene title
        Label selectAccountLabel = new Label("Select Account");
        selectAccountLabel.setStyle("-fx-font-size: 20;");

        // Toggle group for radio buttons to select the account type
        ToggleGroup accountSelectionGroup = new ToggleGroup();

        // Radio buttons for chequing and savings accounts
        RadioButton chequingRadioButton = new RadioButton("Chequing Account");
        chequingRadioButton.setToggleGroup(accountSelectionGroup);

        RadioButton savingsRadioButton = new RadioButton("Savings Account");
        savingsRadioButton.setToggleGroup(accountSelectionGroup);

        // Button to continue to the main scene
        Button continueButton = new Button("Continue");

        // Event handler for the continue button
        continueButton.setOnAction(e -> {
            if (chequingRadioButton.isSelected()) {
                currentUser.selectAccount(1);
            } else if (savingsRadioButton.isSelected()) {
                currentUser.selectAccount(2);
            }

            stage.setScene(createMainScene(stage, true));
        });

        // Add UI components to the account selection layout
        selectionLayout.getChildren().addAll(selectAccountLabel, chequingRadioButton, savingsRadioButton, continueButton);

        // Apply base style to the account selection layout
        selectionLayout.setStyle(configReader.getBaseStyle());

        // Return the scene created with the account selection layout
        return new Scene(selectionLayout);
    }

    // Method to create the main scene for account information and transactions
    private Scene createMainScene(Stage stage, boolean showBackButton) {
                    stage.setTitle("ATM Withdraw System");

        // Check if the user and current account are not null
        if (currentUser != null && currentUser.getCurrentAccount() != null) {
            // Create a grid pane for the main scene layout
            GridPane mainLayout = new GridPane();
            mainLayout.setPadding(new Insets(20));
            mainLayout.setHgap(10);
            mainLayout.setVgap(10);

            // Labels and buttons for account information and transactions
            Label accountInfoLabel = new Label("Account Information");
            Label balanceLabel = new Label("Balance: $" + currentUser.getCurrentAccount().getBalance());
            Button withdrawButton = new Button("Withdraw");

            // Event handler for the withdraw button
            withdrawButton.setOnAction(e -> {
                double currentBalance = currentUser.getCurrentAccount().getBalance();
                TextInputDialog dialog = new TextInputDialog();

                // Apply styles to the Withdraw Money dialog
                UIConfigReader configReader = new UIConfigReader();
                // Apply the base style to the entire scene
                dialog.getDialogPane().getScene().getRoot().setStyle(configReader.getBaseStyle());

                dialog.setTitle("Withdraw Money");
                dialog.setHeaderText("Enter the amount to withdraw:");
                dialog.setContentText("Amount:");

                String result = dialog.showAndWait().orElse(null);
                if (result != null) {
                    try {
                        double amountToWithdraw = Double.parseDouble(result);
                        // Validate the amount to withdraw
                        if (amountToWithdraw < 20) {  // Update the check for the minimum withdrawal amount
                            showAlert(Alert.AlertType.ERROR, "Invalid Amount", "Withdrawal amount must be at least $20.");
                        } else if (amountToWithdraw > currentBalance) {
                            showAlert(Alert.AlertType.ERROR, "Insufficient Funds", "You do not have enough funds in your account.");
                        } else {
                            // Perform withdrawal, generate receipt, and update UI
                            atm.withdraw(amountToWithdraw, currentUser);
                            String transactionReceipt = generateTransactionReceipt(currentUser, amountToWithdraw);
                            updateTransactionReceipt(transactionReceipt);
                            showAlert(Alert.AlertType.INFORMATION, "Withdrawal Successful", "You have withdrawn $" + amountToWithdraw);
                            balanceLabel.setText("Balance: $" + currentUser.getCurrentAccount().getBalance());
                        }
                    } catch (NumberFormatException ex) {
                        showAlert(Alert.AlertType.ERROR, "Invalid Amount", "Please enter a valid numeric amount.");
                    }
                }
            });

            // Add UI components to the main layout
            mainLayout.add(accountInfoLabel, 0, 0);
            mainLayout.add(balanceLabel, 0, 1);
            mainLayout.add(withdrawButton, 0, 2);

            // Add back button if needed
            if (showBackButton) {
                Button backButton = new Button("Back to Select Account");
                backButton.setOnAction(e -> stage.setScene(createAccountSelectionScene(stage)));
                mainLayout.add(backButton, 0, 3);
            }

            // Logout button
            Button logoutButton = new Button("Logout");
            logoutButton.setOnAction(e -> {
                stage.setScene(createLoginScene(stage));
                currentUser = null;
                stage.setTitle("ATM System");

            });

            // Add logout button to the layout
            mainLayout.add(logoutButton, 0, 4);

            // Create the transaction receipt Text element
            transactionReceiptText = new Text("Transaction Receipt:\n");
            transactionReceiptText.setVisible(true);
            mainLayout.add(transactionReceiptText, 0, 5);

            // Apply base style to the main layout
            mainLayout.setStyle(configReader.getBaseStyle());

            // Return the scene created with the main layout
            return new Scene(mainLayout);
        } else {
            // Show an error if no account is selected
            showAlert(Alert.AlertType.ERROR, "No Account Selected", "Please select an account.");
            stage.setScene(createAccountSelectionScene(stage));
            return createAccountSelectionScene(stage);
        }
    }

    // Method to generate a transaction receipt
    private String generateTransactionReceipt(User user, double amount) {
        String receipt = "Transaction Summary:\n";
        receipt += "Amount Withdrawn: $" + amount + "\n";

        // Get account information from the ATM
        Account accountInfo = atm.getAccountInfo(user);

        // Check if account information is available
        if (accountInfo != null) {
            receipt += "Remaining Balance: $" + accountInfo.getBalance() + "\n";
            receipt += "Account ID: " + accountInfo.getAccountId() + "\n";
        } else {
            receipt += "Unable to retrieve account information.\n";
        }

        receipt += "ATM ID: " + atm.getAtmId() + "\n";

        // Users at this ATM
        receipt += "Current User at this ATM:\n";
        receipt += "Account Number: " + user.getAccountNumber() + "\n";

        // Current User who used the 'withdraw' function
        receipt += "Users who used the 'withdraw' function:\n";
        receipt += "Account Number: " + user.getAccountNumber();

        return receipt;
    }

    // Method to update the transaction receipt in the UI
    private void updateTransactionReceipt(String receipt) {
        transactionReceiptText.setText(receipt);

        // Get base text color from config reader
        UIConfigReader configReader = new UIConfigReader();
        String textColor = configReader.getTextColor();

        // Set text color to white if in dark mode
        if (configReader.isDarkModeEnabled()) {
            textColor = "#fff"; // White color in hex
        }

        // Apply text color to the transaction receipt
        transactionReceiptText.setStyle("-fx-fill: " + textColor);

        transactionReceiptText.setVisible(true);
    }

    // Method to show an alert dialog
    private void showAlert(Alert.AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);

        // Get base style from config reader
        UIConfigReader configReader = new UIConfigReader();
        String baseStyle = configReader.getBaseStyle();

        // Apply base style to the alert dialog
        alert.getDialogPane().setStyle(baseStyle);

        alert.showAndWait();
    }
}
