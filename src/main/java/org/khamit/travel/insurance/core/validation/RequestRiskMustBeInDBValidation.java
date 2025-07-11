package org.khamit.travel.insurance.core.validation;

import org.khamit.travel.insurance.core.domain.RiskType;
import org.khamit.travel.insurance.core.repository.RiskTypeRepository;
import org.khamit.travel.insurance.dto.v2.TravelCalculatePremiumRequestV2;
import org.khamit.travel.insurance.dto.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class RequestRiskMustBeInDBValidation implements RequestValidation {
    @Autowired
    RiskTypeRepository repository;

    @Override
    public Optional<ValidationError> validateField(TravelCalculatePremiumRequestV2 request) {
        StringBuilder risksNotInDB = new StringBuilder("Следующие риски не существуют:");
        Set<String> riskTypes = new HashSet<>(repository.findAll()).stream().map(RiskType::getTitle).collect(Collectors.toSet());
        int length1 = risksNotInDB.length();
        if(request.getSelectedRisks()!=null && request.getSelectedRisks().size()>0){
            request.getSelectedRisks().forEach(e->{
                if (!riskTypes.contains(e)) risksNotInDB.append(" ").append(e).append(",");
            });
        }
        if(risksNotInDB.length()>length1){
            return Optional.of(new ValidationError("selectedRisks", risksNotInDB.toString()));
        }
       return Optional.empty();
    }
}
