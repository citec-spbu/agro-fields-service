package agroscience.fields.utilities.validation.validators;

import agroscience.fields.dto.RequestCropRotation;
import agroscience.fields.utilities.LocalDateConverting;
import agroscience.fields.utilities.validation.constraints.LocalDateTimeFormat;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;

public class LocalDateTimeFormatValidator implements ConstraintValidator<LocalDateTimeFormat, RequestCropRotation> {
    @Override
    public boolean isValid(RequestCropRotation request, ConstraintValidatorContext context) {

        LocalDate localStartDate = null;
        LocalDate localEndDate = null;
        boolean isValid = true;

        try {
            localStartDate = LocalDateConverting.stringToLocalDateTime(request.getStartDate());
        } catch (Exception e) {
            isValid = false;
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Ошибка в дате создания").addConstraintViolation();
        }

        try {
            localEndDate = LocalDateConverting.stringToLocalDateTime(request.getEndDate());
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
