import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.Date;

@XmlRootElement
public class Student {
    private String name;
    private Date dateOfBirth;

    public Student(){} // needed for jaxb runtime

    public Student(String name, Date dateOfBirth) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getName() {
        return name;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    @Override
    public String toString() {
        return "Student{name=" + name + ", dateOfBirth=" + dateOfBirth + "}";
    }
}
