package core;

import lombok.extern.java.Log;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

@Log
public class PropertyLoader {
    Properties properties;

    public PropertyLoader() {

        properties = new Properties();
        try {
            loadFile();
        } catch (Exception e) {
            log.warning("FAILED TO LOAD PROPERTIES: " + e.getMessage());
        }
    }

    private void loadFile() throws IOException {
        /*
        TO-DO:
        1. path [done]
        2. load properties
        3. store
         */

        Path path = Paths.get(
                System.getProperty("user.dir"),
                "src",
                "main",
                "resources",
                "persistent.properties"
        );

        properties.load(Files.newInputStream(path));

        log.info("SUCCESSFULLY LOADED PROPERTIES");
    }

    public String getProperty(String propertyName) {
        return properties.getProperty(propertyName);
    }

    static void main() {
        PropertyLoader pl = new PropertyLoader();
        log.info(pl.getProperty("DB_USER"));
    }

}