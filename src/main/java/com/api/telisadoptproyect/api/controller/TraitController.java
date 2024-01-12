package com.api.telisadoptproyect.api.controller;

import com.api.telisadoptproyect.api.request.TraitRequests.TraitCreateRequest;
import com.api.telisadoptproyect.api.request.TraitRequests.TraitUpdateRequest;
import com.api.telisadoptproyect.api.response.TraitResponses.TraitCollectionResponse;
import com.api.telisadoptproyect.api.response.TraitResponses.TraitSingletonResponse;
import com.api.telisadoptproyect.api.service.TraitService;
import com.api.telisadoptproyect.library.entity.Trait;
import com.api.telisadoptproyect.library.util.PaginationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/traits")
@CrossOrigin
public class TraitController {
    @Autowired
    private TraitService traitService;


    @GetMapping("/autocomplete")
    public ResponseEntity<TraitCollectionResponse> getTraitsAutocomplete(
            @RequestParam(name = "specieId", required = false) String specieId){
        return ResponseEntity
                .ok()
                .body(traitService.getTraitCollectionAutocomplete(specieId));
    }

    @GetMapping("")
    public ResponseEntity<TraitCollectionResponse> getTraits(
            @RequestHeader(name = PaginationUtils.X_PAGINATION_NUM, required = false, defaultValue = PaginationUtils.DEFAULT_PAGINATION_NUM) String pageNumber,
            @RequestHeader(name = PaginationUtils.X_PAGINATION_LIMIT, required = false, defaultValue = PaginationUtils.DEFAULT_PAGINATION_LIMIT) String pageLimit,
            @RequestParam(name = "specieId", required = false) String specieId,
            @RequestParam(name = "rarity", required = false) String rarity){

        final Integer pageNumberValue = Integer.parseInt(pageNumber);
        final Integer pageLimitValue = Integer.parseInt(pageLimit);
        final Page<Trait> response = traitService.getTraitCollection(pageNumberValue, pageLimitValue, specieId, rarity);

        HttpHeaders headers = PaginationUtils.createHttpHeaderForPagination(response, pageLimitValue);
        TraitCollectionResponse traitCollectionResponse = new TraitCollectionResponse(response);

        return ResponseEntity
                .ok()
                .headers(headers)
                .body(traitCollectionResponse);
    }

    @GetMapping("/{traitId}")
    public ResponseEntity<TraitSingletonResponse> getTrait(
            @RequestHeader(name = "specieId") String specieId,
            @PathVariable(name = "traitId") String traitId){
        return ResponseEntity
                .ok()
                .body(traitService.getTrait(specieId, traitId));
    }

    @PostMapping("")
    public ResponseEntity<TraitSingletonResponse> createTrait(
            @RequestBody TraitCreateRequest request){
        return ResponseEntity
                .ok()
                .body(traitService.createTrait(request));
    }

    @PutMapping("/{traitId}")
    public ResponseEntity<TraitSingletonResponse> updateTrait(
            @RequestHeader(name = "specieId") String specieId,
            @PathVariable(name = "traitId") String traitId,
            @RequestBody TraitUpdateRequest request){
        return ResponseEntity
                .ok()
                .body(traitService.updateTrait(specieId, traitId, request));
    }
}
