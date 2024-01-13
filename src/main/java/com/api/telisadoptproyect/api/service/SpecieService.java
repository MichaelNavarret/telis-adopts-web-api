package com.api.telisadoptproyect.api.service;

import com.api.telisadoptproyect.api.configuration.PropertiesConfig;
import com.api.telisadoptproyect.api.request.SpecieRequests.SpecieUpdateRequest;
import com.api.telisadoptproyect.api.response.BaseResponse;
import com.api.telisadoptproyect.api.response.SpecieResponses.SpecieCollectionResponse;
import com.api.telisadoptproyect.api.response.SpecieResponses.SpecieSingletonResponse;
import com.api.telisadoptproyect.library.entity.Specie;
import com.api.telisadoptproyect.library.exception.BadRequestException;
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
import com.cloudinary.*;
import com.cloudinary.utils.ObjectUtils;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;

import static com.api.telisadoptproyect.commons.Constants.CLOUDINARY_TRAITS_SHEET_FOLDER_PATH;

@Service
public class SpecieService {
    @Autowired
    private SpecieRepository specieRepository;
    @Autowired
    private PropertiesConfig propertiesConfig;
    @Autowired
    private CloudinaryService cloudinaryService;

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
    public SpecieSingletonResponse createSpecie( MultipartFile traitsSheet, String specieName){
        if(StringUtils.isBlank(specieName)) throw new BadRequestException("The name of specie cannot be null");
        Specie foundedSpecie = specieRepository.findByName(specieName).orElse(null);
        if (foundedSpecie != null) throw new BadRequestException("The name of specie cannot be repeated");
        Specie specie = new Specie();
        specie.setCode(specieCodeGenerator(specieName));
        specie.setName(specieName);

        if(traitsSheet != null && !traitsSheet.isEmpty()){
           String publicId = cloudinaryService.uploadFile(traitsSheet, CLOUDINARY_TRAITS_SHEET_FOLDER_PATH);
              specie.setTraitSheetUrl(cloudinaryService.getUrlFile(publicId, CLOUDINARY_TRAITS_SHEET_FOLDER_PATH));
        }

        specieRepository.save(specie);

        return new SpecieSingletonResponse(BaseResponse.Status.SUCCESS, HttpStatus.CREATED.value(), specie);

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

        return new SpecieSingletonResponse(BaseResponse.Status.SUCCESS,
                HttpStatus.CREATED.value(),
                specieRepository.save(currentSpecie));
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
