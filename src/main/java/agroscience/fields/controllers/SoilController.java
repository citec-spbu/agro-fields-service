package agroscience.fields.controllers;

import agroscience.fields.dto.soil.RequestSoil;
import agroscience.fields.dto.soil.ResponseSoil;
import agroscience.fields.mappers.SoilMapper;
import agroscience.fields.security.AuthoriseService;
import agroscience.fields.security.Role;
import agroscience.fields.services.SoilService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "api/v1/fields/soil")
public class SoilController {
    private final SoilService soilService;
    private final SoilMapper soilMapper;
    private final AuthoriseService auth;

    @PostMapping
    @Operation(description = "Создание агрохимии")
    public ResponseSoil createSoil(@Valid @RequestBody RequestSoil request, HttpServletRequest header){
        return soilMapper.soilToResponseSoil(soilService.createSoil(auth.doFilter(header,
                new Role.Builder().organization().worker().build()), soilMapper.requestSoilToSoil(request), request.getFieldId()));
    }

    @PutMapping
    @Operation(description = "Обновление агрохимии, fieldId фиктивный в request, он не меняется, было лень использовать другую dto")
    public ResponseSoil updateCrop(@Valid@Min(1) Long soilId, @Valid @RequestBody RequestSoil request, HttpServletRequest header){
        return soilMapper.soilToResponseSoil(soilService.updateSoil(auth.doFilter(header, new Role.Builder().worker().organization().build()),
                soilId, soilMapper.requestSoilToSoil(request)));
    }

    @DeleteMapping
    @Operation(description = "Удаление агрохимии")
    public ResponseEntity<Void> deleteCrop(@Valid@Min(1) Long soilId, HttpServletRequest request){
        soilService.deleteSoilById(soilId, auth.doFilter(request, new Role.Builder().worker().organization().build()));
        return ResponseEntity.noContent().build();
    }
}
