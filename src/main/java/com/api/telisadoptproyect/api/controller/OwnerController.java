package com.api.telisadoptproyect.api.controller;

import com.api.telisadoptproyect.api.request.OwnerRequests.OwnerAddCharacterFavoriteRequest;
import com.api.telisadoptproyect.api.request.OwnerRequests.OwnerCreateRequest;
import com.api.telisadoptproyect.api.request.OwnerRequests.OwnerRequest;
import com.api.telisadoptproyect.api.request.OwnerRequests.OwnerUpdateRequest;
import com.api.telisadoptproyect.api.response.BaseResponse;
import com.api.telisadoptproyect.api.response.OwnerResponses.OwnerCollectionResponse;
import com.api.telisadoptproyect.api.response.OwnerResponses.OwnerSingletonResponse;
import com.api.telisadoptproyect.api.service.OwnerService;
import com.api.telisadoptproyect.library.entity.Owner;
import com.api.telisadoptproyect.library.util.PaginationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/owners")
@CrossOrigin
public class OwnerController {
    @Autowired
    private OwnerService ownerService;

    @GetMapping(value = "/autocomplete", produces = "application/json")
    @PreAuthorize("hasPermission(#null, {'can-read-owners', 'can-write-owners'})")
    public ResponseEntity<OwnerCollectionResponse> getOwnersAutocomplete(){
        return ResponseEntity
                .ok()
                .body(ownerService.getOwnerCollectionAutocomplete());
    }
    @PostMapping(value = "", produces = "application/json")
    @PreAuthorize("hasPermission(#null, {'can-write-owners'})")
    public ResponseEntity<OwnerSingletonResponse> createOwner(
            @RequestBody OwnerCreateRequest request){
        return ResponseEntity
                .ok()
                .body(ownerService.createOwner(request));
    }

    @GetMapping(value = "/me", produces = "application/json")
    @PreAuthorize("hasPermission(#null, {'can-read-owners', 'can-write-owners'})")
    public ResponseEntity<OwnerSingletonResponse> getMyProfile(){
        Owner owner = ownerService.getMyProfile();
        return ResponseEntity
                .ok()
                .body(new OwnerSingletonResponse(BaseResponse.Status.SUCCESS, HttpStatus.OK.value(), owner));
    }

    @GetMapping("")
    @PreAuthorize("hasPermission(#null, {'can-read-owners', 'can-write-owners'})")
    public ResponseEntity<OwnerCollectionResponse> getOwners(
            @RequestHeader(name = PaginationUtils.X_PAGINATION_NUM, required = false, defaultValue = PaginationUtils.DEFAULT_PAGINATION_NUM) String pageNumber,
            @RequestHeader(name = PaginationUtils.X_PAGINATION_LIMIT, required = false, defaultValue = PaginationUtils.DEFAULT_PAGINATION_LIMIT) String pageLimit,
            @RequestParam(name = "nickName", required = false) String nickName,
            @RequestParam(name = "email", required = false) String email ){

        final Integer pageNumberValue = Integer.parseInt(pageNumber);
        final Integer pageLimitValue = Integer.parseInt(pageLimit);
        final Page<Owner> response = ownerService.getOwnersCollection(pageNumberValue, pageLimitValue, nickName, email);

        HttpHeaders headers = PaginationUtils.createHttpHeaderForPagination(response, pageLimitValue);
        OwnerCollectionResponse ownerCollectionResponse = new OwnerCollectionResponse(response);

        return ResponseEntity
                .ok()
                .headers(headers)
                .body(ownerCollectionResponse);
    }

    @GetMapping(value = "/{ownerId}", produces = "application/json")
    @PreAuthorize("hasPermission(#null, {'can-read-owners', 'can-write-owners'})")
    public ResponseEntity<OwnerSingletonResponse> getOwnerById(
            @PathVariable(name = "ownerId") String ownerId){
        return ResponseEntity
                .ok()
                .body(ownerService.getOwnerSingleton(ownerId));
    }

    @PutMapping(value = "/{ownerId}", produces = "application/json")
    @PreAuthorize("hasPermission(#null, {'can-write-owners'})")
    public ResponseEntity<OwnerSingletonResponse> updateOwner(
            @PathVariable(name = "ownerId") String ownerId,
            @RequestBody OwnerUpdateRequest request){
        return ResponseEntity
                .ok()
                .body(ownerService.updateOwner(ownerId, request));
    }

    @PutMapping(value = "/{ownerId}/favorite-character", produces = "application/json")
    @PreAuthorize("hasPermission(#null, {'can-write-owners'})")
    public ResponseEntity<OwnerSingletonResponse> addFavoriteCharacter(
            @PathVariable(name = "ownerId") String ownerId,
            @RequestBody OwnerAddCharacterFavoriteRequest request){
        return ResponseEntity
                .ok()
                .body(ownerService.addFavoriteCharacter(ownerId, request));
    }
}
