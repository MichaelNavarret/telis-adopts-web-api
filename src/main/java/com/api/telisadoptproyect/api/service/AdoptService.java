package com.api.telisadoptproyect.api.service;

import com.api.telisadoptproyect.api.request.AdoptRequests.AdoptCreateRequest;
import com.api.telisadoptproyect.api.request.SubTraitRequests.SubTraitCreateRequest;
import com.api.telisadoptproyect.api.response.AdoptResponses.AdoptSingletonResponse;
import com.api.telisadoptproyect.api.response.BaseResponse;
import com.api.telisadoptproyect.library.entity.*;
import com.api.telisadoptproyect.library.exception.BadRequestException;
import com.api.telisadoptproyect.library.repository.AdoptRepository;
import com.api.telisadoptproyect.library.util.PaginationUtils;
import com.api.telisadoptproyect.library.validation.EnumValidation;
import jakarta.transaction.Transactional;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Set;

@Service
public class AdoptService {
    @Autowired
    private AdoptRepository adoptRepository;
    @Autowired
    private SpecieService specieService;
    @Autowired
    private SubTraitService subTraitService;
    @Autowired
    private OwnerService ownerService;

    @Transactional
    public AdoptSingletonResponse createAdopt(AdoptCreateRequest createRequest){
        if (createRequest == null) throw new BadRequestException("The request cannot be null");
        if (createRequest.getSpecieId() == null) throw new BadRequestException("The Specie cannot be null");
        if (createRequest.getCreationType() == null) throw new BadRequestException("The Creation Type cannot be null");
        if(!EnumValidation.validateEnum(Adopt.CreationType.class, createRequest.getCreationType())){
            throw new BadRequestException("The Creation Type is invalid.");
        }

        Specie specie = specieService.findById(createRequest.getSpecieId());

        Adopt adopt = new Adopt();
        adopt.setName(StringUtils.isNotBlank(createRequest.getName()) ? createRequest.getName() : "TBN");
        adopt.setCode(generateCodeToAdopt(createRequest.getCreationType()));
        adopt.setCreationType(EnumValidation.toEnum(Adopt.CreationType.class, createRequest.getCreationType()));
        adopt.setSpecie(specie);
        adopt.setSubTraits(Collections.emptySet());
        adopt.setRarity(getAdoptRarity(createRequest));

        Owner owner;
        if (StringUtils.isNotBlank(createRequest.getOwnerId())){
            if (createRequest.isNotRegisteredOwner()){
                owner = ownerService.createNotRegisteredOwner(createRequest.getOwnerId());
            }else{
                owner = ownerService.getOwnerById(createRequest.getOwnerId());
            }
            adopt.setOwner(owner);
        }
        adoptRepository.save(adopt);

        if(createRequest.getSubTraits() != null && !createRequest.getSubTraits().isEmpty()){
            createAndLinkSubTraitsToAdopt(createRequest.getSubTraits(), adopt);
        }

        return new AdoptSingletonResponse(BaseResponse.Status.SUCCESS, HttpStatus.CREATED.value(), adopt);
    }

    public Page<Adopt> getAdoptCollection(Integer pageNumber, Integer pageLimit) {
        Sort sort = PaginationUtils.createSortCriteria("code:ASC");
        Pageable pageable = PageRequest.of(pageNumber, pageLimit, sort);
        return adoptRepository.findAll(pageable);
    }

    //------------------------------------------- [PRIVATE METHODS]
    private void createAndLinkSubTraitsToAdopt(List<SubTraitCreateRequest> requests, Adopt adopt){
        Set<SubTrait> subTraits = subTraitService.createSubTraitsByAdopt(requests, adopt);
        subTraitService.saveAll(subTraits);
        adopt.setSubTraits(subTraits);
        adoptRepository.save(adopt);
    }

    private String generateCodeToAdopt(String creationType){
        Adopt.CreationType type = EnumValidation.toEnum(Adopt.CreationType.class, creationType);
        if (type == null) throw new BadRequestException("The Creation Type is invalid.");
        int numberCode = getLastNumberCode(type) + 1;
        return switch (type) {
            case PREMADE -> "PRE-" + String.format("%04d", numberCode);
            case CUSTOM -> "CUS-" + String.format("%04d", numberCode);
            case MYO -> "MYO-" + String.format("%04d", numberCode);
            case GUEST_ARTIST -> "GA-" + String.format("%04d", numberCode);
        };
    }

    private int getLastNumberCode(Adopt.CreationType type){
        Adopt adopt = adoptRepository.findFirstByCreationTypeOrderByCreatedOnDesc(type);
        if (adopt == null){
            return 0;
        }
        return switch (type) {
            case PREMADE, MYO, CUSTOM -> Integer.parseInt(adopt.getCode().substring(4));
            case GUEST_ARTIST -> Integer.parseInt(adopt.getCode().substring(3));
        };
    }

    private Trait.Rarity getAdoptRarity(AdoptCreateRequest createRequest){
        if (createRequest.getSubTraits() == null || createRequest.getSubTraits().isEmpty()){
            return Trait.Rarity.COMMON;
        }else{
            for (SubTraitCreateRequest subTrait : createRequest.getSubTraits()){
                switch (subTrait.getRarity()){
                    case "COMMON" -> {
                        return Trait.Rarity.COMMON;
                    }
                    case "UNCOMMON" -> {
                        return Trait.Rarity.UNCOMMON;
                    }
                    case "RARE" -> {
                        return Trait.Rarity.RARE;
                    }
                    case "EPIC" -> {
                        return Trait.Rarity.EPIC;
                    }
                }
            }
            return Trait.Rarity.COMMON;
        }
    }
}
