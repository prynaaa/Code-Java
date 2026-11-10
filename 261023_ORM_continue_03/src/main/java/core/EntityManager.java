package core;

import annotations.Column;
import annotations.Entity;
import annotations.Id;
import lombok.extern.java.Log;
import pojo.Pizza;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Log
public class EntityManager {
    private static EntityManager instance = null;

    public static EntityManager getInstance() throws IOException {
        if (instance == null) {
            instance = new EntityManager();
        }
        return instance;
    }

    /** Drops and re-creates the table of every @Entity class. */
    private EntityManager() throws IOException {
        for (Class<?> entityClass : EntityClassFinder.findAll()) {
            dropTable(tableName(entityClass));
            createTable(entityClass);
        }
    }

    private void dropTable(String name) {
        execute(buildDropTableSql(name));
    }

    private void createTable(Class<?> clazz) {
        execute(buildCreateTableSql(clazz));
    }

    private void execute(String sql) {
        log.info(sql);
        try (Statement statement = DbAccess.getInstance().getStatement()) {
            statement.execute(sql);
        } catch (SQLException e) {
            log.severe("Failed to execute: " + sql + " (" + e.getMessage() + ")");
        }
    }

    // ---- SQL generation (no DB needed, so it can be unit tested) ----

    static String buildDropTableSql(String tableName) {
        return String.format("DROP TABLE IF EXISTS %s", tableName);
    }

    static String buildCreateTableSql(Class<?> clazz) {
        if (!clazz.isAnnotationPresent(Entity.class)) {
            throw new IllegalArgumentException(clazz.getName() + " is not annotated with @Entity");
        }

        List<String> columns = new ArrayList<>();
        for (Field field : clazz.getDeclaredFields()) {
            if (Modifier.isStatic(field.getModifiers()) || field.isSynthetic()) {
                continue;
            }
            columns.add(columnName(field) + " " + columnDefinition(field));
        }

        if (columns.isEmpty()) {
            throw new IllegalArgumentException(clazz.getName() + " has no persistent fields");
        }

        return "CREATE TABLE " + tableName(clazz) + " (" + String.join(", ", columns) + ")";
    }

    static String tableName(Class<?> clazz) {
        return clazz.getSimpleName().toLowerCase();
    }

    /** @Column(name = ...) if present, otherwise the lower-cased field name. */
    static String columnName(Field field) {
        Column column = field.getAnnotation(Column.class);
        if (column != null && !column.name().isBlank()) {
            return column.name();
        }
        return field.getName().toLowerCase();
    }

    private static String columnDefinition(Field field) {
        Class<?> type = field.getType();
        boolean isId = field.isAnnotationPresent(Id.class);

        if (isId) {
            // auto-generated primary key for numeric ids
            if (type == Long.class || type == long.class) return "BIGSERIAL PRIMARY KEY";
            if (type == Integer.class || type == int.class) return "SERIAL PRIMARY KEY";
            return sqlType(type) + " PRIMARY KEY";
        }
        return sqlType(type);
    }

    private static String sqlType(Class<?> type) {
        if (type == String.class) return "VARCHAR(255)";
        if (type == Long.class || type == long.class) return "BIGINT";
        if (type == Integer.class || type == int.class) return "INTEGER";
        if (type == Short.class || type == short.class) return "SMALLINT";
        if (type == Double.class || type == double.class) return "DOUBLE PRECISION";
        if (type == Float.class || type == float.class) return "REAL";
        if (type == Boolean.class || type == boolean.class) return "BOOLEAN";
        if (type == BigDecimal.class) return "NUMERIC";
        if (type == LocalDate.class) return "DATE";
        if (type == LocalDateTime.class) return "TIMESTAMP";
        throw new IllegalArgumentException("Unsupported field type: " + type.getName());
    }
    public static String insertIntoSQL(Class<?> clazz, Object entity) {
        if (!clazz.isAnnotationPresent(Entity.class)) {
            throw new IllegalArgumentException(clazz.getName() + " is not annotated with @Entity");
        }

        List<String> columns = new ArrayList<>();
        List<String> values = new ArrayList<>();

        for (Field field : clazz.getDeclaredFields()) {
            if (Modifier.isStatic(field.getModifiers()) || field.isSynthetic()) {
                continue;
            }

            field.setAccessible(true);
            columns.add(columnName(field));

            try {
                Object value = field.get(entity);
                if(value != null) {
                    values.add("'" + value.toString().replace("'", "''") + "'");
                }
            } catch (Exception e) {
                throw new RuntimeException("Failed to read field: " + field.getName(), e);
            }
        }

        if (columns.isEmpty()) {
            throw new IllegalArgumentException(clazz.getName() + " has no persistent fields");
        }

        log.info("Insert into " + entity.toString());
        return "INSERT INTO " + tableName(clazz)
                + " (" + String.join(", ", columns) + ")"
                + " VALUES (" + String.join(", ", values) + ")";
    }

    public void showTable(Class<?> clazz) {
        execute("SELECT * FROM " + clazz.getSimpleName());
    }

    public void persist(Object entity){
        dropTable(tableName(entity.getClass()));
        createTable(entity.getClass());
        execute(insertIntoSQL(entity.getClass(), entity));
    }

    public static void main(String[] args) {
        try {
            EntityManager  e = EntityManager.getInstance();
            e.execute("SELECT * FROM pizza");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
