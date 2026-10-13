import java.lang.reflect.Method;
import java.util.Arrays;

public class Main {
    static void main() {
        System.out.println("Start Annotations");
        Demo demo = new Demo();

        demo.methodWithAnnotations();

        // Methods of Class Object
        Method[] methods = Object.class.getMethods(); // type Class
        for(Method m : methods){
            System.out.println(m.getName() + "()");
        }

        System.out.println("-".repeat(40));
        methods = Demo.class.getMethods();
        for(Method m : methods){
            System.out.println(m.getName() + "()");
        }

        System.out.println("\n" + "-".repeat(20) + "NOAH" + "-".repeat(20));
        methods = Demo.class.getDeclaredMethods(); // get only self written methods
        for(Method m : methods){
            System.out.println(m.getName() + "()");
            if(m.isAnnotationPresent(Noah.class)) {
                System.out.println("- @NOAH is watching you...");
            } else {
                System.out.println("- @NOAH is nowhere to be seen...");
            }
        }

        System.out.println("\n" + "-".repeat(20) + "NOAH's PARAMETER" + "-".repeat(20));
        for(Method m : methods){
            System.out.println(m.getName() + "()");
            if(m.isAnnotationPresent(NoahParameter.class)) {
                System.out.println("- @NOAHPARAMETER is watching you...");
                // Create an Object of NoahParameter Annotation
                NoahParameter parameter = m.getAnnotation(NoahParameter.class);
                System.out.println("- Value: " + parameter.name());
                // added a new parameter to annotation called value
                System.out.println("- Value: " + parameter.value());
                // added a float list parameter
                System.out.println("- Value: " + Arrays.toString(parameter.listOfFloats()));
            }
        }

    }
}
