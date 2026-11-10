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
            System.err.println("Can't load " + PATH + ": " + iox.getMessage());
        }
    }

    public static String getProperty(String key){
        return properties.getProperty(key);
    }

    public static void main(String[] args) {
        System.out.println(getProperty("DB_URL"));
    }
}
