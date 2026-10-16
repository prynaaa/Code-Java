import core.PropertyLoader;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PropertyLoaderTest {
    @Test
    @DisplayName("Test the DB_URL in property loader")
    public void test_dbURL() {
        PropertyLoader propertyLoader = new PropertyLoader();
        String actual = propertyLoader.getProperty("DB_URL");
        String expected = "jdbc:postgres://localhost:5432";

        assertEquals(expected, actual, "DB_URL are not equals");
    }

    @Test
    @DisplayName("Test the DB_USER in property loader")
    public void test_dbUSER() {
        PropertyLoader propertyLoader = new PropertyLoader();
        String actual = propertyLoader.getProperty("DB_USER");
        String expected = "postgres";

        assertEquals(expected, actual, "DB_USER's are not equals");
    }

    @Test
    @DisplayName("Test the DB_PASSWORD in property loader")
    public void test_dbPSW() {
        PropertyLoader propertyLoader = new PropertyLoader();
        String actual = propertyLoader.getProperty("DB_PASSWORD");
        String expected = "postgres";

        assertEquals(expected, actual, "DB_PASSWORD's are not equals");
    }
}
