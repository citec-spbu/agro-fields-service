package agroscience.fields.v2.controllers;

import agroscience.fields.dto.soil.RequestSoil;
import agroscience.fields.security.AuthoriseService;
import agroscience.fields.security.Role;
import agroscience.fields.v2.dto.seasons.RequestSeasons;
import agroscience.fields.v2.entities.Seasons;
import agroscience.fields.v2.services.SeasonsService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "api/v2/fields/seasons")
public class SeasonsController {
    private final SeasonsService seasonsService;
    private final ModelMapper modelMapper;

    @PostMapping
    public void save(@Valid @RequestBody RequestSeasons request) {
        var season = modelMapper.map(request, Seasons.class);
        seasonsService.save(season);
    }

    @GetMapping
    public List<Seasons> getAll(UUID orgId) {
        return seasonsService.getAll(orgId);
    }
}