import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface NoahParameter {
    String name() default "HTL";
    int value() default -1000;
    // your turn: implement and Array as parameter of an Annotations
    float[] listOfFloats() default {2f, 3f};
}
