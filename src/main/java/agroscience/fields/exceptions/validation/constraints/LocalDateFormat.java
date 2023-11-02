package agroscience.fields.exceptions.validation.constraints;

import agroscience.fields.exceptions.validation.validators.LocalDateFormatValidator;
import agroscience.fields.exceptions.validation.validators.LocalDateValidatorForFieldType;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {LocalDateFormatValidator.class, LocalDateValidatorForFieldType.class})
public @interface LocalDateFormat {
    String message() default "Дата введена неверно";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
