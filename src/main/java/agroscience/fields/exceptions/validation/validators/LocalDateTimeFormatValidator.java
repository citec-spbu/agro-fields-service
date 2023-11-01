package agroscience.fields.exceptions.validation.validators;

import agroscience.fields.dto.TimeDTO;
import agroscience.fields.exceptions.DateException;
import agroscience.fields.utilities.LocalDateConverting;
import agroscience.fields.exceptions.validation.constraints.LocalDateTimeFormat;
import jakarta.persistence.Tuple;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.core.MethodParameter;
import org.springframework.data.util.Pair;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import reactor.util.function.Tuple2;

import java.time.LocalDate;

public class LocalDateTimeFormatValidator implements ConstraintValidator<LocalDateTimeFormat, TimeDTO> {
    @Override
    public boolean isValid(TimeDTO request, ConstraintValidatorContext context) {

        LocalDate localStartDate = null;
        LocalDate localEndDate = null;
        var ex = new DateException();

        try {
            localStartDate = LocalDateConverting.stringToLocalDateTime(request.start());
        } catch (Exception e) {

            ex.getFieldErrors().add(Pair.of("start", "Дата написана неправильно"));
        }

        try {
            localEndDate = LocalDateConverting.stringToLocalDateTime(request.end());
        } catch (Exception e) {
            ex.getFieldErrors().add(Pair.of("end", "Дата написана неправильно"));
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
