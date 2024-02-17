package com.api.telisadoptproyect.api.service;

import com.api.telisadoptproyect.api.request.SubTraitRequests.SubTraitCreateRequest;
import com.api.telisadoptproyect.api.request.SubTraitRequests.SubTraitUpdateRequest;
import com.api.telisadoptproyect.api.response.SubTraitResponses.SubTraitInfo;
import com.api.telisadoptproyect.api.validation.SubTraitValidation;
import com.api.telisadoptproyect.library.entity.Adopt;
import com.api.telisadoptproyect.library.entity.SubTrait;
import com.api.telisadoptproyect.library.entity.Trait;
import com.api.telisadoptproyect.library.exception.BadRequestException;
import com.api.telisadoptproyect.library.repository.SubTraitRepository;
import com.api.telisadoptproyect.library.validation.EnumValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SubTraitService {
    @Autowired
    private SubTraitRepository subTraitRepository;
    @Autowired
    private TraitService traitService;
    @Autowired
    private SubTraitValidation subTraitValidation;

    public Set<SubTrait> createSubTraitsByAdopt(List<SubTraitCreateRequest> createRequestList, Adopt adopt){
        List<SubTrait> subTraits = createRequestList.stream().map(request -> {
            SubTrait.Rarity rarity = EnumValidation.toEnum(SubTrait.Rarity.class, request.getRarity());
            if(rarity == null) throw new BadRequestException("The Rarity is invalid.");
            SubTrait subTrait = new SubTrait();
            subTrait.setAdopt(adopt);
            subTrait.setAdditionalInfo(request.getAdditionalInfo());
            subTrait.setMainTrait(traitService.findById(request.getMainTraitId()));
            subTrait.setRarity(rarity);
            return subTrait;
        }).collect(Collectors.toList());

        List<String> mainTraitsIds = createRequestList.stream().map(SubTraitCreateRequest::getMainTraitId).collect(Collectors.toList());
        List<Trait> mainTraits = traitService.findByIds(mainTraitsIds);

        subTraitValidation.validateIfSubTraitsBelongSpecie(mainTraits, adopt.getSpecie());
        return new HashSet<>(subTraitRepository.saveAll(subTraits));
    }


    public void updateSubTraitAdditionalInfo(SubTraitUpdateRequest subTrait){
        SubTrait subTraitToUpdate = subTraitRepository.findById(subTrait.getId()).orElseThrow(
                () -> new BadRequestException("The SubTrait does not exist.")
        );
        subTraitToUpdate.setAdditionalInfo(subTrait.getAdditionalInfo());
        save(subTraitToUpdate);
    }

    public SubTrait save (SubTrait subTrait){
        return subTraitRepository.save(subTrait);
    }

    public void saveAll (Set<SubTrait> subTraits){
        subTraitRepository.saveAll(subTraits);
    }
}
