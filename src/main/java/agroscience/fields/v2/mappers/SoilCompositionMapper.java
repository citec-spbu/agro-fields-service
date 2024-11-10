package agroscience.fields.v2.mappers;

import agroscience.fields.v2.entities.SoilComposition;
import generated.agroscience.fields.api.model.SoilCompositionDTO;
import java.util.List;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring", uses = {SoilCompositionMapper.class})
public interface SoilCompositionMapper {

  SoilComposition map(SoilCompositionDTO request);

  SoilCompositionDTO map(SoilComposition entity);

  List<SoilCompositionDTO> map(List<SoilComposition> soilCompositionList);

}
