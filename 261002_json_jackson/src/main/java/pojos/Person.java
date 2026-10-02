package pojos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class Person {
    @JsonProperty("firstname") // 02
    private String firstName; // 01

    @JsonProperty("lastname") // 02
    private String lastName; // 01

    @JsonFormat(pattern = "dd.MM.yyyy") // 02
    private LocalDate dateOfBirth; // 02

    private List<Person> children; // 03

    private Person parent; // 04

}
