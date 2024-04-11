package com.api.telisadoptproyect.api.service;

import com.api.telisadoptproyect.api.response.BadgeResponses.BadgeCollectionResponse;
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
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BadgeService {

    @Autowired
    private BadgeRepository badgeRepository;

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


    // ====================================== PUBLIC UTILS METHODS  ====================================================
    public Badge getBadgeById(String badgeId) {
        return badgeRepository.findById(badgeId).orElseThrow(() -> new RuntimeException("Badge not found"));
    }
}
