package agroscience.fields.utilities.validation.validators;

import agroscience.fields.dto.TimeDTO;
import agroscience.fields.utilities.LocalDateConverting;
import agroscience.fields.utilities.validation.constraints.LocalDateTimeFormat;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;

public class LocalDateTimeFormatValidator implements ConstraintValidator<LocalDateTimeFormat, TimeDTO> {
    @Override
    public boolean isValid(TimeDTO request, ConstraintValidatorContext context) {

        LocalDate localStartDate = null;
        LocalDate localEndDate = null;
        boolean isValid = true;

        try {
            localStartDate = LocalDateConverting.stringToLocalDateTime(request.start());
        } catch (Exception e) {
            isValid = false;
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Ошибка в дате создания").addConstraintViolation();
        }

        try {
            localEndDate = LocalDateConverting.stringToLocalDateTime(request.end());
        } catch (Exception e) {
            isValid = false;
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Ошибка в дате конца").addConstraintViolation();
        }

        if (localStartDate != null && localEndDate != null && localEndDate.isBefore(localStartDate)) {
            isValid = false;
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Дата конца должна быть позже даты создания").addConstraintViolation();
        }

        return isValid;
    }
}
