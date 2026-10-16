package core;

import lombok.extern.java.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

@Log
public enum DbAccess {
    INSTANCE;
    /*
    TO-DO:
    1. Singleton
    2. connect to DB setting from properties
     */

    private Connection connection;
    private final String DB_USER;
    private final String DB_URL;
    private final String DB_PASSWORD;
    private final String DB_NAME;

    DbAccess() {
        PropertyLoader propertyLoader = new PropertyLoader();
        DB_USER = propertyLoader.getProperty("DB_USER");
        DB_URL = propertyLoader.getProperty("DB_URL");
        DB_PASSWORD = propertyLoader.getProperty("DB_PASSWORD");
        DB_NAME = propertyLoader.getProperty("DB_NAME");
    }

    public Statement getStatement(){
        try(Statement statement = connection.createStatement()) {
            log.info("Created Statement successfully");
            return statement;
        } catch (SQLException sqlx) {
            log.warning("Could not create Statement");
        }
        return null;
    }

    static void main() {

    }
}
