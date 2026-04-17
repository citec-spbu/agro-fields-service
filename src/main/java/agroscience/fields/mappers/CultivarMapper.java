package agroscience.fields.mappers;

import agroscience.fields.dao.entities.Cultivar;
import agroscience.fields.dto.cultivar.ResponseCultivar;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CultivarMapper {

  @Mapping(target = "cropId", source = "crop.cropId")
  ResponseCultivar map(Cultivar cultivar);
}
