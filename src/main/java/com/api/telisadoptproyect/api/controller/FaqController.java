package com.api.telisadoptproyect.api.controller;

import com.api.telisadoptproyect.api.request.FaqRequests.FaqCreateRequest;
import com.api.telisadoptproyect.api.request.FaqRequests.FaqUpdateRequest;
import com.api.telisadoptproyect.api.response.FaqResponses.FaqCollectionResponse;
import com.api.telisadoptproyect.api.response.FaqResponses.FaqSingletonResponse;
import com.api.telisadoptproyect.api.service.FaqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/faqs")
@CrossOrigin
public class FaqController {
    @Autowired
    private FaqService faqService;

    @GetMapping("")
    @PreAuthorize("hasPermission(#null, {'can-read-species', 'can-write-species'})")
    public ResponseEntity<FaqCollectionResponse> getAllFaqsBySpecie(
            @RequestParam(value ="specieId") final String specieId){
        return ResponseEntity.ok(faqService.getAllFaqsBySpecie(specieId));
    }

    @PostMapping
    @PreAuthorize("hasPermission(#null, {'can-write-species'})")
    public ResponseEntity<FaqSingletonResponse> createFaq(
            @RequestBody final FaqCreateRequest faqCreateRequest){
        return ResponseEntity.ok(faqService.createFaq(faqCreateRequest));
    }

    @PutMapping("/{faqId}")
    @PreAuthorize("hasPermission(#null, {'can-write-species'})")
    public ResponseEntity<FaqSingletonResponse> updateFaq(
            @PathVariable(value = "faqId") final String faqId,
            @RequestParam(value ="specieId") final String specieId,
            @RequestBody final FaqUpdateRequest faqUpdateRequest){
        return ResponseEntity.ok(faqService.updateFaq(specieId, faqId, faqUpdateRequest));
    }

    @DeleteMapping("/{faqId}")
    @PreAuthorize("hasPermission(#null, {'can-write-species'})")
    public ResponseEntity<FaqSingletonResponse> deleteFaq(
            @PathVariable(value = "faqId") final String faqId,
            @RequestParam(value ="specieId") final String specieId){
        return ResponseEntity.ok(faqService.deleteFaq(specieId, faqId));
    }
}
