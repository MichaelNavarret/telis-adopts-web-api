package com.api.telisadoptproyect.api.service;

import com.api.telisadoptproyect.api.request.OwnerRequests.OwnerCreateRequest;
import com.api.telisadoptproyect.api.response.BaseResponse;
import com.api.telisadoptproyect.api.response.OwnerResponses.OwnerSingletonResponse;
import com.api.telisadoptproyect.library.entity.Owner;
import com.api.telisadoptproyect.library.exception.BadRequestException;
import com.api.telisadoptproyect.library.repository.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class OwnerService {
    @Autowired
    private OwnerRepository ownerRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public OwnerSingletonResponse createOwner(OwnerCreateRequest createRequest) {
        Owner owner = new Owner();
        owner.setNickName(createRequest.getNickName());
        owner.setEmail(createRequest.getEmail());
        owner.setPassword(passwordEncoder.encode(createRequest.getNickName()));

        return new OwnerSingletonResponse(BaseResponse.Status.SUCCESS,
                HttpStatus.CREATED.value(),
                ownerRepository.save(owner));
    }

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
