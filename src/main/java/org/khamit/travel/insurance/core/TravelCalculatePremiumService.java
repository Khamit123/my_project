package org.khamit.travel.insurance.core;

import org.khamit.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.khamit.travel.insurance.dto.TravelCalculatePremiumResponse;

public interface TravelCalculatePremiumService {

    public TravelCalculatePremiumResponse calculatePremium(TravelCalculatePremiumRequest request);

}
