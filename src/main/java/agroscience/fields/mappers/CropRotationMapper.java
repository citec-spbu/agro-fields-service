package agroscience.fields.mappers;

import agroscience.fields.dao.entities.CropRotation;
import agroscience.fields.dto.*;
import agroscience.fields.dto.croprotation.ResponseCRForF;
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

    @Mapping(target = "startDate", source = "cropRotations.startDate", qualifiedByName = "localDateToString")
    @Mapping(target = "endDate", source = "cropRotations.endDate", qualifiedByName = "localDateToString")
    @Mapping(target = "crop", source = "cropRotations.crop")
    @Mapping(target = "id", source = "cropRotations.id")
    ResponseCRWithField responseCRWithField(CropRotation cropRotations, ResponseFieldName field);

    @Mapping(target = "startDate", source = "startDate", qualifiedByName = "stringToLocalDate")
    @Mapping(target = "endDate", source = "endDate", qualifiedByName = "stringToLocalDate")
    CropRotation CropRotatopnRequestToCropRotation(RequestCropRotation request);
    @Named("localDateToString")
    default String localDateToString(LocalDate date){
        return LocalDateConverting.localDateTimeToString(date);
    }

    @Named("stringToLocalDate")
    default LocalDate localDateToString(String date) throws ParseException {
        return LocalDateConverting.stringToLocalDateTime(date);
    }
//    @Named("cropName")
//    default String cropName(Crop crop){
//        return crop.getName();
//    }

    @Mapping(target = "id", ignore = true)
    void newCRToCR(@MappingTarget CropRotation cropRotation, CropRotation newCropRotation);
}
