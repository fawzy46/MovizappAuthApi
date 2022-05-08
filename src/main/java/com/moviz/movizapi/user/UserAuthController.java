package com.moviz.movizapi.user;

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class UserAuthController {

    private UserAuthService userauthservice;

    public UserAuthController(UserAuthService userauthservice) {
        this.userauthservice = userauthservice;
    }

    @RequestMapping("/users")
    public List<User> getAllUser() {
        return userauthservice.getAllUsers();
    }

    @RequestMapping("/auth")
    public boolean AuthUser(@RequestBody User user) {
        return userauthservice.AuthUser(user.getEmail(),user.getPassword());
    }

    @RequestMapping("/users/{email}")
    public boolean existsUserbyEmail( @PathVariable String email) {
        return userauthservice.existsUserbyEmail(email);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/signup")
    public ResponseEntity<Boolean> addUser(@RequestBody User user) {
        if(!existsUserbyEmail(user.getEmail())) {
            userauthservice.addUser(user);
            return new ResponseEntity<Boolean>(true, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<Boolean>(false, HttpStatus.OK);
        }
    }

}
