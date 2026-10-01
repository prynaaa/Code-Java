/*
<countryId>0</countryId>
<countryName>Greece</countryName>
<countryCode>GR</countryCode
 */

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import lombok.Data;

import java.util.List;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class Country {
    @XmlElement(name = "countryId")
    private int id;
    @XmlElement(name = "countryName")
    private String name;
    @XmlElement(name = "countryCode")
    private String code; // Enum
    @XmlElementWrapper(name = "Cities")
    @XmlElement(name = "City")
    private List<City> cities;
}
