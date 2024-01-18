package com.api.telisadoptproyect.api.service;

import com.api.telisadoptproyect.api.request.OwnerRequests.OwnerCreateRequest;
import com.api.telisadoptproyect.api.response.BaseResponse;
import com.api.telisadoptproyect.api.response.OwnerResponses.OwnerCollectionResponse;
import com.api.telisadoptproyect.api.response.OwnerResponses.OwnerSingletonResponse;
import com.api.telisadoptproyect.api.validation.OwnerValidation;
import com.api.telisadoptproyect.library.entity.Owner;
import com.api.telisadoptproyect.library.entity.PasswordResetToken;
import com.api.telisadoptproyect.library.entity.QOwner;
import com.api.telisadoptproyect.library.exception.BadRequestException;
import com.api.telisadoptproyect.library.repository.OwnerRepository;
import com.api.telisadoptproyect.library.repository.PasswordResetTokenRepository;
import com.api.telisadoptproyect.library.util.PaginationUtils;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class OwnerService {
    @Autowired
    private OwnerRepository ownerRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private PasswordResetTokenRepository passwordResetTokenRepository;
    @Autowired
    private OwnerValidation ownerValidation;

    public OwnerSingletonResponse createOwner(OwnerCreateRequest createRequest) {
        ownerValidation.checkIfNicknameOwnerExist(createRequest.getNickName());
        ownerValidation.checkIfEmailOwnerExist(createRequest.getEmail());   
        Owner owner = new Owner();
        owner.setNickName(createRequest.getNickName());
        owner.setEmail(createRequest.getEmail());
        owner.setPassword(passwordEncoder.encode(createRequest.getNickName()));

        return new OwnerSingletonResponse(BaseResponse.Status.SUCCESS,
                HttpStatus.CREATED.value(),
                ownerRepository.save(owner));
    }

    public Owner createNotRegisteredOwner(String ownerNickname){
        ownerValidation.checkIfNicknameOwnerExist(ownerNickname);
        Owner owner = new Owner();
        owner.setNickName(ownerNickname);
        return ownerRepository.save(owner);
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


    public void createPasswordResetTokenForOwner(Owner ownerFound, String token) {
        final PasswordResetToken myToken = new PasswordResetToken(ownerFound, token);
        myToken.setId(UUID.randomUUID().toString());
        passwordResetTokenRepository.save(myToken);
    }

    public Owner getMyProfile() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return getOwnerByEmail(userDetails.getUsername());
    }

    public OwnerCollectionResponse getOwnerCollectionAutocomplete() {
        return new OwnerCollectionResponse(BaseResponse.Status.SUCCESS, HttpStatus.OK.value(), ownerRepository.findAll());
    }

    public Page<Owner> getOwnersCollection(Integer pageNumber, Integer pageLimit, String nickName, String email) {
        QOwner qOwner = QOwner.owner;
        BooleanExpression query = qOwner.id.isNotNull();

        if (StringUtils.isNotBlank(nickName)){
            query = query.and(qOwner.nickName.eq(nickName));
        }

        if (StringUtils.isNotBlank(email)){
            query = query.and(qOwner.email.eq(email));
        }

        Sort sort = PaginationUtils.createSortCriteria("nickName:ASC");
        Pageable pageable = PageRequest.of(pageNumber, pageLimit, sort);

        return ownerRepository.findAll(query, pageable);
    }
}
