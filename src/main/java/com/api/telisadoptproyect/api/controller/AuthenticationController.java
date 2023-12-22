package com.api.telisadoptproyect.api.controller;

import com.api.telisadoptproyect.api.request.LoginRequest;
import com.api.telisadoptproyect.api.request.OwnerRequests.OwnerLoginRequest;
import com.api.telisadoptproyect.api.response.AuthenticationResponses.AuthenticationResponse;
import com.api.telisadoptproyect.api.response.AuthenticationResponses.JwtResponse;
import com.api.telisadoptproyect.api.service.AuthenticationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthenticationController {
    private final static Logger LOGGER = LoggerFactory.getLogger(AuthenticationController.class);
    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping(value = "/generate-login-token", produces = "application/json")
    public ResponseEntity<AuthenticationResponse> generateTokenLogin(
            @RequestBody OwnerLoginRequest ownerLoginRequest) {
        LOGGER.info("Generating token for login....");
        return ResponseEntity.ok(authenticationService.generateTokenLogin(ownerLoginRequest));
    }

    @PostMapping(value = "/login", produces = "application/json")
    public ResponseEntity<JwtResponse> login(
            @RequestBody LoginRequest loginRequest) {
        LOGGER.info("Login....");
        return ResponseEntity.ok(authenticationService.login(loginRequest));
    }
}
