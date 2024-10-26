package agroscience.fields.v2.mappers;

import agroscience.fields.v2.entities.Season;
import generated.agroscience.fields.api.model.SeasonDTO;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SeasonMapper {

  Season map(SeasonDTO seasonDTO);

  List<SeasonDTO> map(List<Season> all);

}