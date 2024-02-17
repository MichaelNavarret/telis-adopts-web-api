package com.api.telisadoptproyect.api.service;

import com.api.telisadoptproyect.api.request.OwnerRequests.OwnerAddCharacterFavoriteRequest;
import com.api.telisadoptproyect.api.request.OwnerRequests.OwnerCreateRequest;
import com.api.telisadoptproyect.api.request.OwnerRequests.OwnerUpdateRequest;
import com.api.telisadoptproyect.api.response.BaseResponse;
import com.api.telisadoptproyect.api.response.OwnerResponses.OwnerCollectionResponse;
import com.api.telisadoptproyect.api.response.OwnerResponses.OwnerSingletonResponse;
import com.api.telisadoptproyect.api.validation.OwnerValidation;
import com.api.telisadoptproyect.library.entity.*;
import com.api.telisadoptproyect.library.exception.BadRequestException;
import com.api.telisadoptproyect.library.repository.AdoptRepository;
import com.api.telisadoptproyect.library.repository.IconRepository;
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

import java.util.*;
import java.util.stream.Collectors;

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
    @Autowired
    private AdoptRepository adoptRepository;
    @Autowired
    private IconRepository iconRepository;

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

    public OwnerSingletonResponse getOwnerSingleton(String ownerId){
        Owner owner = getOwnerById(ownerId);
        List<Adopt> ownerAdoptList = adoptRepository.findByOwner(owner);
        Set<Badge> badges = ownerAdoptList.stream().map(Adopt::getBadges).flatMap(Set::stream).collect(Collectors.toSet());
        List<String> badgesCode = badges.stream().map(Badge::getCode).collect(Collectors.toList());

        OwnerSingletonResponse response = new OwnerSingletonResponse(BaseResponse.Status.SUCCESS, HttpStatus.OK.value(), owner);
        response.setBadgesCode(badgesCode);

        return response;
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

    public OwnerSingletonResponse updateOwner(String ownerId, OwnerUpdateRequest request) {
        if (StringUtils.isBlank(ownerId)) throw new BadRequestException("Owner id is required");
        if (request == null) throw new BadRequestException("Owner update request is required");
        Owner owner = getOwnerById(ownerId);

        if (StringUtils.isNotBlank(request.getNickName()) && !request.getNickName().equals(owner.getNickName())){
            ownerValidation.checkIfNicknameOwnerExist(request.getNickName());
            owner.setNickName(request.getNickName());
        }

        if (StringUtils.isNotBlank(request.getEmail()) && !request.getEmail().equals(owner.getEmail())){
            ownerValidation.checkIfEmailOwnerExist(request.getEmail());
            owner.setEmail(request.getEmail());
        }

        if (StringUtils.isNotBlank(request.getDiscord())){
            owner.setDiscord(request.getDiscord());
        }

        if (StringUtils.isNotBlank(request.getInstagram())){
            owner.setInstagram(request.getInstagram());
        }

        if (StringUtils.isNotBlank(request.getTwitter())){
            owner.setTwitter(request.getTwitter());
        }

        if (StringUtils.isNotBlank(request.getDevianart())){
            owner.setDevianart(request.getDevianart());
        }

        if (StringUtils.isNotBlank(request.getToyhouse())){
            owner.setToyhouse(request.getToyhouse());
        }

        if (StringUtils.isNotBlank(request.getIconId())){
            Optional<Icon> icon = iconRepository.findById(request.getIconId());
            if(icon.isPresent()){
                owner.setProfileIcon(icon.get());
            }else{
                throw new BadRequestException("Icon not found with id: " + request.getIconId());
            }
        }

        if (request.getFavoriteAdoptsIds() != null && !request.getFavoriteAdoptsIds().isEmpty()){
            if (owner.getFavorites().size() <= request.getFavoriteAdoptsIds().size()){
                Set<Adopt> favoriteAdopts =  new HashSet<>();
                request.getFavoriteAdoptsIds().forEach(favoriteAdoptId -> {
                    Optional <Adopt> adopt = adoptRepository.findById(favoriteAdoptId);
                    adopt.ifPresent(favoriteAdopts::add);
                });
                owner.setFavorites(favoriteAdopts);
            }
        }else{
            owner.setFavorites(null);
        }

        return new OwnerSingletonResponse(BaseResponse.Status.SUCCESS, HttpStatus.OK.value(), ownerRepository.save(owner));
    }

    public OwnerSingletonResponse addFavoriteCharacter(String ownerId, OwnerAddCharacterFavoriteRequest request){
        Owner owner = getOwnerById(ownerId);
        Adopt adopt = adoptRepository.findById(request.getAdoptId()).orElseThrow(
                () -> new BadRequestException("Adopt not found with id: " + request.getAdoptId())
        );

        Set<Adopt> oldFavoritesCharacters = owner.getFavoriteCharacters();

        if (oldFavoritesCharacters == null || oldFavoritesCharacters.isEmpty()){
            oldFavoritesCharacters = new HashSet<>();
            adopt.setFavoriteCharacterIndex(1);
            oldFavoritesCharacters.add(adopt);
            owner.setFavoriteCharacters(oldFavoritesCharacters);
        }else{
            if (oldFavoritesCharacters.contains(adopt)){
                throw new BadRequestException("The character is already in the favorites");
            }

            if(oldFavoritesCharacters.size() != 3){
                adopt.setFavoriteCharacterIndex(oldFavoritesCharacters.size() + 1);
                oldFavoritesCharacters.add(adopt);
                owner.setFavoriteCharacters(oldFavoritesCharacters);
            }

            if(oldFavoritesCharacters.size() == 3){
                oldFavoritesCharacters.forEach(favoriteCharacter -> {
                    if(favoriteCharacter.getFavoriteCharacterIndex() == 1){
                        favoriteCharacter.setFavoriteCharacterIndex(0);
                    }else{
                        favoriteCharacter.setFavoriteCharacterIndex(favoriteCharacter.getFavoriteCharacterIndex() - 1);
                    }
                });
                adopt.setFavoriteCharacterIndex(3);
                oldFavoritesCharacters.add(adopt);
                Set<Adopt> newFavoritesCharacters = oldFavoritesCharacters.stream()
                        .filter(favoriteCharacter -> favoriteCharacter.getFavoriteCharacterIndex() != 0).collect(Collectors.toSet());
                owner.setFavoriteCharacters(newFavoritesCharacters);
            }
        }
        return new OwnerSingletonResponse(BaseResponse.Status.SUCCESS, HttpStatus.OK.value(), ownerRepository.save(owner));
    }


}
