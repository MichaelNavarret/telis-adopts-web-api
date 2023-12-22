package com.api.telisadoptproyect.api.service;

import com.api.telisadoptproyect.library.entity.Owner;
import com.api.telisadoptproyect.library.exception.BadRequestException;
import com.api.telisadoptproyect.library.repository.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OwnerService {
    @Autowired
    private OwnerRepository ownerRepository;

    public Owner getOwnerByEmail(String email){
        return ownerRepository.findByEmail(email).orElseThrow(
                () -> new BadRequestException("Owner not found with email: " + email)
        );
    }

    public Owner getOwnerById(String ownerId){
        return ownerRepository.findById(ownerId).orElseThrow(
                () -> new BadRequestException("Owner not found with id: " + ownerId)
        );
    }

    public void save(Owner owner){
        ownerRepository.save(owner);
    }
}
