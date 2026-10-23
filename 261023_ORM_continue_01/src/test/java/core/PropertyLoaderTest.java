package core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PropertyLoaderTest {

    // your turn: check the properties
    // 1. pom.xml
    @Test
    @DisplayName("Test the DB_URL in property loader")
    public void test_dbUrl(){
        PropertyLoader propertyLoader = new PropertyLoader();
        String actual = propertyLoader.getProperty("DB_URL");
        String expected = "jdbc:postgres://localhost:5432";

        assertEquals(expected, actual, "DB_URL are not equal");
    }

    // your turn: do all other properties tests

}