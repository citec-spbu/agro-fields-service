package agroscience.fields.v2.services;

import jakarta.persistence.EntityNotFoundException;
import java.util.Optional;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class DefaultService {

  protected static  <T> T getOrThrow(Optional<T> opt, Class<T> clazz) {
    return opt.orElseThrow(() -> new EntityNotFoundException(clazz.getSimpleName() + " not found"));
  }

}