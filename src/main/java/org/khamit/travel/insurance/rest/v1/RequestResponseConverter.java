package org.khamit.travel.insurance.rest.v1;

import org.khamit.travel.insurance.dto.v1.Person;
import org.khamit.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
import org.khamit.travel.insurance.dto.v1.TravelCalculatePremiumResponseV1;
import org.khamit.travel.insurance.dto.v2.TravelCalculatePremiumRequestV2;
import org.khamit.travel.insurance.dto.v2.TravelCalculatePremiumResponseV2;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RequestResponseConverter {

    public TravelCalculatePremiumRequestV2 requestConvertFromV1ToV2(TravelCalculatePremiumRequestV1 requestV1){
        TravelCalculatePremiumRequestV2 requestV2 = new TravelCalculatePremiumRequestV2();
        requestV2.setCountry(requestV1.getCountry());
        requestV2.setMedicalLimit(requestV1.getMedicalLimit());
        requestV2.setAgreementDateFrom(requestV1.getAgreementDateFrom());
        requestV2.setAgreementDateTo(requestV1.getAgreementDateTo());
        requestV2.setSelectedRisks(requestV1.getSelectedRisks());
        requestV2.setPersonList(List.of(
                new Person(requestV1.getPersonFirstName(),requestV1.getPersonLastName(),requestV1.getBirthday())));
        return requestV2;
    }

    public TravelCalculatePremiumResponseV1 responseConvertFromV2ToV1(TravelCalculatePremiumResponseV2 responseV2){
        TravelCalculatePremiumResponseV1 responseV1 = new TravelCalculatePremiumResponseV1();
        if(responseV2.hasErrors()){
            return new TravelCalculatePremiumResponseV1(responseV2.getErrors());
        }
        responseV1.setCountry(responseV2.getCountry());
        responseV1.setMedicalLimit(responseV2.getMedicalLimit());
        responseV1.setAgreementDateFrom(responseV2.getAgreementDateFrom());
        responseV1.setAgreementDateTo(responseV2.getAgreementDateTo());
        responseV1.setAgreementPrice(responseV2.getAgreementPrice());
        Person person =responseV2.getPersonList().get(0);
        responseV1.setPersonFirstName(person.getPersonFirstName());
        responseV1.setPersonLastName(person.getPersonLastName());
        responseV1.setBirthday(person.getBirthday());
        responseV1.setRiskPremuimInfoList(person.getPremiumInfo().getRiskPremuimInfo());
        return  responseV1;
    }
}
