package agroscience.fields.exceptions.validation.constraints;

import agroscience.fields.exceptions.validation.validators.LocalDateTimeFormatValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = LocalDateTimeFormatValidator.class)
public @interface LocalDateTimeFormat {
    String message() default "Дата введена неверно";
    String fieldName() default ""; // Добавляем поле для указания имени поля
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
