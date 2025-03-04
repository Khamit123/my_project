package org.khamit.travel.insurance.rest;

import lombok.RequiredArgsConstructor;
import org.khamit.travel.insurance.core.TravelCalculatePremiumService;
import org.khamit.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.khamit.travel.insurance.dto.TravelCalculatePremiumResponse;
import org.khamit.travel.insurance.rest.logger.TimeWatchLogger;
import org.khamit.travel.insurance.rest.logger.TravelRequestAsJSONLogger;
import org.khamit.travel.insurance.rest.logger.TravelResponseAsJSONLogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RequiredArgsConstructor
@RestController
@RequestMapping("/insurance/travel")
class TravelCalculatePremiumController {

	private final TravelCalculatePremiumService calculatePremiumService;
	private final TravelRequestAsJSONLogger travelRequestAsJSONLogger;
	private final TravelResponseAsJSONLogger travelResponseAsJSONLogger;
	private final TimeWatchLogger timeWatchLogger;


	@PostMapping(path = "/",
			consumes = "application/json",
			produces = "application/json")
	public TravelCalculatePremiumResponse calculatePremium(@RequestBody TravelCalculatePremiumRequest request) {
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		travelRequestAsJSONLogger.log(request);
		TravelCalculatePremiumResponse response=calculatePremiumService.calculatePremium(request);
		travelResponseAsJSONLogger.log(response);
		timeWatchLogger.log(stopWatch);
		return response;
	}

}