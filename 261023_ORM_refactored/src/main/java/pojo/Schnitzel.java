package pojo;

import annotations.Column;
import annotations.Entity;
import annotations.Id;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@Entity
@Data
public class Schnitzel {
    @Id
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "cookedOn")
    private LocalDateTime cookedOn;
}
