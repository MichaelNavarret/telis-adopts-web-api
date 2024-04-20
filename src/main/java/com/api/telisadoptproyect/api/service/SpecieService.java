package com.api.telisadoptproyect.api.service;

import com.api.telisadoptproyect.api.configuration.PropertiesConfig;
import com.api.telisadoptproyect.api.request.SpecieRequests.SpecieCreateRequest;
import com.api.telisadoptproyect.api.request.SpecieRequests.SpecieUpdateRequest;
import com.api.telisadoptproyect.api.response.BaseResponse;
import com.api.telisadoptproyect.api.response.SpecieResponses.SpecieCollectionResponse;
import com.api.telisadoptproyect.api.response.SpecieResponses.SpecieSingletonResponse;
import com.api.telisadoptproyect.api.validation.SpecieValidation;
import com.api.telisadoptproyect.library.entity.Specie;
import com.api.telisadoptproyect.library.entity.SpecieForm;
import com.api.telisadoptproyect.library.exception.BadRequestException;
import com.api.telisadoptproyect.library.repository.SpecieFormRepository;
import com.api.telisadoptproyect.library.repository.SpecieRepository;
import com.api.telisadoptproyect.library.util.PaginationUtils;
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

import java.util.Set;

import static com.api.telisadoptproyect.commons.Constants.*;

@Service
public class SpecieService {
    @Autowired
    private SpecieRepository specieRepository;
    @Autowired
    private SpecieFormRepository specieFormRepository;
    @Autowired
    private PropertiesConfig propertiesConfig;
    @Autowired
    private CloudinaryService cloudinaryService;
    @Autowired
    private SpecieValidation specieValidation;
    @Autowired
    private FaqService faqService;

    // ----------- Main Endpoints Methods --------------
    public Page<Specie> getSpecieCollection(Integer pageNumber, Integer pageLimit){
        Sort sort = PaginationUtils.createSortCriteria("name:ASC");
        Pageable pageable = PageRequest.of(pageNumber, pageLimit, sort);
        return specieRepository.findAll(pageable);
    }


    public SpecieCollectionResponse getSpecieCollectionAutocomplete() {
        return new SpecieCollectionResponse(BaseResponse.Status.SUCCESS, HttpStatus.OK.value(), specieRepository.findAll());
    }

    @Transactional
    public SpecieSingletonResponse createSpecie(SpecieCreateRequest request){
        if(StringUtils.isBlank(request.getName())) throw new BadRequestException("The name of specie cannot be null");
        Specie foundedSpecie = specieRepository.findByName(request.getName()).orElse(null);
        if (foundedSpecie != null) throw new BadRequestException("The name of specie cannot be repeated");
        Specie specie = new Specie();
        specie.setCode(specieCodeGenerator(request.getName()));
        specie.setName(request.getName());

        if(StringUtils.isNotBlank(request.getMainSpecieId())){
            Specie mainSpecie = specieRepository.findById(request.getMainSpecieId()).orElseThrow(
                    () -> new BadRequestException("The mainSpecieId not exist"));
            specie.setMainSpecie(mainSpecie);
            specieRepository.save(specie);

            Set<Specie> subSpecies = mainSpecie.getSubSpecies();
            subSpecies.add(specie);
            mainSpecie.setSubSpecies(subSpecies);
            specieRepository.save(mainSpecie);
        }

        specieRepository.save(specie);

        return new SpecieSingletonResponse(BaseResponse.Status.SUCCESS, HttpStatus.CREATED.value(), specie);

    }
    @Transactional
    public SpecieSingletonResponse addSpecieForm(String specieId, MultipartFile imageSpecieForm, String code) {
        if(StringUtils.isBlank(specieId)) throw new BadRequestException("The specieId cannot be null");
        if(imageSpecieForm == null || imageSpecieForm.isEmpty()) throw new BadRequestException("The image cannot be null");

        Specie specie = specieRepository.findById(specieId).orElseThrow(
                () -> new BadRequestException("The Specie with the corresponding id not exist"));

        SpecieForm specieForm = new SpecieForm();
        if(StringUtils.isNotBlank(code)){
            specieForm.setCode(code);
        }

        String publicId = cloudinaryService.uploadFile(imageSpecieForm, CLOUDINARY_SPECIE_FORM_FOLDER_PATH);
        specieForm.setFormUrlImage(cloudinaryService.getUrlFile(publicId, CLOUDINARY_SPECIE_FORM_FOLDER_PATH));

        specieForm = specieFormRepository.save(specieForm);

        Set<SpecieForm> specieFormList = specie.getExtraInfoList();
        specieFormList.add(specieForm);
        specie.setExtraInfoList(specieFormList);

        return new SpecieSingletonResponse(BaseResponse.Status.SUCCESS, HttpStatus.CREATED.value(),  specieRepository.save(specie));
    }


