package customer_data;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import lombok.Getter;

import java.util.List;

@Getter
public class Country {
    private int countryId;
    private String countryName;
    private String countryCode;
    private List<City> cities;

    public Country() {}

    public Country(int countryId, String countryName, String countryCode, List<City> cities) {
        this.countryId = countryId;
        this.countryName = countryName;
        this.countryCode = countryCode;
        this.cities = cities;
    }

    @XmlElement(name = "countryId")
    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    @XmlElement(name = "countryName")
    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    @XmlElement(name = "countryCode")
    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }


    @XmlElementWrapper(name = "Cities")
    @XmlElement(name = "City")
    public void setCities(List<City> cities) {
        this.cities = cities;
    }

    @Override
    public String toString() {
        return "\ncustomer_data.Country{" +
                "countryId=" + countryId +
                ", countryName='" + countryName + '\'' +
                ", countryCode='" + countryCode + '\'' +
                ", cities=" + cities +
                "}\n";
    }
}
