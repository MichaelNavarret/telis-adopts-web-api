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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/adopts")
@CrossOrigin
public class AdoptController {
    @Autowired
    private AdoptService adoptService;

    @PostMapping("")
    public ResponseEntity<AdoptSingletonResponse> createAdopt(
            @RequestBody AdoptCreateRequest request){
        return ResponseEntity
                .ok()
                .body(adoptService.createAdopt(request));
    }

    @GetMapping("")
    public ResponseEntity<AdoptCollectionResponse> getAdopts(
            @RequestHeader(name = PaginationUtils.X_PAGINATION_NUM, required = false, defaultValue = PaginationUtils.DEFAULT_PAGINATION_NUM) String pageNumber,
            @RequestHeader(name = PaginationUtils.X_PAGINATION_LIMIT, required = false, defaultValue = PaginationUtils.DEFAULT_PAGINATION_LIMIT) String pageLimit) {

        final Integer pageNumberValue = Integer.parseInt(pageNumber);
        final Integer pageLimitValue = Integer.parseInt(pageLimit);
        final Page<Adopt> response = adoptService.getAdoptCollection(pageNumberValue, pageLimitValue);

        HttpHeaders headers = PaginationUtils.createHttpHeaderForPagination(response, pageLimitValue);
        AdoptCollectionResponse adoptCollectionResponse = new AdoptCollectionResponse(response);
        return ResponseEntity
                .ok()
                .headers(headers)
                .body(adoptCollectionResponse);
    }
}
