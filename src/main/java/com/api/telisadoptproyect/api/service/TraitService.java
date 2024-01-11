package com.api.telisadoptproyect.api.service;

import com.api.telisadoptproyect.api.request.TraitRequests.TraitCreateRequest;
import com.api.telisadoptproyect.api.request.TraitRequests.TraitUpdateRequest;
import com.api.telisadoptproyect.api.response.BaseResponse;
import com.api.telisadoptproyect.api.response.TraitResponses.TraitCollectionResponse;
import com.api.telisadoptproyect.api.response.TraitResponses.TraitSingletonResponse;
import com.api.telisadoptproyect.library.entity.QTrait;
import com.api.telisadoptproyect.library.entity.Specie;
import com.api.telisadoptproyect.library.entity.Trait;
import com.api.telisadoptproyect.library.exception.BadRequestException;
import com.api.telisadoptproyect.library.repository.TraitRepository;
import com.api.telisadoptproyect.library.util.PaginationUtils;
import com.api.telisadoptproyect.library.validation.EnumValidation;
import com.querydsl.core.types.dsl.BooleanExpression;
import jakarta.transaction.Transactional;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TraitService {
    @Autowired
    private TraitRepository traitRepository;
    @Autowired
    private SpecieService specieService;

    // ----------- [Public Endpoints Methods] --------------
    public Page<Trait> getTraitCollection(Integer pageNumber, Integer pageLimit, String specieId, String rarity){
        if (EnumValidation.validateEnum(Trait.Rarity.class, rarity)) throw new BadRequestException("Rarity is not valid");

        QTrait qTrait = QTrait.trait1;
        BooleanExpression query = qTrait.id.isNotNull();

        if(StringUtils.isNotBlank(specieId)){
            query = query.and(qTrait.specie.id.eq(specieId));
        }

        Sort sort = PaginationUtils.createSortCriteria("trait:ASC");
        Pageable pageable = PageRequest.of(pageNumber, pageLimit, sort);

        if(specieId == null && rarity == null){
            return traitRepository.findAll(pageable);
        }
        return traitRepository.findAll(query, pageable);
    }

    public TraitSingletonResponse getTrait(String specieId, String traitId){
        if (StringUtils.isBlank(specieId)) throw new BadRequestException("Specie id is required");
        if (StringUtils.isBlank(traitId)) throw new BadRequestException("Trait id is required");

        Specie specie = specieService.findById(specieId);
        Trait trait = findByIdAndSpecieId(traitId, specie.getId());

        return new TraitSingletonResponse(
                BaseResponse.Status.SUCCESS,
                HttpStatus.OK.value(),
                trait);
    }

    @Transactional
    public TraitSingletonResponse createTrait (TraitCreateRequest createRequest){
        if (createRequest == null) throw new BadRequestException("Request is required");
        if (StringUtils.isBlank(createRequest.getSpecieId())) throw new BadRequestException("Specie id is required");

        createRequest.getRarities().forEach(rarity -> {
            if (!EnumValidation.validateEnum(Trait.Rarity.class, rarity))
                throw new BadRequestException("Rarity is not valid");
        });

        Specie specie = specieService.findById(createRequest.getSpecieId());

        return new TraitSingletonResponse(
                BaseResponse.Status.SUCCESS,
                HttpStatus.CREATED.value(),
                buildTrait(createRequest, specie));
    }

    public TraitSingletonResponse updateTrait(String specieId, String traitId, TraitUpdateRequest updateRequest) {
        if (StringUtils.isBlank(traitId)) throw new BadRequestException("Trait id is required");
        if (updateRequest == null) throw new BadRequestException("Request is required");

        updateRequest.getRarities().forEach(rarity -> {
            if (!EnumValidation.validateEnum(Trait.Rarity.class, rarity))
                throw new BadRequestException("Rarity is not valid");
        });

        Trait trait = findByIdAndSpecieId(traitId, specieId);

        if (StringUtils.isNotBlank(updateRequest.getTrait())){
            trait.setTrait(updateRequest.getTrait());
        }

        if (updateRequest.getRarities() != null){
            trait.setRarities(updateRequest.getRarities().stream().map(rarity -> EnumValidation.toEnum(Trait.Rarity.class, rarity)).toList());
        }

        return new TraitSingletonResponse(
                BaseResponse.Status.SUCCESS,
                HttpStatus.OK.value(),
                traitRepository.save(trait));
    }

    // ----------- [Public Utils Methods] --------------

    public Trait findByIdAndSpecieId(String traitId, String specieId){
        return traitRepository.findByIdAndSpecie_Id(traitId, specieId).orElseThrow(
                () -> new BadRequestException("Trait id or Specie id not valid together")
        );
    }

    public Trait findById(String traitId){
        return traitRepository.findById(traitId).orElseThrow(
                () -> new BadRequestException("Trait id not valid")
        );
    }
    public List<Trait> findByIds(List<String> traitIds){
        return traitIds.stream().map(traitId -> traitRepository.findById(traitId).orElseThrow(
                () -> new BadRequestException("One of the trait ids are not valid")
        )).toList();
    }

    // ------------- [Private Methods] -------------

    private Trait buildTrait(TraitCreateRequest createRequest, Specie specie){
        Trait trait = new Trait();
        trait.setTrait(createRequest.getTrait());
        trait.setRarities(createRequest.getRarities().stream().map(rarity -> EnumValidation.toEnum(Trait.Rarity.class, rarity)).toList());
        trait.setSpecie(specie);
        return traitRepository.save(trait);
    }
}
