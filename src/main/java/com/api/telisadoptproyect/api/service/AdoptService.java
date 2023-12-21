package com.api.telisadoptproyect.api.service;

import com.api.telisadoptproyect.api.request.AdoptRequests.AdoptCreateRequest;
import com.api.telisadoptproyect.api.request.SubTraitRequests.SubTraitCreateRequest;
import com.api.telisadoptproyect.api.response.AdoptResponses.AdoptSingletonResponse;
import com.api.telisadoptproyect.api.response.BaseResponse;
import com.api.telisadoptproyect.library.entity.Adopt;
import com.api.telisadoptproyect.library.entity.Specie;
import com.api.telisadoptproyect.library.entity.SubTrait;
import com.api.telisadoptproyect.library.exception.BadRequestException;
import com.api.telisadoptproyect.library.repository.AdoptRepository;
import com.api.telisadoptproyect.library.validation.EnumValidation;
import org.springframework.beans.factory.annotation.Autowired;
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

    public AdoptSingletonResponse createAdopt(AdoptCreateRequest createRequest){
        if (createRequest == null) throw new BadRequestException("The request cannot be null");
        if (createRequest.getSpecieId() == null) throw new BadRequestException("The Specie cannot be null");
        if (createRequest.getCreationType() == null) throw new BadRequestException("The Creation Type cannot be null");
        if(!EnumValidation.validateEnum(Adopt.CreationType.class, createRequest.getCreationType())){
            throw new BadRequestException("The Creation Type is invalid.");
        }

        Specie specie = specieService.findById(createRequest.getSpecieId());
        subTraitService.validateIfSubTraitsBelongSpecie(createRequest.getSubTraits(), specie);

        Adopt adopt = new Adopt();
        adopt.setName(createRequest.getName() != null? createRequest.getName() : "TBN");
        adopt.setCode("TBN");
        adopt.setCreationType(EnumValidation.toEnum(Adopt.CreationType.class, createRequest.getCreationType()));
        adopt.setSpecie(specie);
        adopt.setSubTraits(Collections.emptySet());
        adoptRepository.save(adopt);

        createAndLinkSubTraitsToAdopt(createRequest.getSubTraits(), adopt);

        return new AdoptSingletonResponse(BaseResponse.Status.SUCCESS, HttpStatus.CREATED.value(), adopt);
    }

    //------------------------------------------- [PRIVATE METHODS]
    private void createAndLinkSubTraitsToAdopt(List<SubTraitCreateRequest> requests, Adopt adopt){
        Set<SubTrait> subTraits = subTraitService.createSubTraitsByAdopt(requests, adopt);
        subTraitService.saveAll(subTraits);
        adopt.setSubTraits(subTraits);
        adoptRepository.save(adopt);
    }
}
