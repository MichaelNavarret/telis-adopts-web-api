package com.api.telisadoptproyect.api.service;

import com.api.telisadoptproyect.api.request.SubTraitRequests.SubTraitCreateRequest;
import com.api.telisadoptproyect.library.entity.Adopt;
import com.api.telisadoptproyect.library.entity.Specie;
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

@Service
public class SubTraitService {
    @Autowired
    private SubTraitRepository subTraitRepository;
    @Autowired
    private TraitService traitService;

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
        }).toList();
        validateIfSubTraitsBelongSpecie(createRequestList, adopt.getSpecie());
        return new HashSet<>(subTraitRepository.saveAll(subTraits));
    }
    public void validateIfSubTraitsBelongSpecie(List<SubTraitCreateRequest> createRequestList, Specie specie){
        List<String> mainTraitsIds = createRequestList.stream().map(SubTraitCreateRequest::getMainTraitId).toList();
        List<Trait> mainTraits = traitService.findByIds(mainTraitsIds);
        mainTraits.forEach(mainTrait -> {
            if (mainTrait.getSpecie().getId().equals(specie.getId()) == false){
                throw new BadRequestException("One of the main traits not belongs to the current Specie");
            }
        });
    }

    public SubTrait save (SubTrait subTrait){
        return subTraitRepository.save(subTrait);
    }

    public List<SubTrait> saveAll (Set<SubTrait> subTraits){
        return subTraitRepository.saveAll(subTraits);
    }
}
