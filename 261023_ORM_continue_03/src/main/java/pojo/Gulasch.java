package pojo;

import annotations.Column;
import annotations.Entity;
import annotations.Id;
import lombok.Builder;

@Builder
@Entity
public class Gulasch {
    @Id
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name="size")
    private String size;

    @Column(name="price")
    private float price;
}
