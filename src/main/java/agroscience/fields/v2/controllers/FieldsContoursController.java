package agroscience.fields.v2.controllers;

import agroscience.fields.v2.dto.fields.RequestFieldv2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(path = "api/v2/fields/fields")
public class FieldsContoursController {
    @PostMapping
    public void save(RequestFieldv2 field){
        log.info("recive {}",field);
    }
}
