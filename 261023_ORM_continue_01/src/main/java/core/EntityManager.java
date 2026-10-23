package core;

import lombok.extern.java.Log;

import javax.crypto.EncryptedPrivateKeyInfo;
import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@Log
public class EntityManager {
    private static EntityManager instance = null;

    public static EntityManager getInstance() throws IOException{
        if(instance == null) {
            instance = new EntityManager();
        }
        return instance;
    }

    private EntityManager() throws IOException{
        // drop table pizza
        // create a new table (called pizza)
        // -> your turn: do this and drive to spar

        List<String> entityClasses = EntityClassFinder.findAll();

    }

    private void dropTable(String name){

        String sql = String.format(
                "DROP table IF EXISTS %s", name.toLowerCase()
        );
        log.info(sql);
        try {
            Statement statement = DbAccess.getInstance().getStatement();
            statement.execute(sql);
            statement.close();

        } catch (SQLException e) {
            log.severe("Failed to get Statement");
            e.printStackTrace();
        }
    }

    private void createTable(Class clazz){
        /*
        String sql = String.format(
                "CREATE TABLE %s()", name.toLowerCase()
        );
        log.info(sql);

         */
        StringBuilder sql = new StringBuilder();
        sql.append("CREATE TABLE ");
        sql.append(clazz.getSimpleName());
        sql.append(" (");

        for(Field field : clazz.getDeclaredFields()){
            sql.append(field.getName());
            sql.append(", ");
        }

        log.info(sql.toString());
    }

    static void main() {
        //EntityManager.getInstance().dropTable("Pizza");

        // find all pojos ...
        // find . -name "*.java"
        try {
            List<String> tables = EntityClassFinder.findAll();
            for(String table : tables){
                EntityManager.getInstance().dropTable(table);
            }

            for(String table : tables){
                EntityManager.getInstance().createTable(table.getClass());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
