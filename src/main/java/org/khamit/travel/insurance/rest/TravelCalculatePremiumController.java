package org.khamit.travel.insurance.rest;

import lombok.RequiredArgsConstructor;
import org.khamit.travel.insurance.core.TravelCalculatePremiumService;
import org.khamit.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.khamit.travel.insurance.dto.TravelCalculatePremiumResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RequiredArgsConstructor
@RestController
@RequestMapping("/insurance/travel")
class TravelCalculatePremiumController {

	private final TravelCalculatePremiumService calculatePremiumService;


	@PostMapping(path = "/",
			consumes = "application/json",
			produces = "application/json")
	public TravelCalculatePremiumResponse calculatePremium(@RequestBody TravelCalculatePremiumRequest request) {
		return calculatePremiumService.calculatePremium(request);
	}

}