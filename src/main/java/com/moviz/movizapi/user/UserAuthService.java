package com.moviz.movizapi.user;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
public class UserAuthService {

    private UserRepository UserRepo;
    String Salt = BCrypt.gensalt(10);
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    public UserAuthService(UserRepository UserRepo) {
        this.UserRepo = UserRepo;
    }
    public boolean existsUserbyEmail(String email) {
        return UserRepo.findUserByEmail(email).isPresent();
    }
    public Response AuthUser(String email,String password){

        Optional<User> QueryRes = UserRepo.findUserByEmail(email);
        if(QueryRes.isPresent()) {

            if(passwordEncoder.matches(password , QueryRes.get().getPassword())) {
                Response res = new Response();
                res.setStatus(true);
                res.setMessage("Valid Login");
                return res;
            }
            else {
                Response res = new Response();
                res.setStatus(false);
                res.setMessage("Invalid Login!");
                return res;
            }
        }

        else {
            Response res = new Response();
            res.setStatus(false);
            res.setMessage("User Does not exist");
            return res;
        }
    }
    public void addUser(User user) {

        user.setPassword(BCrypt.hashpw(user.getPassword(), Salt));
        user.setEmail(user.getEmail().toLowerCase(Locale.ROOT));
        UserRepo.insert(user);
    }
    public List<User> getAllUsers() {
        List<User> Users = new ArrayList<>();
        UserRepo.findAll().forEach(Users::add);
        return Users;
    }

}