    public SpecieSingletonResponse getSpecie(String specieId) {
        Specie specie = findById(specieId);
        return new SpecieSingletonResponse(BaseResponse.Status.SUCCESS, HttpStatus.OK.value(), specie);
    }


    public SpecieSingletonResponse updateSpecie(String specieId, SpecieUpdateRequest request){
        if(request == null) throw new BadRequestException("The request cannot be null");

        Specie currentSpecie = specieRepository.findById(specieId).orElseThrow(
                () -> new BadRequestException("The Specie with the corresponding id not exist"));

        if(StringUtils.isNotBlank(request.getName()) && currentSpecie.getName().equals(request.getName()) == false){
            Specie specieWithSameName = specieRepository.findByName(request.getName()).orElse(null);
            if(specieWithSameName == null){
                currentSpecie.setName(request.getName().trim());
            }
            currentSpecie.setCode(specieCodeGenerator(request.getName()));
        }

        if(StringUtils.isNotBlank(request.getStory())){
           currentSpecie.setHistory(request.getStory());
        }

        return new SpecieSingletonResponse(BaseResponse.Status.SUCCESS,
                HttpStatus.CREATED.value(),
                specieRepository.save(currentSpecie));
    }

    public SpecieSingletonResponse updateSpecieAsset(String specieId, MultipartFile inputFile, String assetType) {
        if(StringUtils.isBlank(specieId)) throw new BadRequestException("The specieId cannot be null");
        if(inputFile == null || inputFile.isEmpty()) throw new BadRequestException("The image cannot be null");
        if(StringUtils.isBlank(assetType)) throw new BadRequestException("The assetType cannot be null");
        Specie specie = findById(specieId);

        String publicId = null;
        switch (assetType){
            case "LOGO":
                publicId = cloudinaryService.uploadFile(inputFile, CLOUDINARY_LOGO_FOLDER_PATH);
                if(specie.getLogoUrl() != null){
                    cloudinaryService.destroyFile(specie.getLogoUrl(), CLOUDINARY_LOGO_FOLDER_PATH);
                }
                specie.setLogoUrl(cloudinaryService.getUrlFile(publicId, CLOUDINARY_LOGO_FOLDER_PATH));
                break;
            case "MASTER_LIST_BANNER":
                publicId = cloudinaryService.uploadFile(inputFile, CLOUDINARY_MASTER_LIST_BANNER_FOLDER_PATH);
                if(specie.getMasterListBannerUrl() != null){
                    cloudinaryService.destroyFile(specie.getMasterListBannerUrl(), CLOUDINARY_MASTER_LIST_BANNER_FOLDER_PATH);
                }
                specie.setMasterListBannerUrl(cloudinaryService.getUrlFile(publicId, CLOUDINARY_MASTER_LIST_BANNER_FOLDER_PATH));
                break;
            case "TRAIT_SHEET":
                publicId = cloudinaryService.uploadFile(inputFile, CLOUDINARY_TRAITS_SHEET_FOLDER_PATH);
                if(specie.getTraitSheetUrl() != null){
                    cloudinaryService.destroyFile(specie.getTraitSheetUrl(),CLOUDINARY_TRAITS_SHEET_FOLDER_PATH);
                }
                specie.setTraitSheetUrl(cloudinaryService.getUrlFile(publicId, CLOUDINARY_TRAITS_SHEET_FOLDER_PATH));
                break;
            case "GUIDE_SHEET":
                publicId = cloudinaryService.uploadFile(inputFile, CLOUDINARY_GUIDE_SHEET_FOLDER_PATH);
                if(specie.getGuideSheetUrl() != null){
                    cloudinaryService.destroyFile(specie.getGuideSheetUrl(), CLOUDINARY_GUIDE_SHEET_FOLDER_PATH);
                }
                specie.setGuideSheetUrl(cloudinaryService.getUrlFile(publicId, CLOUDINARY_GUIDE_SHEET_FOLDER_PATH));
                break;
            case "CHARACTER":
                publicId = cloudinaryService.uploadFile(inputFile, CLOUDINARY_CHARACTER_FOLDER_PATH);
                if(specie.getCharacterUrl() != null){
                    cloudinaryService.destroyFile(specie.getCharacterUrl(), CLOUDINARY_CHARACTER_FOLDER_PATH);
                }
                specie.setCharacterUrl(cloudinaryService.getUrlFile(publicId, CLOUDINARY_CHARACTER_FOLDER_PATH));
            default:
                throw new BadRequestException("The assetType is not valid");
        }

        return new SpecieSingletonResponse(BaseResponse.Status.SUCCESS,
                HttpStatus.CREATED.value(),
                specieRepository.save(specie));
    }

    // ----------- Public Utils methods --------------
    public Specie findById(String specieId){
        return specieRepository.findById(specieId).orElseThrow(
                () -> new BadRequestException("Not founded Specie with that Id")
        );
    }

    // ----------- Private methods --------------
    private String specieCodeGenerator(String name){
        return name.trim().toLowerCase().replaceAll("\\s+", "_");
    }
}
