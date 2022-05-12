package com.moviz.movizapi.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;

@RestController
@CrossOrigin(origins = "*")
public class UserAuthController {

    private UserAuthService userauthservice;

    public UserAuthController(UserAuthService userauthservice) {
        this.userauthservice = userauthservice;
    }

    @RequestMapping(method = RequestMethod.GET, value ="/users")
    public List<User> getAllUser() {
        return userauthservice.getAllUsers();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/auth")
    public Response AuthUser(@RequestBody Credentials user) {
        return userauthservice.AuthUser(user.getEmail().toLowerCase(Locale.ROOT),user.getPassword());
    }

    @RequestMapping( method = RequestMethod.GET, value = "/users/{email}")
    public boolean existsUserbyEmail(@PathVariable String email) {
        return userauthservice.existsUserbyEmail(email);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/signup")
    public ResponseEntity<Boolean> addUser(@RequestBody User user) {
        if(!existsUserbyEmail(user.getEmail().toLowerCase(Locale.ROOT))) {
            userauthservice.addUser(user);
            return new ResponseEntity<Boolean>(true, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<Boolean>(false, HttpStatus.OK);
        }
    }

}
