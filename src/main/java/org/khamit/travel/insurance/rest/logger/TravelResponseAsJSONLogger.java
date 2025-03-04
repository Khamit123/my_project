package org.khamit.travel.insurance.rest.logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.khamit.travel.insurance.dto.TravelCalculatePremiumResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class TravelResponseAsJSONLogger {
    private final static Logger logger= LoggerFactory.getLogger(TravelResponseAsJSONLogger.class);
    public void log(TravelCalculatePremiumResponse response){
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
