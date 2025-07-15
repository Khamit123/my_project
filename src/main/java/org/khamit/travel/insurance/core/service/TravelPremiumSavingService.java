package org.khamit.travel.insurance.core.service;

import org.khamit.travel.insurance.dto.v2.TravelCalculatePremiumResponseV2;

public interface TravelPremiumSavingService {

    void saveAllNeededData(TravelCalculatePremiumResponseV2 responseV2);

}
