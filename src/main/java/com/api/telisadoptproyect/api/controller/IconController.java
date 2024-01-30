package com.api.telisadoptproyect.api.controller;

import com.api.telisadoptproyect.api.response.IconResponses.IconCollectionResponse;
import com.api.telisadoptproyect.api.service.IconService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/icons")
@CrossOrigin
public class IconController {
    @Autowired
    private IconService iconService;

    @GetMapping("/{ownerId}")
    public ResponseEntity<IconCollectionResponse> getAllIcons(
            @PathVariable (name = "ownerId") String ownerId){
        return ResponseEntity.ok(iconService.getAllIcons(ownerId));
    }
}
