package com.api.telisadoptproyect.api.controller;

import com.api.telisadoptproyect.api.request.SpecieRequests.SpecieCreateRequest;
import com.api.telisadoptproyect.api.request.SpecieRequests.SpecieUpdateRequest;
import com.api.telisadoptproyect.api.response.SpecieResponses.SpecieCollectionResponse;
import com.api.telisadoptproyect.api.response.SpecieResponses.SpecieSingletonResponse;
import com.api.telisadoptproyect.api.service.SpecieService;
import com.api.telisadoptproyect.library.entity.Specie;
import com.api.telisadoptproyect.library.util.PaginationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/species")
@CrossOrigin
public class SpecieController {
    @Autowired
    private SpecieService specieService;

    @GetMapping("/autocomplete")
    public ResponseEntity<SpecieCollectionResponse> getSpeciesAutocomplete(){
        return ResponseEntity
                .ok()
                .body(specieService.getSpecieCollectionAutocomplete());
    }

    @GetMapping("")
    public ResponseEntity<SpecieCollectionResponse> getSpecies(
            @RequestHeader(name = PaginationUtils.X_PAGINATION_NUM, required = false, defaultValue = PaginationUtils.DEFAULT_PAGINATION_NUM) String pageNumber,
            @RequestHeader(name = PaginationUtils.X_PAGINATION_LIMIT, required = false, defaultValue = PaginationUtils.DEFAULT_PAGINATION_LIMIT) String pageLimit){

        final Integer pageNumberValue = Integer.parseInt(pageNumber);
        final Integer pageLimitValue = Integer.parseInt(pageLimit);
        final Page<Specie> response = specieService.getSpecieCollection(pageNumberValue, pageLimitValue);

        HttpHeaders headers = PaginationUtils.createHttpHeaderForPagination(response, pageLimitValue);
        SpecieCollectionResponse specieCollectionResponse = new SpecieCollectionResponse(response);
        return ResponseEntity
                .ok()
                .headers(headers)
                .body(specieCollectionResponse);
    }

    @GetMapping("/{specieId}")
    public ResponseEntity<SpecieSingletonResponse> getSpecie(
            @PathVariable(name = "specieId") String specieId){
        return ResponseEntity
                .ok()
                .body(specieService.getSpecie(specieId));
    }

    @PostMapping("")
    public ResponseEntity<SpecieSingletonResponse> createSpecie(
            @RequestParam(name = "specieName", required = false)String specieName,
            @RequestParam(name = "file", required = false)MultipartFile inputFile,
            @RequestParam(name = "file2", required = false)MultipartFile inputFile2){
        return ResponseEntity
                .ok()
                .body(specieService.createSpecie(inputFile, inputFile2, specieName));
    }

    @PutMapping("/{specieId}")
    public ResponseEntity<SpecieSingletonResponse> updateSpecie(
            @PathVariable(name = "specieId") String specieId,
            @RequestBody SpecieUpdateRequest request){
        return ResponseEntity
                .ok()
                .body(specieService.updateSpecie(specieId, request));
    }
}
