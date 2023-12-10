package agroscience.fields.services;

import agroscience.fields.dao.entities.Crop;
import agroscience.fields.dao.repositories.CropsRepository;
import agroscience.fields.exceptions.DuplicateException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CropsService {
    private final CropsRepository cropsRepository;

    public List<Crop> getCrop(String name, int page, int size) {
        return cropsRepository.findAllByNameIgnoreCaseStartingWith(
                name, PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "name"))
        );
    }
}