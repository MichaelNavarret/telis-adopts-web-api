package com.api.telisadoptproyect.api.controller;

import com.api.telisadoptproyect.api.request.OwnerRequests.OwnerCreateRequest;
import com.api.telisadoptproyect.api.response.OwnerResponses.OwnerSingletonResponse;
import com.api.telisadoptproyect.api.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/owners")
public class OwnerController {
    @Autowired
    private OwnerService ownerService;

    @PostMapping(value = "", produces = "application/json")
    public ResponseEntity<OwnerSingletonResponse> createOwner(
            @RequestBody OwnerCreateRequest request){
        return ResponseEntity
                .ok()
                .body(ownerService.createOwner(request));
    }
}
