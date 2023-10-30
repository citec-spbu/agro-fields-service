package agroscience.fields.mappers;

import agroscience.fields.dao.entities.Crop;
import agroscience.fields.dto.RequestCrop;
import agroscience.fields.dto.crop.ResponseCrop;
import agroscience.fields.dto.ResponseCropName;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CropMapper {
    ResponseCropName cropToCropNameResponse(Crop crop);
    Crop requestCropToCrop(RequestCrop requestCrop);
    ResponseCrop cropToResponseCrop(Crop crop);
}
