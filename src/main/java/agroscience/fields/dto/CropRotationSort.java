package agroscience.fields.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;

@Getter
@RequiredArgsConstructor
public enum CropRotationSort {

    START_DATE_DESC(Sort.by(Sort.Direction.DESC, "endDate")),
    START_DATE_ASC(Sort.by(Sort.Direction.ASC, "startDate"));

    private final Sort sortValue;

}
