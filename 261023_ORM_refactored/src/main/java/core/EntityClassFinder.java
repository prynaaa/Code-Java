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

    private static String toClassName(String relativePath) {
        return relativePath
                .substring(0, relativePath.lastIndexOf('.'))
                .replace(File.separatorChar, '.');
    }

    private static Class<?> getClassByName(String name) {
        try {
            return Class.forName(name);
        } catch (Exception e) {
            log.warning(e.getMessage());
            return null;
        }
    }
}
