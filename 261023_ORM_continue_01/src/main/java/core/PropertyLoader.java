package core;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

public class PropertyLoader {

    private static final Path PATH = Path.of(
            System.getProperty("user.dir"),
            "src",
            "main",
            "resources",
            "persistent.properties"
    );

    private static Properties properties = new Properties();

    // init block
    static {
        try {
            properties.load(Files.newInputStream(PATH));
        } catch(IOException iox){
            System.out.println("Cant load file");
            System.out.println(iox.getStackTrace());
        }
    }

    public static String getProperty(String key){
        return properties.getProperty(key);
    }

    static void main() {
        PropertyLoader propertyLoader = new PropertyLoader();
        System.out.println(propertyLoader.getProperty("DB_URL"));
    }
}
