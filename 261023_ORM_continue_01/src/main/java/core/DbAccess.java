package core;

import lombok.extern.java.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

@Log
public class DbAccess {
    private static DbAccess instance = null;

    private Connection connection;

    private DbAccess(){
        try {
            connect();
        }catch(SQLException sqex){
            log.severe("Connection to DB failed");
            sqex.printStackTrace();
        }
    }

    public static DbAccess getInstance(){
        if(instance == null) {
            instance = new DbAccess();
        }
        return instance;
    }

    private void connect() throws SQLException {
        connection = DriverManager.getConnection(
                PropertyLoader.getProperty("DB_URL") + PropertyLoader.getProperty("DB_NAME"),
                PropertyLoader.getProperty("DB_USER"),
                PropertyLoader.getProperty("DB_PASS")
        );

    }

    public Statement getStatement() throws SQLException {
        return connection.createStatement();
    }

    static void main() {
        DbAccess dbAccess = new DbAccess();
        try {
            dbAccess.connect();
        } catch (Exception e) {
            log.severe(e.getMessage());
        }
    }
}
