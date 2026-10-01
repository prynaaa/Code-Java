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

}
