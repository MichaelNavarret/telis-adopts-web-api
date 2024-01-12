package com.api.telisadoptproyect.api.validation;

import com.api.telisadoptproyect.api.request.SubTraitRequests.SubTraitCreateRequest;
import com.api.telisadoptproyect.library.entity.Specie;
import com.api.telisadoptproyect.library.entity.Trait;
import com.api.telisadoptproyect.library.exception.BadRequestException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SubTraitValidation {
    public void validateIfSubTraitsBelongSpecie(List<Trait> traits, Specie specie){
        traits.forEach(mainTrait -> {
            if (mainTrait.getSpecie().getId().equals(specie.getId()) == false){
                throw new BadRequestException("One of the main traits not belongs to the current Specie");
            }
        });
    }

}
