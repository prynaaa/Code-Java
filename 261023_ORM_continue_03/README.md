# Pizza ORM

A tiny reflection-based ORM that creates database tables from annotated classes.

## Annotations
| Annotation | Target | Purpose |
|---|---|---|
| `@Entity` | class | Marks a class as persistent (one table per entity) |
| `@Id` | field | Primary key. `Long`/`Integer` ids become `BIGSERIAL`/`SERIAL` |
| `@Column(name = "...")` | field | Sets the database column name (default: lower-cased field name) |

```java
@Entity
public class Pizza {
    @Id
    @Column(name = "the_id")
    public Long id;
    public String name;
}
```

## EntityManager
`EntityManager.getInstance()` scans `src/main/java` for `@Entity` classes, then for each one
drops the table (`DROP TABLE IF EXISTS`) and re-creates it (`CREATE TABLE ...`).

Supported types: `String`, `Long`, `Integer`, `Short`, `Double`, `Float`, `Boolean`,
`BigDecimal`, `LocalDate`, `LocalDateTime`.

## Configuration
Edit `src/main/resources/persistent.properties` (`DB_URL`, `DB_NAME`, `DB_USER`, `DB_PASS`).

## Run
```
mvn test                                   # unit tests (no database needed)
mvn compile exec:java -Dexec.mainClass=core.EntityManager   # needs the PostgreSQL DB
```

## Done
- [x] finish the implementation of create table
- [x] add the annotation `@Column` to set a new database column name
