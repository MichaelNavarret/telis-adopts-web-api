package com.api.telisadoptproyect.api.controller;

import com.api.telisadoptproyect.api.request.FaqRequests.FaqCreateRequest;
import com.api.telisadoptproyect.api.request.FaqRequests.FaqUpdateRequest;
import com.api.telisadoptproyect.api.response.FaqResponses.FaqCollectionResponse;
import com.api.telisadoptproyect.api.response.FaqResponses.FaqSingletonResponse;
import com.api.telisadoptproyect.api.service.FaqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public ResponseEntity<FaqSingletonResponse> createFaq(
            @RequestParam(value ="specieId") final String specieId,
            @RequestBody final FaqCreateRequest faqCreateRequest){
        return ResponseEntity.ok(faqService.createFaq(specieId, faqCreateRequest));
    }

    @PatchMapping("/{faqId}")
    public ResponseEntity<FaqSingletonResponse> updateFaq(
            @PathVariable(value = "faqId") final String faqId,
            @RequestParam(value ="specieId") final String specieId,
            @RequestBody final FaqUpdateRequest faqUpdateRequest){
        return ResponseEntity.ok(faqService.updateFaq(specieId, faqId, faqUpdateRequest));
    }

    @DeleteMapping("/{faqId}")
    public ResponseEntity<FaqSingletonResponse> deleteFaq(
            @PathVariable(value = "faqId") final String faqId,
            @RequestParam(value ="specieId") final String specieId){
        return ResponseEntity.ok(faqService.deleteFaq(specieId, faqId));
    }
}
