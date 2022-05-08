package com.moviz.movizapi.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class UserAuthController {

    @Autowired
    private UserAuthService userauthservice;
    @RequestMapping("/users")
    public List<User> getAllUser() {
        return userauthservice.getAllUsers();
    }

    @RequestMapping("/auth")
    public boolean AuthUser(@RequestBody User user) {
        return userauthservice.AuthUser(user.getEmail(),user.getPassword());
    }

    @RequestMapping("/users/{email}")
    public List<User> getUserbyEmail( @PathVariable String email) {
        return userauthservice.getUserbyEmail(email);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/signup")
    public void  addUser(@RequestBody User user) {
        userauthservice.addUser(user);
    }

}
