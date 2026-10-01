import jakarta.xml.bind.annotation.*;
import lombok.Data;

import java.util.List;

@Data
@XmlRootElement(name = "dataHolder")
@XmlAccessorType(XmlAccessType.FIELD)
public class DataHolder {
    @XmlElementWrapper(name = "Countries")
    @XmlElement(name = "Country")
    private List<Country> countries;

    public void loadCitiesIntoCustomer(){
        for(Country country : countries){
            for(City city : country.getCities()){
                for(Customer customer : city.getCustomers()){
                    customer.setCity(city);
                }
            }
        }
    }
}
