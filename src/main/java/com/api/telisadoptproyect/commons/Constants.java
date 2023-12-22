package com.api.telisadoptproyect.commons;

public class Constants {
    public static final String PASS_REGEX = "^(?=(?:.*[A-Z]))(?=(?:.*[a-z]))(?=(?:.*\\d))(?=(?:.*[!@#$%^&*()\\-_=+{};:,<.>]))([A-Za-z0-9!@#$%^&*()\\-_=+{};:,<.>]{8,20})$";
    public static final String EMAIL_REGEX = "^(([^<>()[\\\\]\\\\.,;:\\s@\\\"]+(\\.[^<>()[\\\\]\\\\.,;:\\s@\\\"]+)*)|(\\\".+\\\"))@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
}
