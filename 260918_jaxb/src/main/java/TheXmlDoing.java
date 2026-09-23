import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TheXmlDoing {
    private static Path path = Paths.get(
            System.getProperty("user.dir"),
            "src",
            "main",
            "resources"
    );

    // xml -> object
    public Student unmarshalling(String filename) throws JAXBException {
        // this is the long way

        // what I want to get out of the file
        JAXBContext context = JAXBContext.newInstance(Student.class);

        // lets get the unmashaller for doing
        Unmarshaller unmarshaller = context.createUnmarshaller();

        Object o = unmarshaller.unmarshal(Path.of(path.toString(), filename).toFile());

        return (Student) o;
    }

    static void main() {
        TheXmlDoing xmlDoing = new TheXmlDoing();
        try {
            // Student s = xmlDoing.unmarshalling("data.xml");
            Student s = xmlDoing.unmarshalling("datav2.xml");
            System.out.println(s);
        } catch (JAXBException jaxbe) {
            jaxbe.printStackTrace();
        }
    }
}
