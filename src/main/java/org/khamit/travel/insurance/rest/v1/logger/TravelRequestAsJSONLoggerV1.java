package org.khamit.travel.insurance.rest.v1.logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.khamit.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class TravelRequestAsJSONLoggerV1 {
    private final static Logger logger= LoggerFactory.getLogger(TravelRequestAsJSONLoggerV1.class);
    public void log(TravelCalculatePremiumRequestV1 request){
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
