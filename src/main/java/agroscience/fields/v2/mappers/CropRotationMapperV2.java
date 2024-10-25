package agroscience.fields.v2.mappers;

import agroscience.fields.v2.dto.croprotation.RequestCropRotationV2;
import agroscience.fields.v2.entities.CropRotationV2;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {CropRotationMapperV2.class})
public interface CropRotationMapperV2 {

  CropRotationV2 map(RequestCropRotationV2 request);

}
