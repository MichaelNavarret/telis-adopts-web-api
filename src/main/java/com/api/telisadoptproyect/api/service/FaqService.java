package com.api.telisadoptproyect.api.service;

import com.api.telisadoptproyect.api.response.FaqResponses.FaqCollectionResponse;
import com.api.telisadoptproyect.library.entity.Faq;
import com.api.telisadoptproyect.library.repository.FaqRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FaqService {
    @Autowired
    private FaqRepository faqRepository;

    public FaqCollectionResponse getAllFaqsBySpecie(String specieId){
        List<Faq> faqs = faqRepository.findBySpecie_Id(specieId);
        return new FaqCollectionResponse(faqs);
    }
}
