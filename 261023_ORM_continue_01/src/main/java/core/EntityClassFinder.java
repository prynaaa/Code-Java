package core;

import annotations.Entity;
import lombok.extern.java.Log;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Log
public class EntityClassFinder {
    public static List<String> findAll() throws IOException {
        // Where to to search
        Path path = Path.of(
                System.getProperty("user.dir"),
                "src", "main", "java"
        );

        List<String> fileNames = Files.walk(path) // stream
                .filter(p -> p.toString().endsWith(".java"))
                .map(path::relativize)
                .map(Path::toString)
                .map(str -> str
                        .replace(File.separatorChar, '.')
                        .substring(0, str.lastIndexOf(".")))
                .map(EntityClassFinder::getClassByName)
                .filter(clazz -> clazz.isAnnotationPresent(Entity.class))
                .toList();

        for(String p : fileNames){
            System.out.println(p);
        }

        return fileNames;
    }

    private static Object getClassByName(String str) {
        try {
            return Class.forName(str);
        } catch (Exception e) {
            log.warning(e.getMessage());
        }
        return null;
    }
}
