package agroscience.fields.v2.controllers;

import agroscience.fields.v2.dto.soilCompositions.RequestSoilComposition;
import agroscience.fields.v2.mappers.SoilCompositionMapper;
import agroscience.fields.v2.services.SoilCompositionsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "api/v2/fields/soilCompositions")
public class SoilCompositionsController {
    private final SoilCompositionMapper soilCompositionMapper;
    private final SoilCompositionsService soilCompositionsService;

    @PostMapping
    public String save(@Valid @RequestBody RequestSoilComposition requestSoilComposition){
        var a = soilCompositionMapper.map(requestSoilComposition);
        return soilCompositionsService.save(a).toString();
    }
}
