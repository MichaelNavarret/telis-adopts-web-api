package com.api.telisadoptproyect.api.service;

import com.api.telisadoptproyect.api.request.FaqRequests.FaqCreateRequest;
import com.api.telisadoptproyect.api.request.FaqRequests.FaqUpdateRequest;
import com.api.telisadoptproyect.api.response.BaseResponse;
import com.api.telisadoptproyect.api.response.FaqResponses.FaqCollectionResponse;
import com.api.telisadoptproyect.api.response.FaqResponses.FaqSingletonResponse;
import com.api.telisadoptproyect.library.entity.Faq;
import com.api.telisadoptproyect.library.entity.Specie;
import com.api.telisadoptproyect.library.exception.BadRequestException;
import com.api.telisadoptproyect.library.repository.FaqRepository;
import com.api.telisadoptproyect.library.repository.SpecieRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FaqService {
    @Autowired
    private FaqRepository faqRepository;
    @Autowired
    private SpecieRepository specieRepository;

    public FaqCollectionResponse getAllFaqsBySpecie(String specieId){
        List<Faq> faqs = faqRepository.findBySpecie_IdOrderByCreatedOnAsc(specieId);
        return new FaqCollectionResponse(faqs);
    }

    public FaqSingletonResponse createFaq(FaqCreateRequest faqCreateRequest){
        if (faqCreateRequest == null) throw new BadRequestException("FaqCreateRequest is required");
        if (StringUtils.isBlank(faqCreateRequest.getSpecieId())) throw new BadRequestException("SpecieId is required");
        Specie specie = specieRepository.findById(faqCreateRequest.getSpecieId()).orElseThrow(() -> new BadRequestException("Specie not found"));

        Faq newFaq = buildFaqFromRequest(faqCreateRequest, specie);
        faqRepository.save(newFaq);

        List<Faq> oldFaqList = faqRepository.findBySpecie_Id(faqCreateRequest.getSpecieId());
        oldFaqList.add(newFaq);
        specie.setFaqs(oldFaqList);
        specieRepository.save(specie);


        return new FaqSingletonResponse(BaseResponse.Status.SUCCESS,
                                        HttpStatus.CREATED.value(),
                                        newFaq);
    }

    public FaqSingletonResponse updateFaq(String specieId, String faqId, FaqUpdateRequest faqUpdateRequest){
        if (StringUtils.isBlank(specieId)) throw new BadRequestException("SpecieId is required");
        if (StringUtils.isBlank(faqId)) throw new BadRequestException("FaqId is required");
        specieRepository.findById(specieId).orElseThrow(() -> new BadRequestException("Specie not found"));
        Faq currentFaq = findById(faqId);

        if(StringUtils.isNotBlank(faqUpdateRequest.getQuestion())){
            currentFaq.setQuestion(faqUpdateRequest.getQuestion());
        }

        if(StringUtils.isNotBlank(faqUpdateRequest.getAnswer())){
            currentFaq.setAnswer(faqUpdateRequest.getAnswer());
        }

        if(StringUtils.isNotBlank(faqUpdateRequest.getWarning())){
            currentFaq.setWarning(faqUpdateRequest.getWarning());
        }else{
            currentFaq.setWarning(null);
        }

        return new FaqSingletonResponse(BaseResponse.Status.SUCCESS,
                                        HttpStatus.OK.value(),
                                        faqRepository.save(currentFaq));
    }

    public FaqSingletonResponse deleteFaq(String specieId, String faqId){
        if (StringUtils.isBlank(specieId)) throw new BadRequestException("SpecieId is required");
        if (StringUtils.isBlank(faqId)) throw new BadRequestException("FaqId is required");
        Specie specie = specieRepository.findById(specieId).orElseThrow(() -> new BadRequestException("Specie not found"));
        Faq faq = findById(faqId);

        List<Faq> oldFaqList = specie.getFaqs();
        oldFaqList.remove(faq);
        specie.setFaqs(oldFaqList);
        faqRepository.delete(faq);
        specieRepository.save(specie);

        return new FaqSingletonResponse(BaseResponse.Status.SUCCESS,
                                        HttpStatus.OK.value(),
                                        faq);
    }

    // ---------------- PRIVATE METHODS ----------------
    private Faq findById(String faqId){
        return faqRepository.findById(faqId).orElseThrow(() -> new BadRequestException("Faq not found"));
    }

    private Faq buildFaqFromRequest(FaqCreateRequest faqCreateRequest, Specie specie){
        Faq faq = new Faq();
        faq.setQuestion(faqCreateRequest.getQuestion());
        faq.setAnswer(faqCreateRequest.getAnswer());
        faq.setWarning(faqCreateRequest.getWarning());
        faq.setSpecie(specie);
        return faqRepository.save(faq);
    }
}
