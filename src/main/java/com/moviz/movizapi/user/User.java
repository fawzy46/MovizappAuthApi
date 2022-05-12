package com.moviz.movizapi.user;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/*import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;*/

//@Entity
@Document
public class User {
    @Id
    private  String id;
    private String name;
    private  String email;
    private String password;
    /*private  String token;*/

    public User(String name, String email, String password/*, String token*/) {
        this.name = name;
        this.email = email;
        this.password = password;
        /*this.token = token;*/
    }

    public User() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    /*public String getToken() {
        return token;
    }*/

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /*public void setToken(String token) {
        this.token = token;
    }*/
}
