package com.api.telisadoptproyect.api.validation;

import com.api.telisadoptproyect.api.request.BadgeRequests.UpdateBadgeRequest;
import com.api.telisadoptproyect.library.entity.Badge;
import com.api.telisadoptproyect.library.exception.BadRequestException;
import com.api.telisadoptproyect.library.repository.BadgeRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class BadgeValidation {
    @Autowired
    BadgeRepository badgeRepository;
    public void validateBadgeId(String badgeId){
        if (StringUtils.isBlank(badgeId)) throw new BadRequestException("The badgeId cannot be null or empty.");
    }
    public void validateBadgeName(String badgeName, String processType){
        if (processType.equals("create")){
            if(StringUtils.isBlank(badgeName)) throw new BadRequestException("The Badge Name cannot be null or empty");
        }
        Optional<Badge> badge = badgeRepository.findByName(badgeName);
        if(badge.isPresent()) throw new BadRequestException("The badge with name " + badgeName + " already exist");
    }
    public void validateBadgeCode(String badgeCode, String processType){
        if(processType.equals("create")){
            if(StringUtils.isBlank(badgeCode)) throw new BadRequestException("The Badge Code cannot be null or empty");
        }
        Optional<Badge> badge = badgeRepository.findByCode(badgeCode);
        if(badge.isPresent()) throw new BadRequestException("The badge with code " + badgeCode + " already exist");
    }

}
