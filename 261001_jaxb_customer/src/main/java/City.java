import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import lombok.Data;

import java.util.List;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class City {
    @XmlElement(name = "cityId")
    private int id;
    @XmlElement(name = "cityName")
    private String name;
    @XmlElement
    private String postalCode;

    @XmlElementWrapper(name = "Customers")
    @XmlElement(name = "Customer")
    private List<Customer> customers;
}
