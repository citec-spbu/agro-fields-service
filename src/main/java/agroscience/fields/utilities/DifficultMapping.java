package agroscience.fields.utilities;

import agroscience.fields.dao.entities.CropRotation;
import agroscience.fields.dto.ResponseCRWithField;
import agroscience.fields.dto.crop.ResponseCrop;
import agroscience.fields.dto.ResponseFieldName;

import java.util.ArrayList;
import java.util.List;

public class DifficultMapping {
    public static List<ResponseCRWithField> listResponseCRWithFieldList(List<CropRotation> cropRotations, List<ResponseFieldName> fieldNames) {
        List<ResponseCRWithField> result = new ArrayList<>();

        // Проверка на null и на равное количество элементов в списках
        if (cropRotations == null || fieldNames == null || cropRotations.size() != fieldNames.size()) {
            // Можно сгенерировать исключение или обработать ошибку по вашему усмотрению
            return result;
        }

        for (int i = 0; i < cropRotations.size(); i++) {
            CropRotation cropRotation = cropRotations.get(i);
            ResponseFieldName fieldName = fieldNames.get(i);

            ResponseCRWithField responseCRWithField = new ResponseCRWithField();
            responseCRWithField.setId(cropRotation.getId());
            responseCRWithField.setCrop(new ResponseCrop(cropRotation.getCrop().getId(), cropRotation.getCrop().getName()));
            responseCRWithField.setStartDate(cropRotation.getStartDate().toString());
            responseCRWithField.setEndDate(cropRotation.getEndDate().toString());
            responseCRWithField.setDescription(cropRotation.getDescription());

            ResponseFieldName responseFieldName = new ResponseFieldName();
            responseFieldName.setId(fieldName.getId());
            responseFieldName.setName(fieldName.getName());

            responseCRWithField.setField(responseFieldName);

            result.add(responseCRWithField);
        }

        return result;
    }
}
