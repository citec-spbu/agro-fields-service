package agroscience.fields.v2.mappers;

import agroscience.fields.v2.dto.SoilCompositionDTO;
import agroscience.fields.v2.entities.SoilComposition;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {SoilCompositionMapper.class})
public interface SoilCompositionMapper {
  SoilComposition map(SoilCompositionDTO request);
}
