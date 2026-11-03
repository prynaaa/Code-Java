package pojo;

import annotations.Entity;
import annotations.Id;

@Entity
public class Pizza {
    @Id
    public Long id;
    public String name;
}
