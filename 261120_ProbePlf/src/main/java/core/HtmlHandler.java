package core;

import annotations.HtmlDate;
import annotations.HtmlElement;
import annotations.HtmlShow;
import pojos.Car;
import pojos.Person;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class HtmlHandler {
    public static String generateHtml(Object o) throws IllegalAccessException {
        StringBuilder html = new StringBuilder();
        List<String> fields = new ArrayList<>();
        List<String> values = new ArrayList<>();

        html.append("<!DOCTYPE html>\n");
        html.append("<html>\n");
        html.append("<body>\n");
        html.append("   <h1>").append(o.getClass().getSimpleName()).append("</h1>\n");
        if(o.getClass().isAnnotationPresent(HtmlShow.class)) {
            for(Field field : o.getClass().getDeclaredFields()) {
                if(field.isAnnotationPresent(HtmlElement.class)) {
                    fields.add(field.getAnnotation(HtmlElement.class).name());
                    field.setAccessible(true);
                    Object value = field.get(o);
                    if(value != null) {
                        values.add(value.toString());
                    }
                }
            }
        }

        if (!fields.isEmpty() && fields.size() == values.size()) {
            for(int i = 0; i < fields.size(); i++) {
                html.append("   <p>").append(fields.get(i)).append(": ").append(values.get(i)).append("</p>\n");
            }
        }

        html.append("</body>\n");
        html.append("</html>\n");
        return html.toString();
    }

    public static List<Class> findAllHtmlGeneratedClasses() throws IOException {
        Path path = Path.of(System.getProperty("user.dir"), "src", "main", "java");

        List<Class> htmlGeneratedClasses = Files.walk(path) // stream
                .filter(p -> p.toString().endsWith(".java"))
                .map(path::relativize)
                .map(Path::toString)
                .map(str -> str
                        .replace(File.separatorChar, '.')
                        .substring(0, str.lastIndexOf(".")))
                .map(HtmlHandler::getClass).filter(Objects::nonNull)
                .filter(clazz -> clazz.isAnnotationPresent(HtmlShow.class))
                .collect(Collectors.toUnmodifiableList());

        return htmlGeneratedClasses;
    }

    public static Class<?> getClass(String className) {
        try {
            return Class.forName(className);
        } catch (ClassNotFoundException e) {
            return null;
        }
    }

    public static void main(String[] args) {
        Person p = new Person("Einstein", "Albert", LocalDate.of(1988,  12, 03), false);
        Car c = new Car("Porsche", 345, true);
        try {
            System.out.println(HtmlHandler.generateHtml(p));
            System.out.println(HtmlHandler.generateHtml(c));
            System.out.println(HtmlHandler.findAllHtmlGeneratedClasses());
        } catch (Exception e) {

            throw new RuntimeException(e);
        }
    }
}
