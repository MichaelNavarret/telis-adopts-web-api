package com.api.telisadoptproyect.api.controller;

import com.api.telisadoptproyect.api.request.SpecieRequests.SpecieCreateRequest;
import com.api.telisadoptproyect.api.response.SpecieResponses.SpecieSingletonResponse;
import com.api.telisadoptproyect.api.service.SpecieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/species")
public class SpecieController {
    @Autowired
    private SpecieService specieService;

    @PostMapping("")
    public ResponseEntity<SpecieSingletonResponse> createSpecie(
            @RequestBody SpecieCreateRequest request){
        return ResponseEntity
                .ok()
                .body(specieService.createSpecie(request));
    }
}
