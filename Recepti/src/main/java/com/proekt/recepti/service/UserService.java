package com.proekt.recepti.service;

import com.proekt.recepti.model.UserModel;

import java.text.ParseException;
import java.util.Date;
import java.util.Optional;

public interface UserService {
    UserModel findIfExists(String username, String email);
    UserModel registerUser(String username, String email, String password, Date birthdate);
    UserModel findByUsername(String username);
    UserModel findByEmail(String email);
    UserModel save(UserModel user);

}
