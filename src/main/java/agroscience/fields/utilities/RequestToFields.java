package agroscience.fields.utilities;

import agroscience.fields.dto.ResponseFieldName;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientRequestException;

import java.util.List;

public class RequestToFields {
    public static List<ResponseFieldName> getFields(WebClient webClient, List<Long> ids) throws WebClientRequestException {
        List<ResponseFieldName> fieldNames = webClient
                .get()
                .uri(uriBuilder -> uriBuilder.path("/getNamesByIds")
                        .queryParam("id", ids)
                        .build())
                .retrieve()
                .bodyToFlux(ResponseFieldName.class)
                .collectList()
                .block();
        return fieldNames;
    }
}
