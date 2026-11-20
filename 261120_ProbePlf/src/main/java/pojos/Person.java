package pojos;

import annotations.HtmlDate;
import annotations.HtmlElement;
import annotations.HtmlShow;

import java.time.LocalDate;
import java.util.Objects;

@HtmlShow
public class Person {
    @HtmlElement(name = "Nachname")
    private String name;
    @HtmlElement(name = "firstName")
    private String firstName;
    @HtmlElement(name = "Geburtsdatum")
    @HtmlDate(pattern = "YYYY-MM-DD")
    private LocalDate dateOfBirth;
    @HtmlElement(name = "Aktiv", required = false)
    private boolean isActive = true;

    public Person(String name, String firstName, LocalDate dateOfBirth, boolean isActive) {
        this.name = name;
        this.firstName = firstName;
        this.dateOfBirth = dateOfBirth;
        this.isActive = isActive;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return isActive == person.isActive && Objects.equals(name, person.name) && Objects.equals(firstName, person.firstName) && Objects.equals(dateOfBirth, person.dateOfBirth);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, firstName, dateOfBirth, isActive);
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", firstName='" + firstName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", isActive=" + isActive +
                '}';
    }
}
