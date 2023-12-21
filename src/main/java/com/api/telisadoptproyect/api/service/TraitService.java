package com.api.telisadoptproyect.api.service;

import com.api.telisadoptproyect.library.entity.Trait;
import com.api.telisadoptproyect.library.exception.BadRequestException;
import com.api.telisadoptproyect.library.repository.TraitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TraitService {
    @Autowired
    private TraitRepository traitRepository;

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
}
