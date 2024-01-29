package com.api.telisadoptproyect.api.controller;

import com.api.telisadoptproyect.api.response.SpecieForm.SpecieFormSingletonResponse;
import com.api.telisadoptproyect.api.service.SpecieFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/species-form")
@CrossOrigin
public class SpecieFormController {
    @Autowired
    private SpecieFormService specieFormService;

    @GetMapping("/{specieFormId}")
    @PreAuthorize("hasPermission(#null, {'can-read-species', 'can-write-species'})")
    public ResponseEntity<SpecieFormSingletonResponse> getSpecieFormSingletonInfo(
            @PathVariable(name = "specieFormId") String specieFormId){
        return ResponseEntity
                .ok()
                .body(specieFormService.getSpecieFormSingletonInfo(specieFormId));
    }
}
