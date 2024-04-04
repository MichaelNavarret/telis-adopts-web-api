package com.api.telisadoptproyect.api.validation;

import com.api.telisadoptproyect.api.request.AdoptRequests.AdoptUpdateRequest;
import com.api.telisadoptproyect.library.exception.BadRequestException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class AdoptValidation {

   public void updateAdoptParamsValidation(String adoptId, AdoptUpdateRequest request){
       if (StringUtils.isBlank(adoptId)) throw new BadRequestException("The adoptId cannot be null");
       if (request == null) throw new BadRequestException("The request cannot be null");
       if (StringUtils.isBlank(request.getSpecieId())) throw new BadRequestException("The specieId cannot be null");
   }
}
