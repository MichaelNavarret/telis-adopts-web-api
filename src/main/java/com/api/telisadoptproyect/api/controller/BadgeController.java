package com.api.telisadoptproyect.api.controller;

import com.api.telisadoptproyect.api.request.BadgeRequests.CreateBadgeRequest;
import com.api.telisadoptproyect.api.request.BadgeRequests.UpdateBadgeRequest;
import com.api.telisadoptproyect.api.response.BadgeResponses.BadgeCollectionResponse;
import com.api.telisadoptproyect.api.response.BadgeResponses.BadgeSingletonResponse;
import com.api.telisadoptproyect.api.service.BadgeService;
import com.api.telisadoptproyect.library.entity.Badge;
import com.api.telisadoptproyect.library.util.MessageUtils;
import com.api.telisadoptproyect.library.util.PaginationUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/badges")
@CrossOrigin
public class BadgeController {
    @Autowired
    private BadgeService badgeService;
    private static final Logger LOGGER = LoggerFactory.getLogger(MessageUtils.class);

    @GetMapping(value = "/all", produces = "application/json")
    public ResponseEntity<BadgeCollectionResponse> getAllBadges(){
        return ResponseEntity.ok(badgeService.getAllBadges());
    }

    @GetMapping(value = "", produces =  "application/json")
    public ResponseEntity<BadgeCollectionResponse> getBadgeCollection(
            @RequestHeader(name = PaginationUtils.X_PAGINATION_NUM, required = false, defaultValue = PaginationUtils.DEFAULT_PAGINATION_NUM) String pageNumber,
            @RequestHeader(name = PaginationUtils.X_PAGINATION_LIMIT, required = false, defaultValue = PaginationUtils.DEFAULT_PAGINATION_LIMIT) String pageLimit,
            @RequestParam(name = "sort", required = false) String sort,
            @RequestParam(name = "q", required = false) String query,
            @RequestParam(name = "active", required = false) Boolean active){


        LOGGER.info("Getting badges collection...");
        LOGGER.info("Sort: " + sort);
        LOGGER.info("Query: " + query);
        LOGGER.info("active: " + active);

        final Integer pageNumberValue = Integer.parseInt(pageNumber);
        final Integer pageLimitValue = Integer.parseInt(pageLimit);

        final Page<Badge> response = badgeService.getBadgeCollection(pageNumberValue, pageLimitValue, sort, query, active);

        HttpHeaders headers = PaginationUtils.createHttpHeaderForPagination(response, pageLimitValue);
        BadgeCollectionResponse badgeCollectionResponse = new BadgeCollectionResponse(response);

        return ResponseEntity
                .ok()
                .headers(headers)
                .body(badgeCollectionResponse);
    }

    @GetMapping(value = "/{badgeId}", produces = "application/json")
    public ResponseEntity<BadgeSingletonResponse> getBadgeSingleton(
            @PathVariable(name ="badgeId") String badgeId){

        LOGGER.info("Getting badge with id " + badgeId + "...");
        return ResponseEntity
                .ok()
                .body(badgeService.getBadgeSingleton(badgeId));
    }

    @PutMapping(value = "/{badgeId}", produces = "application/json")
    public ResponseEntity<BadgeSingletonResponse> updateBadge(
            @PathVariable(name ="badgeId") String badgeId,
            @RequestBody UpdateBadgeRequest request){

        LOGGER.info("Updating badge with id " + badgeId + "...");
        LOGGER.info("Request: " + request.toString());

        return ResponseEntity
                .ok()
                .body(badgeService.updateBadge(badgeId, request));
    }

    @PutMapping(value = "/{badgeId}/badgeImage", produces = "application/json")
    public ResponseEntity<BadgeSingletonResponse> uploadBadgeImage(
            @PathVariable(name = "badgeId") String badgeId,
            @RequestParam (name = "file") MultipartFile badgeImage){

        LOGGER.info("Uploading badgeImage to badge with id: " + badgeId);

        return ResponseEntity
                .ok()
                .body(badgeService.uploadBadgeImage(badgeId, badgeImage));
    }

    @PostMapping(value = "", produces = "application/json")
    public ResponseEntity<BadgeSingletonResponse> createBadge(
            @RequestBody CreateBadgeRequest request){

        LOGGER.info("Creating new badge...");

        return ResponseEntity
                .ok()
                .body(badgeService.createBadge(request));
    }

}
