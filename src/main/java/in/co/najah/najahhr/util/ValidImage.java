package in.co.najah.najahhr.util;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CheckForValidBase64Image.class)
@Documented
public @interface ValidImage {

    String message() default "{in.co.najah.najahhr.util.ValidImage.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    @Target({ElementType.FIELD})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @interface List {
        ValidImage[] value();
    }

}
