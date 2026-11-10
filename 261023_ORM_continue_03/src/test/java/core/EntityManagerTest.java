package core;

import annotations.Column;
import annotations.Entity;
import annotations.Id;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pojo.Pizza;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EntityManagerTest {

    @Entity
    static class Customer {
        @Id
        @Column(name = "the_id")
        Long id;
        @Column(name = "full_name")
        String name;
        int age;
        boolean active;
    }

    static class NotAnEntity {
        Long id;
    }

    @Test
    @DisplayName("CREATE TABLE for Pizza")
    void test_createTablePizza() {
        assertEquals(
                "CREATE TABLE pizza (id BIGSERIAL PRIMARY KEY, name VARCHAR(255))",
                EntityManager.buildCreateTableSql(Pizza.class));
    }

    @Test
    @DisplayName("@Column renames the database column")
    void test_columnAnnotation() {
        assertEquals(
                "CREATE TABLE customer (the_id BIGSERIAL PRIMARY KEY, full_name VARCHAR(255), "
                        + "age INTEGER, active BOOLEAN)",
                EntityManager.buildCreateTableSql(Customer.class));
    }

    @Test
    @DisplayName("DROP TABLE statement")
    void test_dropTable() {
        assertEquals("DROP TABLE IF EXISTS pizza", EntityManager.buildDropTableSql("pizza"));
    }

    @Test
    @DisplayName("Classes without @Entity are rejected")
    void test_notAnEntity() {
        assertThrows(IllegalArgumentException.class,
                () -> EntityManager.buildCreateTableSql(NotAnEntity.class));
    }

    @Test
    @DisplayName("EntityClassFinder finds Pizza")
    void test_finder() throws Exception {
        assertTrue(EntityClassFinder.findAll().contains(Pizza.class));
    }
}
