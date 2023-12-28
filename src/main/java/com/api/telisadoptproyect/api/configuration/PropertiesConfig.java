package com.api.telisadoptproyect.api.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PropertiesConfig {
    @Value("${app.sendgrid.key}")
    private String sendgridKey;
    @Value("${jwt.secret}")
    private String jwtSecret;
    @Value("${app.sendgrid.test-mode}")
    private boolean sendgridTestModeActive;
    @Value("${app.sendgrid.sender-email}")
    private String sendgridSenderEmail;
    @Value("${app.base-url}")
    private String applicationBaseUrl;

    public String getSendgridKey() {
        return sendgridKey;
    }

    public String getJwtSecret() {
        return jwtSecret;
    }

    public boolean isSendgridTestModeActive() {
        return sendgridTestModeActive;
    }

    public String getSendgridSenderEmail() {
        return sendgridSenderEmail;
    }

    public String getApplicationBaseUrl() {
        return applicationBaseUrl;
    }
}
