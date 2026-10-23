package core;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class EntityClassFinder {
    public static List<String> findAll() throws IOException {
        // Where to to search
        Path path = Path.of(
                System.getProperty("user.dir"),
                "src", "main", "java", "pojo"
        );

        List<String> fileNames = Files.walk(path) // stream
                .filter(p -> p.toString().endsWith(".java"))
                .map(p -> path.relativize(p))
                .map(p -> p.toString())
                .map(str -> str
                        .substring(0, str.lastIndexOf(".")))
                .toList();

        for(String p : fileNames){
            System.out.println(p.toString());
        }

        return fileNames;
    }
}
