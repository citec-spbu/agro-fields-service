package agroscience.fields.utilities.validation.constraints;

import agroscience.fields.utilities.validation.validators.LocalDateTimeFormatValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = LocalDateTimeFormatValidator.class)
public @interface LocalDateTimeFormat {
    String message() default "Дата введена неверно";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
