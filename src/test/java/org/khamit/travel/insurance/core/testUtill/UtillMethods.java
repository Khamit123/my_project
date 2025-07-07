package org.khamit.travel.insurance.core.testUtill;

import org.khamit.travel.insurance.dto.PersonPremiumInfo;
import org.khamit.travel.insurance.dto.RiskPremuimInfo;
import org.khamit.travel.insurance.dto.Person;
import org.khamit.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
import org.khamit.travel.insurance.dto.v1.TravelCalculatePremiumResponseV1;
import org.khamit.travel.insurance.dto.v2.TravelCalculatePremiumRequestV2;
import org.khamit.travel.insurance.dto.v2.TravelCalculatePremiumResponseV2;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class UtillMethods {
    public static TravelCalculatePremiumRequestV1 createRequestV1(){
        TravelCalculatePremiumRequestV1 request = new TravelCalculatePremiumRequestV1();
        request.setSelectedRisks(List.of("Медицинский риск","Отмена поездки"));
        request.setCountry("Россия");
        request.setMedicalLimit(BigDecimal.ONE);
        request.setBirthday(LocalDate.of(2003,6,15));
        request.setAgreementDateFrom(LocalDate.now().plusDays(1));
        request.setAgreementDateTo(LocalDate.now().plusDays(6));
        request.setPersonFirstName("Хамит");
        request.setPersonLastName("Билялетдинов");
        return request;
    }

    public static TravelCalculatePremiumResponseV1 createResponseV1(){
        TravelCalculatePremiumResponseV1 response = new TravelCalculatePremiumResponseV1();
        response.setErrors(null);
        response.setCountry("Россия");
        response.setMedicalLimit(BigDecimal.ONE);
        response.setBirthday(LocalDate.of(2003,6,15));
        response.setAgreementDateFrom(LocalDate.now().plusDays(1));
        response.setAgreementDateTo(LocalDate.now().plusDays(6));
        response.setPersonFirstName("Хамит");
        response.setPersonLastName("Билялетдинов");
        response.setAgreementPrice(BigDecimal.valueOf(730));
        response.setRiskPremuimInfoList(List.of(new RiskPremuimInfo("Медицинский риск",BigDecimal.valueOf(720)),
                new RiskPremuimInfo("Отмена поездки",BigDecimal.valueOf(10))));
        return response;
    }

    public static TravelCalculatePremiumRequestV2 createRequestV2(){
        TravelCalculatePremiumRequestV2 request = new TravelCalculatePremiumRequestV2();
        request.setSelectedRisks(List.of("Медицинский риск","Отмена поездки"));
        request.setCountry("Россия");

        Person person = new Person("Хамит","Бил",LocalDate.of(2003,6,15),BigDecimal.ONE);
        Person person2 = new Person("Егор","Бил",LocalDate.of(1981,6,15),BigDecimal.ONE);
        List<Person> people = List.of(person,person2);
        request.setPersonList(people);
        request.setAgreementDateFrom(LocalDate.now().plusDays(1));
        request.setAgreementDateTo(LocalDate.now().plusDays(6));
        return request;
    }

    public static TravelCalculatePremiumResponseV2 createResponseV2(){
        TravelCalculatePremiumResponseV2 response = new TravelCalculatePremiumResponseV2();
        response.setErrors(null);
        response.setCountry("Россия");
        Person person = new Person("Хамит","Бил",LocalDate.of(2003,6,15),BigDecimal.ONE);
        Person person2 = new Person("Егор","Бил",LocalDate.of(1981,6,15),BigDecimal.ONE);
        List<RiskPremuimInfo> riskPremuimInfos=List.of(new RiskPremuimInfo("Медицинский риск",BigDecimal.valueOf(720)),
                new RiskPremuimInfo("Отмена поездки",BigDecimal.valueOf(10)));
        List<RiskPremuimInfo> riskPremuimInfos2=List.of(new RiskPremuimInfo("Медицинский риск",BigDecimal.valueOf(780)),
                new RiskPremuimInfo("Отмена поездки",BigDecimal.valueOf(10)));
        person.setPremiumInfo(new PersonPremiumInfo(BigDecimal.valueOf(730),riskPremuimInfos));
        person2.setPremiumInfo(new PersonPremiumInfo(BigDecimal.valueOf(790),riskPremuimInfos2));
        List<Person> people = List.of(person,person2);
        response.setPersonList(people);
        response.setAgreementDateFrom(LocalDate.now().plusDays(1));
        response.setAgreementDateTo(LocalDate.now().plusDays(6));
        response.setAgreementPrice(BigDecimal.valueOf(1520));
        return response;
    }

}
