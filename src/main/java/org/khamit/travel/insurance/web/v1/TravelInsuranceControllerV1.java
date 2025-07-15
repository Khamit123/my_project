package org.khamit.travel.insurance.web.v1;


import lombok.RequiredArgsConstructor;
import org.khamit.travel.insurance.core.service.TravelCalculatePremiumService;
import org.khamit.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
import org.khamit.travel.insurance.dto.v1.TravelCalculatePremiumResponseV1;
import org.khamit.travel.insurance.dto.v2.TravelCalculatePremiumRequestV2;
import org.khamit.travel.insurance.dto.v2.TravelCalculatePremiumResponseV2;
import org.khamit.travel.insurance.rest.v1.RequestResponseConverter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class TravelInsuranceControllerV1 {

    private final TravelCalculatePremiumService service;
    private  final RequestResponseConverter converter;



    @GetMapping("/insurance/travel/web/v1")
    public String showForm() {
        return "travel-calculate-premium";
    }

    @PostMapping("/insurance/travel/web/v1")
    public @ResponseBody TravelCalculatePremiumResponseV1 processForm(@RequestBody TravelCalculatePremiumRequestV1 requestV1) {
        TravelCalculatePremiumRequestV2 requestV2= converter.requestConvertFromV1ToV2(requestV1);
        TravelCalculatePremiumResponseV2 responseV2 =service.calculatePremium(requestV2);
        TravelCalculatePremiumResponseV1 responseV1 = converter.responseConvertFromV2ToV1(responseV2);
        return responseV1;
    }

}
