package customer_data;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;

import java.util.Date;

@Getter
public class Customer {
    private String dateOfBirth;
    private String firstname;
    private String gender;
    private String lastname;

    public Customer(){}

    public Customer(String dateOfBirth, String firstname, String gender, String lastname) {
        this.dateOfBirth = dateOfBirth;
        this.firstname = firstname;
        this.gender = gender;
        this.lastname = lastname;
    }

    @XmlElement(name = "dateOfBirth")
    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @XmlElement(name = "firstname")
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    @XmlElement(name = "gender")
    public void setGender(String gender) {
        this.gender = gender;
    }

    @XmlElement(name = "lastname")
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @Override
    public String toString() {
        return "\ncustomer_data.Customer{" +
                "dateOfBirth=" + dateOfBirth +
                ", firstname='" + firstname + '\'' +
                ", gender='" + gender + '\'' +
                ", lastname='" + lastname + '\'' +
                "}\n";
    }
}
