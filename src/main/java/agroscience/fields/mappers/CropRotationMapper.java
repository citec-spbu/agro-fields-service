package agroscience.fields.mappers;

import agroscience.fields.dao.models.FandCRandC;
import agroscience.fields.dao.entities.CropRotation;
import agroscience.fields.dto.croprotation.*;
import agroscience.fields.utilities.LocalDateConverting;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.List;

@Mapper(componentModel = "spring", uses = CropMapper.class)
public interface CropRotationMapper {
    @Mapping(target = "startDate", source = "startDate", qualifiedByName = "localDateToString")
    @Mapping(target = "endDate", source = "endDate", qualifiedByName = "localDateToString")
    @Mapping(target = "crop", source = "crop")
    ResponseCRForF cropRotationToCropRotationResponse(CropRotation cropRotation);

    @Mapping(target = "startDate", source = "startDate", qualifiedByName = "localDateToString")
    @Mapping(target = "endDate", source = "endDate", qualifiedByName = "localDateToString")
    @Mapping(target = "cropName", source = "crop", qualifiedByName = "cropName")
    List<ResponseCRForF> cropRotationToCropRotationResponse(List<CropRotation> cropRotations);
    @Mapping(target = "cropRotations", source = "cropRotationResponses")
    ResponseListCropRotationsForField cropRotationsToList(Long fieldId,
                                                          List<ResponseCRForF> cropRotationResponses);

    @Mapping(target = "startDate", source = "dto.cropRotation.startDate", qualifiedByName = "localDateToString")
    @Mapping(target = "endDate", source = "dto.cropRotation.endDate", qualifiedByName = "localDateToString")
    @Mapping(target = "crop", source = "dto.crop")
    @Mapping(target = "id", source = "dto.cropRotation.id")
    @Mapping(target = "description", source = "dto.cropRotation.description")
    @Mapping(target = "field", source = "dto.field")
    ResponseCRWithField responseCRWithField(FandCRandC dto);

    @Mapping(target = "startDate", source = "startDate", qualifiedByName = "stringToLocalDate")
    @Mapping(target = "endDate", source = "endDate", qualifiedByName = "stringToLocalDate")
    @Mapping(target = "field", ignore = true)
    @Mapping(target = "crop", ignore = true)
    CropRotation CropRotatopnRequestToCropRotation(RequestCropRotation request);
    @Named("localDateToString")
    default String localDateToString(LocalDate date){
        return LocalDateConverting.localDateToString(date);
    }

    @Named("stringToLocalDate")
    default LocalDate localDateToString(String date) throws ParseException {
        return LocalDateConverting.stringToLocalDate(date);
    }

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "field", ignore = true)
    @Mapping(target = "crop", ignore = true)
    void newCRToCR(@MappingTarget CropRotation cropRotation, CropRotation newCropRotation);
}
