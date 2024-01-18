package com.api.telisadoptproyect.api.service;

import com.api.telisadoptproyect.api.response.BaseResponse;
import com.api.telisadoptproyect.api.response.SpecieForm.SpecieFormSingletonResponse;
import com.api.telisadoptproyect.library.entity.SpecieForm;
import com.api.telisadoptproyect.library.exception.BadRequestException;
import com.api.telisadoptproyect.library.repository.SpecieFormRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SpecieFormService {
    @Autowired
    private SpecieFormRepository specieFormRepository;

    public SpecieFormSingletonResponse getSpecieFormSingletonInfo(String specieFormId) {
        SpecieForm specieForm = specieFormRepository.findById(specieFormId).orElseThrow(
                () -> new BadRequestException("SpecieForm not found - id: " + specieFormId)
        );
        return new SpecieFormSingletonResponse(BaseResponse.Status.SUCCESS, 200, specieForm);
    }
}
