package org.khamit.travel.insurance.rest.v2;

import lombok.RequiredArgsConstructor;
import org.khamit.travel.insurance.core.service.TravelCalculatePremiumService;
import org.khamit.travel.insurance.dto.v2.TravelCalculatePremiumRequestV2;
import org.khamit.travel.insurance.dto.v2.TravelCalculatePremiumResponseV2;
import org.khamit.travel.insurance.rest.TimeWatchLogger;
import org.khamit.travel.insurance.rest.v2.logger.TravelRequestAsJSONLoggerV2;
import org.khamit.travel.insurance.rest.v2.logger.TravelResponseAsJSONLoggerV2;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RequiredArgsConstructor
@RestController
@RequestMapping("/insurance/travel/v2")
class TravelCalculatePremiumControllerV2 {

	private final TravelCalculatePremiumService calculatePremiumService;
	private final TravelRequestAsJSONLoggerV2 travelRequestAsJSONLoggerV2;
	private final TravelResponseAsJSONLoggerV2 travelResponseAsJSONLoggerV2;
	private final TimeWatchLogger timeWatchLogger;


	@PostMapping(path = "/",
			consumes = "application/json",
			produces = "application/json")
	public TravelCalculatePremiumResponseV2 calculatePremium(@RequestBody TravelCalculatePremiumRequestV2 request) {
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		travelRequestAsJSONLoggerV2.log(request);
		TravelCalculatePremiumResponseV2 response=calculatePremiumService.calculatePremium(request);
		travelResponseAsJSONLoggerV2.log(response);
		timeWatchLogger.log(stopWatch);
		return response;
	}

}