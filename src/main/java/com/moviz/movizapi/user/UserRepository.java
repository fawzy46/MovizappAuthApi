package com.moviz.movizapi.user;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, Long> {
    Optional<User> findUserByEmail(String email);
    Optional<User> findUserByPassword(String password);
    Optional<User> findUserByEmailAndPassword(String email, String password);
}
