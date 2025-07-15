package org.khamit.travel.insurance.web.v2;


import lombok.RequiredArgsConstructor;
import org.khamit.travel.insurance.core.service.TravelCalculatePremiumService;
import org.khamit.travel.insurance.dto.v2.TravelCalculatePremiumRequestV2;
import org.khamit.travel.insurance.dto.v2.TravelCalculatePremiumResponseV2;
import org.khamit.travel.insurance.rest.v1.RequestResponseConverter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class TravelInsuranceControllerV2 {

    private final TravelCalculatePremiumService service;
    private  final RequestResponseConverter converter;



    @GetMapping("/insurance/travel/web/v2")
    public String showForm() {
        return "web-v2";
    }

    @PostMapping("/insurance/travel/web/v2")
    public @ResponseBody TravelCalculatePremiumResponseV2 processForm(@RequestBody TravelCalculatePremiumRequestV2 requestV2) {
        TravelCalculatePremiumResponseV2 responseV2 =service.calculatePremium(requestV2);
        return responseV2;
    }

}
