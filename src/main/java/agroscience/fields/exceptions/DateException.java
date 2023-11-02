package agroscience.fields.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.util.Pair;
import reactor.util.function.Tuple2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@EqualsAndHashCode(callSuper = true)
@Data
public class DateException extends IllegalArgumentException {
    private final List<Pair<String, String>> fieldErrors = new ArrayList<>();
}
