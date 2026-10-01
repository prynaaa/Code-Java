import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import lombok.Data;
import lombok.Singular;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;

import java.nio.file.Path;
import java.nio.file.Paths;

@Log
public enum XmlAccess {

    INSTANCE;

    private static String fileName = "customer_data.xml";
    private static Path path = Paths.get(
            System.getProperty("user.dir"),
            "src",
            "main",
            "resources",
            fileName
    );

    static {
        INSTANCE.load();
    }

    // your turn:
    // refactor your XML Access to a Singleton

    private DataHolder dataHolder;

    public DataHolder getDataHolder(){
        return dataHolder;
    }

    private void load(){
        try {
            JAXBContext context = JAXBContext.newInstance(DataHolder.class);
            dataHolder = (DataHolder) context
                    .createUnmarshaller()
                    .unmarshal(path.toFile());
        } catch (JAXBException e) {
            System.out.println("load jaxb unmarshaller failed");
            throw new RuntimeException(e);
        }
    }

    static void main() {
        XmlAccess xmlAccess = XmlAccess.INSTANCE;
        System.out.println(xmlAccess.getDataHolder());
    }
}
