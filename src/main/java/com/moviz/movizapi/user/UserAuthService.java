package com.moviz.movizapi.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserAuthService {

    private UserRepo userrepo;
    String Salt = BCrypt.gensalt(10);
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    public UserAuthService(UserRepo userrepo) {
        this.userrepo = userrepo;
    }


    public List<User> getAllUsers() {
        List<User> Users = new ArrayList<>();
        userrepo.findAll().forEach(Users::add);
        return Users;
    }
    public void addUser(User user) {
        user.setPassword(BCrypt.hashpw(user.getPassword(), Salt));
        userrepo.save(user);
    }

    public boolean existsUserbyEmail(String email) {
        return userrepo.existsByEmail(email);
    }
    
    public boolean AuthUser(String email,String password){
        password = BCrypt.hashpw(password, Salt);
        return userrepo.existsUserByEmailAndPassword(email,password);
    }
}
