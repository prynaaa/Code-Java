package customer_data;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;

import java.util.List;

@Getter
@XmlRootElement(name = "dataHolder")
public class DataHolder {
    private List<Country> countries;

    public DataHolder() {}

    public DataHolder(List<Country> countries) {
        this.countries = countries;
    }

    @XmlElementWrapper(name = "Countries")
    @XmlElement(name = "Country")
    public void setCountries(List<Country> countries) {
        this.countries = countries;
    }

    @Override
    public String toString() {
        return "DataHolder{\n" +
                "countries=" + countries +
                "}\n";
    }
}
