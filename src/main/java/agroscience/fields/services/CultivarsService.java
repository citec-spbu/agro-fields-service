package agroscience.fields.services;

import agroscience.fields.dao.entities.Cultivar;
import agroscience.fields.dao.repositories.CultivarsRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CultivarsService {

  private final CultivarsRepository cultivarsRepository;

  public List<Cultivar> getCultivars(Long cropId, String name, int page, int size) {
    return cultivarsRepository.findAllByCropCropIdAndCultivarNameIgnoreCaseStartingWith(
            cropId,
            name,
            PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "cultivarName"))
    );
  }
}
