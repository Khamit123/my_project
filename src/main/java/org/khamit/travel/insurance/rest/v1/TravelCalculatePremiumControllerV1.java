package org.khamit.travel.insurance.rest.v1;

import lombok.RequiredArgsConstructor;
import org.khamit.travel.insurance.core.service.TravelCalculatePremiumService;
import org.khamit.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
import org.khamit.travel.insurance.dto.v1.TravelCalculatePremiumResponseV1;
import org.khamit.travel.insurance.dto.v2.TravelCalculatePremiumResponseV2;
import org.khamit.travel.insurance.rest.TimeWatchLogger;
import org.khamit.travel.insurance.rest.v1.logger.TravelRequestAsJSONLoggerV1;
import org.khamit.travel.insurance.rest.v1.logger.TravelResponseAsJSONLoggerV1;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RequiredArgsConstructor
@RestController
@RequestMapping("/insurance/travel/v1")
class TravelCalculatePremiumControllerV1 {

	private final TravelCalculatePremiumService calculatePremiumService;
	private final TravelRequestAsJSONLoggerV1 travelRequestAsJSONLoggerV1;
	private final TravelResponseAsJSONLoggerV1 travelResponseAsJSONLoggerV1;
	private final TimeWatchLogger timeWatchLogger;
	private final RequestResponseConverter converter;

	@PostMapping(path = "/",
			consumes = "application/json",
			produces = "application/json")
	public TravelCalculatePremiumResponseV1 calculatePremium(@RequestBody TravelCalculatePremiumRequestV1 requestV1) {
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		travelRequestAsJSONLoggerV1.log(requestV1);
		TravelCalculatePremiumResponseV2 responseV2=calculatePremiumService.calculatePremium(converter.requestConvertFromV1ToV2(requestV1));
		TravelCalculatePremiumResponseV1 responseV1=converter.responseConvertFromV2ToV1(responseV2);
		travelResponseAsJSONLoggerV1.log(responseV1);
		timeWatchLogger.log(stopWatch);
		return responseV1;
	}

}