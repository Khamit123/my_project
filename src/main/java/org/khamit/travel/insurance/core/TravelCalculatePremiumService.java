package org.khamit.travel.insurance.core;

import org.khamit.travel.insurance.rest.TravelCalculatePremiumRequest;
import org.khamit.travel.insurance.rest.TravelCalculatePremiumResponse;

import java.math.BigDecimal;
import java.util.Date;

public interface TravelCalculatePremiumService {

    public TravelCalculatePremiumResponse calculatePremium(TravelCalculatePremiumRequest request);

}
