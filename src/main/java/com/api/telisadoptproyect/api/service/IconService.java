package com.api.telisadoptproyect.api.service;

import com.api.telisadoptproyect.api.response.BaseResponse;
import com.api.telisadoptproyect.api.response.IconResponses.IconCollectionResponse;
import com.api.telisadoptproyect.library.entity.Icon;
import com.api.telisadoptproyect.library.entity.Owner;
import com.api.telisadoptproyect.library.exception.BadRequestException;
import com.api.telisadoptproyect.library.repository.IconRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class IconService {
    @Autowired
    private IconRepository iconRepository;
    @Autowired
    private OwnerService ownerService;

    public IconCollectionResponse getAllIcons(String ownerId) {
        if (StringUtils.isBlank(ownerId)) throw new BadRequestException("Owner id is required");
        Owner owner = ownerService.getOwnerById(ownerId);
        List<Icon> iconsList = iconRepository.findAll().stream().map(icon -> {
            if (icon.isExclusive()) {
                if (icon.getAvailableFor().contains(owner)) {
                    return icon;
                }
            } else {
                return icon;
            }
            return null;
        }).collect(Collectors.toList());

        iconsList.removeIf(Objects::isNull);

        return new IconCollectionResponse(BaseResponse.Status.SUCCESS,HttpStatus.OK.value(),iconsList);
    }
}
