package com.moviz.movizapi.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
/*import java.util.Arrays;*/
import java.util.List;

@Service
public class UserAuthService {

    @Autowired
    private UserRepo userrepo;
    /*private static List<User> Users = new ArrayList<>(Arrays.asList(
            new Use*/

    public List<User> getAllUsers() {
        //return Users;
        List<User> Users = new ArrayList<>();
        userrepo.findAll().forEach(Users::add);
        return Users;
    }
    public void addUser(User user) {
        //Users.add(user);
        userrepo.save(user);
    }

    public List<User> getUserbyEmail(String email) {
        return userrepo.findByEmail(email);
    }
    
    public boolean AuthUser(String email,String password){
        return userrepo.existsUserByEmailAndPassword(email,password);
    }
}
