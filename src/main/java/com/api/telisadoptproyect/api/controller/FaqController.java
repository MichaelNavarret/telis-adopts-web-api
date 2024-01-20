package com.api.telisadoptproyect.api.controller;

import com.api.telisadoptproyect.api.response.FaqResponses.FaqCollectionResponse;
import com.api.telisadoptproyect.api.service.FaqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/faqs")
@CrossOrigin
public class FaqController {
    @Autowired
    private FaqService faqService;

    @GetMapping("")
    public ResponseEntity<FaqCollectionResponse> getAllFaqsBySpecie(
            @RequestParam(value ="specieId") final String specieId){
        return ResponseEntity.ok(faqService.getAllFaqsBySpecie(specieId));
    }
}
