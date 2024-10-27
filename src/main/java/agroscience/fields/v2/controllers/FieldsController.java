package agroscience.fields.v2.controllers;

import agroscience.fields.v2.mappers.FieldMapperV2;
import agroscience.fields.v2.services.FieldsService;
import generated.agroscience.fields.api.FieldsApi;
import generated.agroscience.fields.api.model.FieldDTO;
import generated.agroscience.fields.api.model.IdDTO;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@PreAuthorize("hasRole('organization') or hasRole('worker')")
public class FieldsController implements FieldsApi {

  private final FieldMapperV2 fieldMapperV2;
  private final FieldsService fieldService;

  @Override
  public void deleteField(UUID fieldId) {
    // TODO По факту не удаляем, а просто ставим флажок, что архивировано. С вложенными объектами также
  }

  @Override
  public List<FieldDTO> findFields(UUID seasonId) {
    // TODO Нужно уметь отсекать архивированные сущности, вот пример getAllByOrganizationIdAndArchivedIsFalse
    // Найти его можно в seasonRepository
    return fieldMapperV2.map(fieldService.findAll(seasonId));
  }

  @Override
  public IdDTO saveField(UUID seasonId, FieldDTO fieldDTO) {
    var fieldEntity = fieldMapperV2.map(fieldDTO);
    var savedFieldEntity = fieldService.save(seasonId, fieldEntity);
    return new IdDTO(savedFieldEntity.getFieldId());
  }

}