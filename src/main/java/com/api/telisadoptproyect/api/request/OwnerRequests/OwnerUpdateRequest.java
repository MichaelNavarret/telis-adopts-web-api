package com.api.telisadoptproyect.api.request.OwnerRequests;

import java.util.List;

public class OwnerUpdateRequest {
    private List<String> favoriteAdoptsIds;

    public OwnerUpdateRequest() {
    }

    public List<String> getFavoriteAdoptsIds() {
        return favoriteAdoptsIds;
    }

    public void setFavoriteAdoptsIds(List<String> favoriteAdoptsIds) {
        this.favoriteAdoptsIds = favoriteAdoptsIds;
    }
}
