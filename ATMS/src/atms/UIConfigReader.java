package atms;

/**
 *
 * @author 6205857
 */
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


/**
 * The `UIConfigReader` class is a utility class responsible for reading UI configuration properties from a file.
 * It loads configuration values such as background color, font size, text color, button color, and dark mode status.
 * The class provides methods to access these configuration properties and generate a base CSS style for UI components.
 *
 * - Read UI configuration properties from an external file (ui_config.properties).
 * - Provide methods to retrieve specific UI configuration values (background color, font size, text color, button color, dark mode).
 * - Generate a base CSS style string based on the loaded configuration properties.
 * - Handle default values for configuration properties if they are not found or are invalid.
 **/

// Utility class for reading UI configuration properties
public class UIConfigReader {

    // Properties object to store configuration values
    private Properties properties = new Properties();

    // Constructor to initialize UIConfigReader and load properties from a file
    public UIConfigReader() {
        // Use try-with-resources to automatically close the FileInputStream
        try (FileInputStream fis = new FileInputStream("C:/Users/natha/Downloads/ui_config.properties")) {
            // Load properties from the input stream
            properties.load(fis);
        } catch (IOException e) {
            // Print the stack trace and throw a runtime exception if an error occurs
            e.printStackTrace();
            throw new RuntimeException("Failed to load UI configuration properties.", e);
        }
    }

    // Get the background color property, with a default value if not found
    public String getBackgroundColor() {
        return properties.getProperty("background.color", "defaultBackgroundColor");
    }

    // Get the font size property as an integer, with a default value if not found or not a valid integer
    public int getFontSize() {
        return Integer.parseInt(properties.getProperty("font.size", "12"));
    }

    // Get the text color property, with a default value if not found
    public String getTextColor() {
        return properties.getProperty("text.color", "defaultTextColor");
    }

    // Get the button color property, with a default value if not found
    public String getButtonColor() {
        return properties.getProperty("button.color", "defaultButtonColor");
    }

    // Check if dark mode is enabled, with a default value if not found
    public boolean isDarkModeEnabled() {
        String darkMode = properties.getProperty("dark.mode", "false");
        return darkMode.equalsIgnoreCase("true");
    }

    // Generate the base CSS style based on configuration properties
    public String getBaseStyle() {
        // StringBuilder to build the CSS style
        StringBuilder style = new StringBuilder();
        // Append background color
        style.append("-fx-background-color: ").append(getBackgroundColor()).append(";");
        // Append font size
        style.append("-fx-font-size: ").append(getFontSize()).append("pt;");
        // Append text color
        style.append("-fx-text-fill: ").append(getTextColor()).append(";");
        // Append button color
        style.append("-fx-base: ").append(getButtonColor()).append(";");
        // Apply dark mode styles
        if (isDarkModeEnabled()) {
            style.append("-fx-background-color: #000;");
        }
        // Convert the StringBuilder to a string and return the final style
        return style.toString();
    }
}
