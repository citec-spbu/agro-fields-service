package agroscience.fields.exceptions.validation.validators;

import agroscience.fields.dto.TimeDTO;
import agroscience.fields.exceptions.DateException;
import agroscience.fields.utilities.LocalDateConverting;
import agroscience.fields.exceptions.validation.constraints.LocalDateFormat;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.data.util.Pair;

import java.time.LocalDate;

public class LocalDateFormatValidator implements ConstraintValidator<LocalDateFormat, TimeDTO> {
    @Override
    public boolean isValid(TimeDTO request, ConstraintValidatorContext context) {

        LocalDate localStartDate = null;
        LocalDate localEndDate = null;
        var ex = new DateException();

        try {
            localStartDate = LocalDateConverting.stringToLocalDate(request.start());
        } catch (Exception e) {
            ex.getFieldErrors().add(Pair.of("start", "Дата введена неправильно"));
        }

        try {
            localEndDate = LocalDateConverting.stringToLocalDate(request.end());
        } catch (Exception e) {
            ex.getFieldErrors().add(Pair.of("end", "Дата введена неправильно"));
        }

        if (localStartDate != null && localEndDate != null && localEndDate.isBefore(localStartDate)) {
            ex.getFieldErrors().add(Pair.of("start", "Дата конца должна быть позже даты создания"));
            ex.getFieldErrors().add(Pair.of("end", "Дата конца должна быть позже даты создания"));
        }

        if(!ex.getFieldErrors().isEmpty()){
            throw ex;
        }

        return true;
    }
}
