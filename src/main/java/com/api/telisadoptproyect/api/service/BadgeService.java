package com.api.telisadoptproyect.api.service;

import com.api.telisadoptproyect.api.request.BadgeRequests.UpdateBadgeRequest;
import com.api.telisadoptproyect.api.response.BadgeResponses.BadgeCollectionResponse;
import com.api.telisadoptproyect.api.response.BadgeResponses.BadgeSingletonResponse;
import com.api.telisadoptproyect.api.response.BaseResponse;
import com.api.telisadoptproyect.api.validation.BadgeValidation;
import com.api.telisadoptproyect.library.entity.Badge;
import com.api.telisadoptproyect.library.entity.QBadge;
import com.api.telisadoptproyect.library.repository.BadgeRepository;
import com.api.telisadoptproyect.library.util.PaginationUtils;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BadgeService {

    @Autowired
    private BadgeRepository badgeRepository;
    @Autowired
    private BadgeValidation badgeValidation;

    // ======================= GET ALL (Simple Get without pagination or params) =======================================
    public BadgeCollectionResponse getAllBadges() {
        return new BadgeCollectionResponse(badgeRepository.findAll());
    }
    // ========================================= GET COLLECTION ========================================================
    public Page<Badge> getBadgeCollection(Integer pageNumber, Integer pageLimit, String sort, String query, Boolean active){
        QBadge qBadge = QBadge.badge;
        BooleanExpression expression = qBadge.id.isNotNull();

        if(StringUtils.isNotBlank(query)){
            expression = expression.and(qBadge.code.containsIgnoreCase(query)
                    .or(qBadge.name.containsIgnoreCase(query)));
        }

        if(active != null){
            expression = expression.and(qBadge.active.eq(active));
        }

        Sort sortCriteria;
        if(StringUtils.isNotBlank(sort)){
            sortCriteria = PaginationUtils.createSortCriteria(sort);
        }else{
            sortCriteria = PaginationUtils.createSortCriteria("createdOn:DESC");
        }

        Pageable pageable = PageRequest.of(pageNumber, pageLimit, sortCriteria);
        return badgeRepository.findAll(expression, pageable);
    }
    // ========================================= GET SINGLETON ========================================================
    public BadgeSingletonResponse getBadgeSingleton(String badgeId) {
        badgeValidation.validateBadgeId(badgeId);
        Badge badge = getBadgeById(badgeId);
        return new BadgeSingletonResponse(BaseResponse.Status.SUCCESS, HttpStatus.OK.value(), badge );
    }
    // =========================================== UPDATE ===== ========================================================
    public BadgeSingletonResponse updateBadge (String badgeId, UpdateBadgeRequest request){
        badgeValidation.validateBadgeId(badgeId);
        Badge badge = getBadgeById(badgeId);

        updateBadgeName(badge, request.getName());
        updateBadgeCode(badge, request.getCode());
        updateBadgeDescription(badge, request.getDescription());
        updateBadgeStatus(badge, request.getActive());

        return new BadgeSingletonResponse(BaseResponse.Status.SUCCESS, HttpStatus.OK.value(), badgeRepository.save(badge));
    }
    private void updateBadgeName (Badge badge, String badgeName){
        if (StringUtils.isNotBlank(badgeName)){
            if (!badgeName.equals(badge.getName())){
                badgeValidation.validateBadgeName(badgeName, "update");
                badge.setName(badgeName);
            }
        }
    }
    private void updateBadgeCode (Badge badge, String badgeCode){
        if (StringUtils.isNotBlank(badgeCode)){
            if (!badgeCode.equals(badge.getCode())){
                badgeValidation.validateBadgeCode(badgeCode, "update");
                badge.setCode(badgeCode);
            }
        }
    }
    private void updateBadgeDescription (Badge badge, String description){
        if(StringUtils.isNotBlank(description)){
            badge.setDescription(description);
        }else{
            badge.setDescription(null);
        }
    }
    private void updateBadgeStatus (Badge badge, Boolean active){
        if(active != null){
            badge.setActive(active);
        }
    }
    // ====================================== PUBLIC UTILS METHODS  ====================================================
    public Badge getBadgeById(String badgeId) {
        return badgeRepository.findById(badgeId).orElseThrow(() -> new RuntimeException("Badge not found"));
    }

}
