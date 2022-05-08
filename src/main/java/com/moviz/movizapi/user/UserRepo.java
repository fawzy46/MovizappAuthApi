package com.moviz.movizapi.user;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepo extends CrudRepository<User, Long> {

    List<User> findByEmail(String email);
    boolean existsUserByEmailAndPassword(String email,String password);

}
