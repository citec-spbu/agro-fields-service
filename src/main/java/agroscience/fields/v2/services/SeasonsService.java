package agroscience.fields.v2.services;

import agroscience.fields.v2.entities.Seasons;
import agroscience.fields.v2.repositories.SeasonsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SeasonsService {
    private final SeasonsRepository seasonsRepository;

    public void save(Seasons season) {
        seasonsRepository.save(season);
    }

    public List<Seasons> getAll(UUID organization_id) {
        return seasonsRepository.getAllByOrganizationId(organization_id);
    }
}
