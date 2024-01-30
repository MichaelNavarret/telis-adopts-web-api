package com.api.telisadoptproyect.api.controller;

import com.api.telisadoptproyect.api.service.BadgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/badges")
@CrossOrigin
public class BadgeController {
    @Autowired
    private BadgeService badgeService;
}
