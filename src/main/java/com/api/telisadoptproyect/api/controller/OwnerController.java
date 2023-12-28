package com.api.telisadoptproyect.api.controller;

import com.api.telisadoptproyect.api.request.OwnerRequests.OwnerCreateRequest;
import com.api.telisadoptproyect.api.request.OwnerRequests.OwnerRequest;
import com.api.telisadoptproyect.api.response.BaseResponse;
import com.api.telisadoptproyect.api.response.OwnerResponses.OwnerSingletonResponse;
import com.api.telisadoptproyect.api.service.OwnerService;
import com.api.telisadoptproyect.library.entity.Owner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/owners")
@CrossOrigin
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

    @GetMapping(value = "/me", produces = "application/json")
    public ResponseEntity<OwnerSingletonResponse> getMyProfile(){
        Owner owner = ownerService.getMyProfile();
        return ResponseEntity
                .ok()
                .body(new OwnerSingletonResponse(BaseResponse.Status.SUCCESS, HttpStatus.OK.value(), owner));
    }
}
