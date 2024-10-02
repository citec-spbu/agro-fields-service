package agroscience.fields.mappers;

import agroscience.fields.dao.entities.Crop;
import agroscience.fields.dto.crop.RequestCrop;
import agroscience.fields.dto.crop.ResponseCrop;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CropMapper {
  Crop requestCropToCrop(RequestCrop requestCrop);

  ResponseCrop cropToResponseCrop(Crop crop);
}
