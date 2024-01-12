package com.api.telisadoptproyect.api.request.SpecieRequests;

import org.springframework.web.multipart.MultipartFile;

public class SpecieCreateRequest {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
