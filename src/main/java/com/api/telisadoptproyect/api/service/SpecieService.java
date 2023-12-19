package com.api.telisadoptproyect.api.service;

import com.api.telisadoptproyect.api.request.SpecieRequests.SpecieCreateRequest;
import com.api.telisadoptproyect.api.response.BaseResponse;
import com.api.telisadoptproyect.api.response.SpecieResponses.SpecieSingletonResponse;
import com.api.telisadoptproyect.library.entity.Specie;

import com.api.telisadoptproyect.library.exception.BadRequestException;
import com.api.telisadoptproyect.library.repository.SpecieRepository;
import com.api.telisadoptproyect.library.util.PaginationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class SpecieService {
    @Autowired
    private SpecieRepository specieRepository;

    public Page<Specie> getSpecieCollection(Integer pageNumber, Integer pageLimit){
        Sort sort = PaginationUtils.createSortCriteria("name:ASC");
        Pageable pageable = PageRequest.of(pageNumber, pageLimit, sort);
        return specieRepository.findAll(pageable);
    }

    public SpecieSingletonResponse createSpecie(SpecieCreateRequest request){
        if(request == null) throw new BadRequestException("The request cannot be null");
        if(request.getName() == null) throw new BadRequestException("The name of specie cannot be null");

        Specie foundedSpecie = specieRepository.findByName(request.getName()).orElse(null);
        if (foundedSpecie != null) throw new BadRequestException("The name of specie cannot be repeated");

        Specie specie = new Specie();
        specie.setCode(specieCodeGenerator(request.getName()));
        specie.setName(request.getName());
        specieRepository.save(specie);

        return new SpecieSingletonResponse(BaseResponse.Status.SUCCESS, HttpStatus.CREATED.value(), specie);

    }

    private String specieCodeGenerator(String name){
        return name.toLowerCase().replace(' ', '_');
    }
}
