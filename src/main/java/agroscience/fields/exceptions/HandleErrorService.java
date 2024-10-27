package agroscience.fields.exceptions;

import static java.util.Objects.requireNonNullElse;

import generated.agroscience.fields.api.model.ExceptionBody;
import generated.agroscience.fields.api.model.ExceptionBodyWithErrors;
import jakarta.persistence.EntityNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class HandleErrorService {

  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ExceptionBodyWithErrors handleValidationException(MethodArgumentNotValidException ex) {
    List<FieldError> errors = ex.getBindingResult().getFieldErrors();
    Map<String, String> errorMap = errors.stream()
            .collect(Collectors.toMap(
                    FieldError::getField,
                    error -> requireNonNullElse(error.getDefaultMessage(), "No message available")
            ));
    return new ExceptionBodyWithErrors(errorMap, "Bad request");
  }

  @ExceptionHandler(EntityNotFoundException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ExceptionBodyWithErrors handleEntityNotFoundException(EntityNotFoundException ex) {
    Map<String, String> errors = new HashMap<>();
    errors.put("error", ex.getMessage());
    return new ExceptionBodyWithErrors(errors, "Model not found");
  }

  @ExceptionHandler(WrongCoordinatesException.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public ExceptionBody handleWrongCoordinatesException(Exception e) {
    log.error("Internal error", e);
    return new ExceptionBody("Internal server error");
  }

}