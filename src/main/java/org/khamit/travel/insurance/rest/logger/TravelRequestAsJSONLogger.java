package org.khamit.travel.insurance.rest.logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.khamit.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class TravelRequestAsJSONLogger {
    private final static Logger logger= LoggerFactory.getLogger(TravelRequestAsJSONLogger.class);
    public void log(TravelCalculatePremiumRequest request){
         ObjectMapper mapper = new ObjectMapper();
         mapper.findAndRegisterModules();
        try {
            String requestJSON =mapper.writeValueAsString(request);
            logger.info("Запрос:{}", requestJSON);
        } catch (JsonProcessingException e) {
            logger.error(e.getMessage(),e);
        }

    }

}
