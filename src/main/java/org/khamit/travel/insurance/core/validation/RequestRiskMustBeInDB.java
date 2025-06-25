package org.khamit.travel.insurance.core.validation;

import org.khamit.travel.insurance.core.domain.RiskType;
import org.khamit.travel.insurance.core.repository.RiskTypeRepository;
import org.khamit.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.khamit.travel.insurance.dto.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Optional;
@Component
public class RequestRiskMustBeInDB implements RequestValidation {
    @Autowired
    RiskTypeRepository repository;

    @Override
    public Optional<ValidationError> validateField(TravelCalculatePremiumRequest request) {
        StringBuilder risksNotInDB = new StringBuilder("Следующие риски не существуют:");
        HashSet<String> riskTypes = new HashSet<>(repository.findAllTitles());
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
