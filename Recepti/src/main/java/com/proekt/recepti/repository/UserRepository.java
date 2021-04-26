package com.proekt.recepti.repository;

import com.proekt.recepti.model.UserModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<UserModel, Long> {
    UserModel findByUsernameOrEmail(String username, String email);
    UserModel findByUsername(String username);
    UserModel findByEmail(String email);
}