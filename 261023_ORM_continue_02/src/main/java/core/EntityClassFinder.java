package core;

import annotations.Entity;
import lombok.extern.java.Log;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Log
public class EntityClassFinder {

    /** Finds all classes below src/main/java that are annotated with @Entity. */
    public static List<Class<?>> findAll() throws IOException {
        Path root = Path.of(System.getProperty("user.dir"), "src", "main", "java");

        try (Stream<Path> files = Files.walk(root)) {
            return files
                    .filter(p -> p.toString().endsWith(".java"))
                    .map(root::relativize)
                    .map(Path::toString)
                    .map(EntityClassFinder::toClassName)
                    .map(EntityClassFinder::getClassByName)
                    .filter(Objects::nonNull)
                    .filter(clazz -> clazz.isAnnotationPresent(Entity.class))
                    .collect(Collectors.toUnmodifiableList());
        }
    }

    // "pojo/Pizza.java" -> "pojo.Pizza"
    private static String toClassName(String relativePath) {
        return relativePath
                .substring(0, relativePath.lastIndexOf('.'))
                .replace(File.separatorChar, '.');
    }

    private static Class<?> getClassByName(String name) {
        try {
            // initialize=false: we only need the annotations, don't run static initializers
            return Class.forName(name, false, EntityClassFinder.class.getClassLoader());
        } catch (ClassNotFoundException | LinkageError e) {
            log.warning("Could not load class " + name + ": " + e.getMessage());
            return null;
        }
    }
}
