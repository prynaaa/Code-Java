package core;
import lombok.extern.java.Log;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Log
public class EntityFileLoader {
    static Path path = Path.of(System.getProperty("user.dir"), "src", "resources");

    // Give string to init method of each Object for generalizations

    List<?> load(String fileName){

        List<?> list = null;
        try {
            for (String line : Files.readAllLines(path, StandardCharsets.UTF_8)) {
                String[] parts = line.split(";");
                Object o;
            }

        } catch (Exception e) {
            log.severe(e.getMessage());
        }
        return list;
    }
}
