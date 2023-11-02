package agroscience.fields.exceptions;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class HandleErrorService {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationException(MethodArgumentNotValidException ex) {
        Map<String, Object> errorMap = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(fieldError -> {
            errorMap.put(fieldError.getField(), fieldError.getDefaultMessage());
        });
        Map<String, Object> response = new HashMap<>();
        response.put("errors", errorMap);

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DateException.class)
    public ResponseEntity<Object> handleValidationException(DateException ex) {
        Map<String, Object> errorMap = new HashMap<>();
        ex.getFieldErrors().forEach(fieldError -> {
            errorMap.put(fieldError.getFirst(), fieldError.getSecond());
        });
        Map<String, Object> response = new HashMap<>();
        response.put("errors", errorMap);

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> handleCarNotFoundException(EntityNotFoundException ex){
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("resultCode", HttpStatus.NOT_FOUND.value());
        errorResponse.put("message", ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DuplicateException.class)
    public ResponseEntity<Object> handleDuplicateEvpException(DuplicateException ex){
        Map<String, Object> errorMap = new HashMap<>();
        errorMap.put(ex.getFieldName(), ex.getMessage());
        Map<String, Object> response = new HashMap<>();
        response.put("errors", errorMap);
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }
}
