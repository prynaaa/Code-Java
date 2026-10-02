package pojos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@Data
public class Person {
    // @JsonProperty("firstname") // 02
    private String firstName; // 01

    // @JsonProperty("lastname") // 02
    private String lastName; // 01

    // @JsonFormat(pattern = "dd.MM.yyyy") // 02
    private LocalDate dateOfBirth; // 02

    private List<Person> children; // 03

    // @JsonIgnore // 04 ignore thee parent for no Stack overflow
    @ToString.Exclude
    private Person parent; // 04

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Person{");
        sb.append(" firstName=").append(firstName);
        sb.append(", lastName=").append(lastName);
        sb.append(", dateOfBirth=").append(dateOfBirth);
        if(children != null){
            sb.append(", children=").append(children);
        }
        if(parent != null){
            sb.append(", parent=").append(parent.firstName).append(" ").append(parent.lastName);
        }
        sb.append(" }");

        return sb.toString();
    }
}
