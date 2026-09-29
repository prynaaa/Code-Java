package customer_data;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import jakarta.xml.bind.JAXBException;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Marker;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Logger;

@Slf4j
public enum Worker {
    INSTANCE;

    private static Path path = Paths.get(
            System.getProperty("user.dir"),
            "src",
            "main",
            "resources"
    );

    public DataHolder unmarshalling(String fileName) throws JAXBException {
        log.info("Start unmarshalling...");
        JAXBContext context = JAXBContext.newInstance(DataHolder.class);

        Unmarshaller unmarshaller = context.createUnmarshaller();

        Object o = unmarshaller.unmarshal(Path.of(path.toString(), fileName).toFile());

        return (DataHolder) o;
    }

    public void marshalling(String newFileName, DataHolder dataHolder) throws JAXBException {
        log.info("Start marshalling...");
        JAXBContext context = JAXBContext.newInstance(DataHolder.class);

        Marshaller marshaller = context.createMarshaller();

        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(dataHolder, new File(path.toString(), newFileName));
    }

    static void main() {
        try {

            DataHolder dataHolder = Worker.INSTANCE.unmarshalling("customer_data.xml");

            System.out.println(dataHolder);

            Worker.INSTANCE.marshalling("new_customer_data.xml", dataHolder);

        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }
}
