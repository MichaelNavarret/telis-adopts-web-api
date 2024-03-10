package com.api.telisadoptproyect.api.controller;

import com.api.telisadoptproyect.api.response.BadgeResponses.BadgeCollectionResponse;
import com.api.telisadoptproyect.api.service.BadgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/badges")
@CrossOrigin
public class BadgeController {
    @Autowired
    private BadgeService badgeService;

    @GetMapping(value = "", produces = "application/json")
    public ResponseEntity<BadgeCollectionResponse> getAllBadges(){
        return ResponseEntity.ok(badgeService.getAllBadges());
    }
}
