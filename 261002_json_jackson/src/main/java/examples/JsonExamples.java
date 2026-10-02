package examples;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import pojos.Person;

public class JsonExamples {

    public static void _01_simpleJson() {
        // your turn: generate a json string generate a person
        String jsonPerson =
                "{" +
                    "\"firstName\": \"Alex\"," +
                    "\"lastName\": \"Colorful\"" +
                "}";

        System.out.println(jsonPerson);

        // json -> string
        ObjectMapper mapper = new ObjectMapper();
        Person p = null;

        try {
            p = mapper.readerFor(Person.class)
                    .readValue(jsonPerson);
        } catch (Exception ex){
            ex.printStackTrace();
        }

        System.out.println(p);
    }

    public static void _02_simpleJson() {
        // your turn: generate a json string generate a person
        String jsonPerson =
                "{" +
                        "\"firstname\": \"Alex\"," +
                        "\"lastname\": \"Colorful\"," +
                        "\"dateOfBirth\": \"14.03.1879\"" +
                        "}";

        System.out.println(jsonPerson);

        // json -> string
        ObjectMapper mapper = new ObjectMapper();
        Person p = null;

        try {
            p = mapper
                    .registerModule(new JavaTimeModule())
                    .readerFor(Person.class)
                    .readValue(jsonPerson);
        } catch (Exception ex){
            ex.printStackTrace();
        }

        System.out.println(p);
    }

    public static void _03_notSoSimpleJson() {
        // your turn: add two children to albert's json string
        String jsonPerson = """
                {
                  "firstname": "Alex",
                  "lastname": "Colorful",
                  "dateOfBirth": "14.03.1879",
                  "children": [
                    {
                      "firstname": "mini",
                      "lastname": "Colorful",
                      "dateOfBirth": "30.11.1900"
                    },
                    {
                      "firstname": "mono",
                      "lastname": "Colorful",
                      "dateOfBirth": "01.05.1902"
                    }
                  ]
                }
                """;

        System.out.println(jsonPerson);

        // json -> string
        ObjectMapper mapper = new ObjectMapper();
        Person p = null;

        try {
            p = mapper
                    .registerModule(new JavaTimeModule())
                    .readerFor(Person.class)
                    .readValue(jsonPerson);
        } catch (Exception ex){
            ex.printStackTrace();
        }

        System.out.println(p);
    }

    public static void _04_moreNotSoSimpleJson() {
        // your turn:
        String jsonPerson = """
                {
                  "firstname": "Alex",
                  "lastname": "Colorful",
                  "dateOfBirth": "14.03.1879",
                  "children": [
                    {
                      "firstname": "mini",
                      "lastname": "Colorful",
                      "dateOfBirth": "30.11.1900"
                    },
                    {
                      "firstname": "mono",
                      "lastname": "Colorful",
                      "dateOfBirth": "01.05.1902"
                    }
                  ]
                }
                """;

        System.out.println(jsonPerson);

        // json -> string
        ObjectMapper mapper = new ObjectMapper();
        Person p = null;

        try {
            p = mapper
                    .registerModule(new JavaTimeModule())
                    .readerFor(Person.class)
                    .readValue(jsonPerson);
        } catch (Exception ex){
            ex.printStackTrace();
        }

        System.out.println(p);
    }

    static void main() {
        // JsonExamples._01_simpleJson();
        // JsonExamples._02_simpleJson();
        JsonExamples._03_notSoSimpleJson();
    }
}
