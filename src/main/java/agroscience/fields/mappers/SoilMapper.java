package agroscience.fields.mappers;

import agroscience.fields.dao.entities.Field;
import agroscience.fields.dao.entities.Soil;
import agroscience.fields.dto.soil.RequestSoil;
import agroscience.fields.dto.soil.ResponseSoil;
import agroscience.fields.utilities.LocalDateConverting;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

import java.text.ParseException;
import java.time.LocalDate;

@Mapper(componentModel = "spring")
public interface SoilMapper {
    @Mapping(target = "sampleDate", source = "sampleDate", qualifiedByName = "stringToLocalDate")
    Soil requestSoilToSoil(RequestSoil requestSoil);

    @Mapping(target = "sampleDate", source = "sampleDate", qualifiedByName = "localDateToString")
    @Mapping(target = "fieldId", source = "field", qualifiedByName = "fieldId")
    ResponseSoil soilToResponseSoil(Soil soil);
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "field", ignore = true)
    Soil newSoilToSoil(@MappingTarget Soil soil, Soil newSoil);

    @Named("localDateToString")
    default String localDateToString(LocalDate date){
        return LocalDateConverting.localDateToString(date);
    }

    @Named("stringToLocalDate")
    default LocalDate localDateToString(String date) throws ParseException {
        return LocalDateConverting.stringToLocalDate(date);
    }
    @Named("fieldId")
    default Long getFieldId(Field field) {
        return field.getId();
    }

}
