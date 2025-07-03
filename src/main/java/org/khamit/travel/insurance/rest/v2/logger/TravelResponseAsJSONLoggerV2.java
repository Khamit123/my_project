package org.khamit.travel.insurance.rest.v2.logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.khamit.travel.insurance.dto.v2.TravelCalculatePremiumResponseV2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class TravelResponseAsJSONLoggerV2 {
    private final static Logger logger= LoggerFactory.getLogger(TravelResponseAsJSONLoggerV2.class);
    public void log(TravelCalculatePremiumResponseV2 response){
        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();
        try {
            String requestJSON =mapper.writeValueAsString(response);
            logger.info("Ответ:{}", requestJSON);
        } catch (JsonProcessingException e) {
            logger.error(e.getMessage(),e);
        }

    }
}
