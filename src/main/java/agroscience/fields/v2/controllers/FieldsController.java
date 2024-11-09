package agroscience.fields.v2.controllers;

import agroscience.fields.v2.mappers.FieldMapperV2;
import agroscience.fields.v2.services.FieldsService;
import generated.agroscience.fields.api.FieldsApi;
import generated.agroscience.fields.api.model.FieldBaseDTO;
import generated.agroscience.fields.api.model.FieldDTO;
import generated.agroscience.fields.api.model.FieldWithContoursAndCropRotationsDTO;
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
  public void changeField(UUID fieldId, FieldBaseDTO fieldBaseDTO) {
    // TODO обновлять
  }

  @Override
  public void deleteField(UUID fieldId) {
    // TODO По факту не удаляем, а просто ставим флажок, что архивировано. С вложенными объектами также
  }

  @Override
  public List<FieldWithContoursAndCropRotationsDTO> findFields(UUID seasonId) {
    // TODO Нужно уметь отсекать архивированные сущности, вот пример getAllByOrganizationIdAndArchivedIsFalse
    // Найти его можно в seasonRepository
    // Важно при запросе в базу отфилтровать cropRotations, чтобы пришёл самый последний только либо никакой
    // Этот метод нужен для превью, для превью было бы "красиво" выводить последнюю растующую культуру,
    // поэтому важно вернуть последний crop rotation
    return fieldMapperV2.map(fieldService.findAll(seasonId));
  }

  @Override
  public IdDTO saveField(UUID seasonId, FieldDTO fieldDTO) {
    var fieldEntity = fieldMapperV2.map(fieldDTO);
    var savedFieldEntity = fieldService.save(seasonId, fieldEntity);
    return new IdDTO(savedFieldEntity.getId());
  }

}