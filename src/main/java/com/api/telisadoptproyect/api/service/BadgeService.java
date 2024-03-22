package com.api.telisadoptproyect.api.service;

import com.api.telisadoptproyect.api.response.BadgeResponses.BadgeCollectionResponse;
import com.api.telisadoptproyect.library.entity.Badge;
import com.api.telisadoptproyect.library.repository.BadgeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BadgeService {

    @Autowired
    private BadgeRepository badgeRepository;

    public BadgeCollectionResponse getAllBadges() {
        return new BadgeCollectionResponse(badgeRepository.findAll());
    }

    public List<Badge> getBadgesByIds(List<String> badgeIds) {
        return badgeRepository.findAllById(badgeIds);
    }
}
