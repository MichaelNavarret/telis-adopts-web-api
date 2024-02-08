package com.api.telisadoptproyect.api.controller;

import com.api.telisadoptproyect.api.request.AdoptRequests.AdoptCreateRequest;
import com.api.telisadoptproyect.api.response.AdoptResponses.AdoptCollectionResponse;
import com.api.telisadoptproyect.api.response.AdoptResponses.AdoptSingletonResponse;
import com.api.telisadoptproyect.api.service.AdoptService;
import com.api.telisadoptproyect.library.entity.Adopt;
import com.api.telisadoptproyect.library.util.PaginationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/adopts")
@CrossOrigin
public class AdoptController {
    @Autowired
    private AdoptService adoptService;

    @PostMapping("")
    @PreAuthorize("hasPermission(#null, {'can-write-adopts'})")
    public ResponseEntity<AdoptSingletonResponse> createAdopt(
            @RequestBody AdoptCreateRequest request){
        return ResponseEntity
                .ok()
                .body(adoptService.createAdopt(request));
    }

    @PutMapping("/{adoptId}/icon")
    @PreAuthorize("hasPermission(#null, {'can-write-adopts'})")
    public ResponseEntity<AdoptSingletonResponse> uploadIconToAdopt(
            @PathVariable(name = "adoptId") String adoptId,
            @RequestParam (name = "file") MultipartFile adoptIcon){
        return ResponseEntity
                .ok()
                .body(adoptService.uploadIconToAdopt(adoptId, adoptIcon));
    }

    @GetMapping("")
    @PreAuthorize("hasPermission(#null, {'can-read-adopts' , 'can-write-adopts'})")
    public ResponseEntity<AdoptCollectionResponse> getAdopts(
            @RequestHeader(name = PaginationUtils.X_PAGINATION_NUM, required = false, defaultValue = PaginationUtils.DEFAULT_PAGINATION_NUM) String pageNumber,
            @RequestHeader(name = PaginationUtils.X_PAGINATION_LIMIT, required = false, defaultValue = PaginationUtils.DEFAULT_PAGINATION_LIMIT) String pageLimit,
            @RequestParam(name ="specieId", required = false) String specieId,
            @RequestParam(name ="creationType", required = false) String creationType,
            @RequestParam(name = "sort", required = false) String sort,
            @RequestParam(name = "ownerId", required = false) String ownerId) {


        final Integer pageNumberValue = Integer.parseInt(pageNumber);
        final Integer pageLimitValue = Integer.parseInt(pageLimit);
        final Page<Adopt> response = adoptService.getAdoptCollection(pageNumberValue, pageLimitValue, specieId, creationType, sort, ownerId);

        HttpHeaders headers = PaginationUtils.createHttpHeaderForPagination(response, pageLimitValue);
        AdoptCollectionResponse adoptCollectionResponse = new AdoptCollectionResponse(response);
        return ResponseEntity
                .ok()
                .headers(headers)
                .body(adoptCollectionResponse);
    }

    @GetMapping("/autocomplete")
    @PreAuthorize("hasPermission(#null, {'can-read-adopts' , 'can-write-adopts'})")
    public ResponseEntity<AdoptCollectionResponse> getAdoptsAutocomplete(
            @RequestParam(name ="specieId", required = false) String specieId,
            @RequestParam(name ="creationType", required = false) String creationType) {
        return ResponseEntity
                .ok()
                .body(adoptService.getAdoptCollectionAutocomplete(specieId, creationType));
    }

    @GetMapping("/{ownerId}/favorites")
    @PreAuthorize("hasPermission(#null, {'can-read-adopts' , 'can-write-adopts'})")
    public ResponseEntity<AdoptCollectionResponse> getFavoritesAdopts(
            @PathVariable(name = "ownerId") String ownerId,
            @RequestHeader(name = PaginationUtils.X_PAGINATION_NUM, required = false, defaultValue = PaginationUtils.DEFAULT_PAGINATION_NUM) String pageNumber,
            @RequestHeader(name = PaginationUtils.X_PAGINATION_LIMIT, required = false, defaultValue = PaginationUtils.DEFAULT_PAGINATION_LIMIT) String pageLimit) {
        final Integer pageNumberValue = Integer.parseInt(pageNumber);
        final Integer pageLimitValue = Integer.parseInt(pageLimit);
        final Page<Adopt> response = adoptService.getFavoriteAdopts(pageNumberValue, pageLimitValue, ownerId);

        HttpHeaders headers = PaginationUtils.createHttpHeaderForPagination(response, pageLimitValue);
        AdoptCollectionResponse adoptCollectionResponse = new AdoptCollectionResponse(response);
        return ResponseEntity
                .ok()
                .headers(headers)
                .body(adoptCollectionResponse);
    }
}
