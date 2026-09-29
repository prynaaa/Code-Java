package customer_data;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Unmarshaller;
import jakarta.xml.bind.JAXBException;

import java.nio.file.Path;
import java.nio.file.Paths;

public enum Worker {
    INSTANCE;

    private static Path path = Paths.get(
            System.getProperty("user.dir"),
            "src",
            "main",
            "resources"
    );

    public DataHolder unmarshalling(String fileName) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(DataHolder.class);

        Unmarshaller unmarshaller = context.createUnmarshaller();

        Object o = unmarshaller.unmarshal(Path.of(path.toString(), fileName).toFile());

        return (DataHolder) o;
    }

    static void main() {
        try {

            DataHolder dataHolder = Worker.INSTANCE.unmarshalling("customer_data.xml");

            System.out.println(dataHolder);

        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }
}
