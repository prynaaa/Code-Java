import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

import java.io.File;
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

        // what i want to get out of the file
        JAXBContext context = JAXBContext.newInstance(Student.class);

        // lets get the unmarshaller for doning
        Unmarshaller unmarshaller = context.createUnmarshaller();

        Object o = unmarshaller.unmarshal(Path.of(path.toString(), filename).toFile());

        return (Student) o;
    }


    // object -> xml
    public void marshalling(Student student) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(Student.class);
        Marshaller marshaller = context.createMarshaller();

        // your turn: generate a formatted xml-output
        //marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        //marshaller.marshal(student, System.out);

        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(student, new File(path.toString(), "sc.xml"));
    }
    static void main() {
        TheXmlDoing xmlDoing = new TheXmlDoing();
        try {
            //Student s = xmlDoing.unmarshalling("data.xml");
            //Student s = xmlDoing.unmarshalling("data_v2.xml");
            Student s = xmlDoing.unmarshalling("data_v3.xml");
            //Student s = xmlDoing.unmarshalling("data_v4.xml");
            System.out.println(s);

            s.setName("Josef Peritsch");

            xmlDoing.marshalling(s);

        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }
}
