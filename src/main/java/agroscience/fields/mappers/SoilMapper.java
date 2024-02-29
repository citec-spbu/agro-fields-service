package agroscience.fields.mappers;

import agroscience.fields.dao.entities.Field;
import agroscience.fields.dao.entities.Soil;
import agroscience.fields.dto.soil.RequestSoil;
import agroscience.fields.dto.soil.ResponseSoil;
import agroscience.fields.dto.soil.ResponseSoilForF;
import agroscience.fields.utilities.LocalDateConverting;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

import java.text.ParseException;
import java.time.LocalDate;

@Mapper(componentModel = "spring")
public interface SoilMapper {
    @Mapping(target = "soilSampleDate", source = "soilSampleDate", qualifiedByName = "stringToLocalDate2")
    Soil requestSoilToSoil(RequestSoil requestSoil);

    @Mapping(target = "soilSampleDate", source = "soilSampleDate", qualifiedByName = "localDateToString2")
    @Mapping(target = "fieldId", source = "field", qualifiedByName = "fieldId")
    ResponseSoil soilToResponseSoil(Soil soil);
    @Mapping(target = "soilId", ignore = true)
    @Mapping(target = "field", ignore = true)
    void newSoilToSoil(@MappingTarget Soil soil, Soil newSoil);

    @Named("localDateToString2")
    default String localDateToString2(LocalDate date){
        return LocalDateConverting.localDateToString(date);
    }

    @Named("stringToLocalDate2")
    default LocalDate localDateToString2(String date) throws ParseException {
        return LocalDateConverting.stringToLocalDate(date);
    }
    @Named("fieldId")
    default Long getFieldId(Field field) {
        return field.getFieldId();
    }

}
