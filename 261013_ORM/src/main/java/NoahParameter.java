import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface NoahParameter {
    String name() default "HTL";
    int value() default -1000;
}
