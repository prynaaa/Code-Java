package core;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class PropertyLoaderTest {

    @Test
    @DisplayName("Test the DB_URL in property loader")
    void test_dbUrl() {
        assertEquals("jdbc:postgresql://localhost:5432/", PropertyLoader.getProperty("DB_URL"),
                "DB_URL are not equal");
    }

    @Test
    @DisplayName("Test the DB_USER in property loader")
    void test_dbUser() {
        assertEquals("orm_postgres", PropertyLoader.getProperty("DB_USER"), "DB_USER are not equal");
    }

    @Test
    @DisplayName("Test the DB_PASS in property loader")
    void test_dbPass() {
        assertEquals("orm_postgres", PropertyLoader.getProperty("DB_PASS"), "DB_PASS are not equal");
    }

    @Test
    @DisplayName("Test the DB_NAME in property loader")
    void test_dbName() {
        assertEquals("pos4a_pizza", PropertyLoader.getProperty("DB_NAME"), "DB_NAME are not equal");
    }

    @Test
    @DisplayName("Unknown property returns null")
    void test_unknownProperty() {
        assertNull(PropertyLoader.getProperty("DOES_NOT_EXIST"));
    }
}
