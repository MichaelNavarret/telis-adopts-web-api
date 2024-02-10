package com.api.telisadoptproyect.api.service;

import com.api.telisadoptproyect.api.request.AdoptRequests.AdoptCreateRequest;
import com.api.telisadoptproyect.api.request.SubTraitRequests.SubTraitCreateRequest;
import com.api.telisadoptproyect.api.response.AdoptResponses.AdoptCollectionResponse;
import com.api.telisadoptproyect.api.response.AdoptResponses.AdoptSingletonResponse;
import com.api.telisadoptproyect.api.response.BaseResponse;
import com.api.telisadoptproyect.library.entity.*;
import com.api.telisadoptproyect.library.exception.BadRequestException;
import com.api.telisadoptproyect.library.repository.AdoptRepository;
import com.api.telisadoptproyect.library.repository.SpecieFormRepository;
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
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

import static com.api.telisadoptproyect.commons.Constants.CLOUDINARY_ADOPTS_ICONS_FOLDER_PATH;

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
    @Autowired
    private CloudinaryService cloudinaryService;
    @Autowired
    private SpecieFormRepository specieFormRepository;

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

        if(StringUtils.isNotBlank(createRequest.getSpecieFormId())){
            Optional<SpecieForm> specieForm = specieFormRepository.findById(createRequest.getSpecieFormId());
            specieForm.ifPresent(adopt::setExtraInfo);
        }

        safeSetOwnerToAdopt(adopt, createRequest);
        safeSetDesignersToAdopt(adopt, createRequest);
        
        adoptRepository.save(adopt);

        if(createRequest.getSubTraits() != null && !createRequest.getSubTraits().isEmpty()){
            createAndLinkSubTraitsToAdopt(createRequest.getSubTraits(), adopt);
        }

        return new AdoptSingletonResponse(BaseResponse.Status.SUCCESS, HttpStatus.CREATED.value(), adopt);
    }

    public AdoptSingletonResponse uploadIconToAdopt(String adoptId, MultipartFile adoptIcon){
        if (adoptId == null) throw new BadRequestException("The adoptId cannot be null");
        if (adoptIcon == null || adoptIcon.isEmpty()) throw new BadRequestException("The adoptIcon cannot be null");

        Adopt foundAdopt = adoptRepository.findById(adoptId).orElseThrow(() -> new BadRequestException("The adoptId is invalid"));

        String publicId = cloudinaryService.uploadFile(adoptIcon, CLOUDINARY_ADOPTS_ICONS_FOLDER_PATH);
        foundAdopt.setIconUrl(cloudinaryService.getUrlFile(publicId, CLOUDINARY_ADOPTS_ICONS_FOLDER_PATH));

        adoptRepository.save(foundAdopt);

        return new AdoptSingletonResponse(BaseResponse.Status.SUCCESS, HttpStatus.OK.value(), foundAdopt);
    }

    public Page<Adopt> getAdoptCollection(Integer pageNumber, Integer pageLimit, String specieId, String creationType,
                                          String sort, String ownerId) {
        QAdopt qAdopt = QAdopt.adopt;
        BooleanExpression expression = qAdopt.id.isNotNull();

        if (StringUtils.isNotBlank(specieId)){
            expression = expression.and(qAdopt.specie.id.eq(specieId));
        }

        if (StringUtils.isNotBlank(creationType)){
            Adopt.CreationType creationTypeFound = EnumValidation.toEnum(Adopt.CreationType.class, creationType);
            if (creationTypeFound == null) throw new BadRequestException("The Creation Type is invalid.");
            expression = expression.and(qAdopt.creationType.eq(creationTypeFound));
        }

        if (StringUtils.isNotBlank(ownerId)){
            expression = expression.and(qAdopt.owner.id.eq(ownerId));
        }

        Sort sortCriteria;
        if(StringUtils.isNotBlank(sort)) {
            sortCriteria = PaginationUtils.createSortCriteria(sort);
        }else{
            sortCriteria = PaginationUtils.createSortCriteria("createdOn:DESC");
        }

        Pageable pageable = PageRequest.of(pageNumber, pageLimit, sortCriteria);
        return adoptRepository.findAll(expression, pageable);
    }

    public Page<Adopt> getFavoriteAdopts(Integer pageNumber, Integer pageLimit, String ownerId){
        Owner owner = ownerService.getOwnerById(ownerId);
        Set<Adopt> favoriteAdopts = owner.getFavorites();

        QAdopt qAdopt = QAdopt.adopt;
        BooleanExpression expression = qAdopt.id.in(favoriteAdopts.stream().map(Adopt::getId).toList());

        Sort sortCriteria = PaginationUtils.createSortCriteria("code:ASC");
        Pageable pageable = PageRequest.of(pageNumber, pageLimit, sortCriteria);

        return adoptRepository.findAll(expression, pageable);
    }

    public Page<Adopt> getDesignedAdopts(Integer pageNumber, Integer pageLimit, String ownerId){
        QAdopt qAdopt = QAdopt.adopt;
        BooleanExpression expression = qAdopt.designers.any().id.eq(ownerId);

        Sort sortCriteria = PaginationUtils.createSortCriteria("code:ASC");
        Pageable pageable = PageRequest.of(pageNumber, pageLimit, sortCriteria);

        return adoptRepository.findAll(expression, pageable);
    }

    public AdoptCollectionResponse getAdoptCollectionAutocomplete(String specieId, String creationType) {
        QAdopt qAdopt = QAdopt.adopt;
        BooleanExpression expression = qAdopt.id.isNotNull();

        if (StringUtils.isNotBlank(specieId)){
            expression = expression.and(qAdopt.specie.id.eq(specieId));
        }

        if (StringUtils.isNotBlank(creationType)){
            Adopt.CreationType creationTypeFound = EnumValidation.toEnum(Adopt.CreationType.class, creationType);
            if (creationTypeFound == null) throw new BadRequestException("The Creation Type is invalid.");
            expression = expression.and(qAdopt.creationType.eq(creationTypeFound));
        }

        return new AdoptCollectionResponse((List<Adopt>) adoptRepository.findAll(expression));
    }

    //------------------------------------------- [PRIVATE METHODS]
    //---------------------------------------------------------------------------------------
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
        int traitLevel = 1;
        int auxTraitLevel = 1;
        if (createRequest.getSubTraits() == null || createRequest.getSubTraits().isEmpty()){
            return Trait.Rarity.COMMON;
        }else{
            for (SubTraitCreateRequest subTrait : createRequest.getSubTraits()){
                switch (subTrait.getRarity()){
                    case "UNCOMMON" -> {
                        auxTraitLevel = 2;
                    }
                    case "RARE" -> {
                        auxTraitLevel = 3;
                    }
                    case "EPIC" -> {
                        auxTraitLevel = 4;
                    }
                }
                if (auxTraitLevel > traitLevel){
                    traitLevel = auxTraitLevel;
                }
            }
            return getRarityByNumber(traitLevel);
        }
    }

    private Trait.Rarity getRarityByNumber(int number){
        return switch (number) {
            case 2 -> Trait.Rarity.UNCOMMON;
            case 3 -> Trait.Rarity.RARE;
            case 4 -> Trait.Rarity.EPIC;
            default -> Trait.Rarity.COMMON;
        };
    }


    private void safeSetDesignersToAdopt(Adopt adopt, AdoptCreateRequest createRequest){
        List<Owner> designers = new ArrayList<>();
        Adopt.CreationType creationTypeRequest = EnumValidation.toEnum(Adopt.CreationType.class, createRequest.getCreationType());
        if (EnumValidation.equals(Adopt.CreationType.PREMADE, creationTypeRequest) ||
            EnumValidation.equals(Adopt.CreationType.CUSTOM, creationTypeRequest)){
            designers = List.of(ownerService.getMyProfile());
        }else{
            if (createRequest.getDesigners() != null && !createRequest.getDesigners().isEmpty()){
                designers = createRequest.getDesigners().stream().map(designer -> {
                    if (designer.isNotRegisteredDesigner()){
                        return ownerService.createNotRegisteredOwner(designer.getId());
                    }else{
                        return ownerService.getOwnerById(designer.getId());
                    }
                }).toList();
            }
        }
        adopt.setDesigners(new HashSet<>(designers));
    }

    private void safeSetOwnerToAdopt(Adopt adopt, AdoptCreateRequest createRequest){
        Owner owner;
        if (StringUtils.isNotBlank(createRequest.getOwnerId())){
            if (createRequest.isNotRegisteredOwner()){
                owner = ownerService.createNotRegisteredOwner(createRequest.getOwnerId());
            }else{
                owner = ownerService.getOwnerById(createRequest.getOwnerId());
            }
            adopt.setOwner(owner);
        }
    }


}
