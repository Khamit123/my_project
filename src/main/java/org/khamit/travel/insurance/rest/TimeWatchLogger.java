package org.khamit.travel.insurance.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
@Component
public class TimeWatchLogger {
    private final static Logger logger = LoggerFactory.getLogger(TimeWatchLogger.class);

    public void log(StopWatch stopWatch) {
        stopWatch.stop();
        logger.info("Время обработки запроса(мс):{}",stopWatch.getTotalTimeMillis());
    }
}
