package com.api.telisadoptproyect.api.request.SpecieRequests;

import org.springframework.web.multipart.MultipartFile;

public class SpecieCreateRequest {
    private String name;

    private String mainSpecieId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMainSpecieId() {
        return mainSpecieId;
    }

    public void setMainSpecieId(String mainSpecieId) {
        this.mainSpecieId = mainSpecieId;
    }
}
