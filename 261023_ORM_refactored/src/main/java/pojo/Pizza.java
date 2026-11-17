package pojo;

import annotations.Column;
import annotations.Entity;
import annotations.Id;
import lombok.Builder;
import lombok.Data;

@Builder
@Entity
@Data
public class Pizza {
    @Id
    public Long id;
    @Column(name="name")
    public String name;
    @Column(name="quanta_costa")
    public float price;
}
