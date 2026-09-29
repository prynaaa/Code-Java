package customer_data;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import lombok.Getter;

import java.util.List;

@Getter
public class City {
    private int cityId;
    private String cityName;
    private List<Customer> customers;
    private int postalCode;

    public City() {}

    public City(int cityId, String cityName, List<Customer> customers, int postalCode) {
        this.cityId = cityId;
        this.cityName = cityName;
        this.customers = customers;
        this.postalCode = postalCode;
    }

    @XmlElement(name = "cityId")
    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    @XmlElement(name = "cityName")
    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    @XmlElementWrapper(name = "Customers")
    @XmlElement(name = "Customer")
    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

    @XmlElement(name = "postalCode")
    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }

    @Override
    public String toString() {
        return "\ncustomer_data.City{" +
                "cityId=" + cityId +
                ", cityName='" + cityName + '\'' +
                ", customers=" + customers +
                ", postalCode=" + postalCode +
                "}\n";
    }
}
