package com.pickfit.pickfit.oauth2.model.service;

import com.pickfit.pickfit.oauth2.model.dto.UserDTO;
import com.pickfit.pickfit.oauth2.model.entity.UserEntity;
import com.pickfit.pickfit.oauth2.model.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public UserEntity handleOAuth2Login(UserDTO userDTO) {
        Optional<UserEntity> existingUser = repository.findById(userDTO.getEmail());

        if (existingUser.isPresent()) {
            return existingUser.get();
        } else {
            UserEntity newUser = new UserEntity();
            newUser.setEmail(userDTO.getEmail());
            newUser.setName(userDTO.getName());
            newUser.setNickname(null);
            newUser.setPhoneNum(null);
            return repository.save(newUser);
        }
    }

    public UserEntity updateUserDetails(String email, String nickname, String phoneNum) {
        Optional<UserEntity> existingUser = repository.findById(email);

        if (existingUser.isPresent()) {
            UserEntity user = existingUser.get();
            user.setNickname(nickname);
            user.setPhoneNum(phoneNum);
            return repository.save(user);
        } else {
            throw new IllegalArgumentException("User not found with email: " + email);
        }
    }
}
