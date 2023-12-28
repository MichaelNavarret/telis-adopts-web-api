package com.api.telisadoptproyect.library.entity;

import com.api.telisadoptproyect.library.util.KeyValueEnumHelper;

public enum EmailType {

    GENERATE_TOKEN_EMAIL("TOKEN", "email-template"),
    RESET_PASS_EMAIL("RESET", "forgot-password"),
    INVITATION_LINK_EMAIL("INVITATION", "invitation-template"),
    CONFIRMATION_EMAIL("CONFIRMATION", "confirmation-template");

    private final String key;
    private final String value;

    EmailType(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public static final KeyValueEnumHelper<EmailType, String, String> HELPER = KeyValueEnumHelper
            .adapt(EmailType::key, EmailType::value, EmailType.values());

    public String key() {
        return this.key;
    }

    public String value() {
        return this.value;
    }
}