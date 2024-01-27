package com.api.telisadoptproyect.api.request.SpecieRequests;

import java.util.List;

public class SpecieUpdateRequest {
    private String name;
    private String story;
    public SpecieUpdateRequest() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStory() {
        return story;
    }

    public void setStory(String story) {
        this.story = story;
    }
}
