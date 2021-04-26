package com.proekt.recepti.service.impl;

import com.proekt.recepti.model.UserModel;
import com.proekt.recepti.repository.UserRepository;
import com.proekt.recepti.service.UserService;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Date;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserModel findIfExists(String username, String email) {
        UserModel findUser = this.userRepository.findByUsernameOrEmail(username, email);
        if (findUser != null) return findUser;
        return null;
    }

    @Override
    public UserModel registerUser(String username, String email, String password, Date birthdate) {
        UserModel createUser = new UserModel(username, email, password, birthdate);
        this.userRepository.save(createUser);
        return createUser;
    }

    @Override
    public UserModel findByUsername(String username) {
        return this.userRepository.findByUsername(username);
    }

    @Override
    public UserModel findByEmail(String email) {
        return this.userRepository.findByEmail(email);
    }

    @Override
    public UserModel save(UserModel user) {
        return this.userRepository.save(user);
    }
}
