package org.khamit.travel.insurance.core;

import org.khamit.travel.insurance.dto.v2.TravelCalculatePremiumRequestV2;
import org.khamit.travel.insurance.dto.v2.TravelCalculatePremiumResponseV2;

public interface TravelCalculatePremiumService {

    public TravelCalculatePremiumResponseV2 calculatePremium(TravelCalculatePremiumRequestV2 request);

}
