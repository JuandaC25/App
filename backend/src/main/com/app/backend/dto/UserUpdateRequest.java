package com.app.backend.dto;

import com.app.backend.model.User;

public class UserUpdateRequest{
    private String username;
    private String email;
    private String password;
    private String role;
    private String active;

    public UserUpdateRequest(){
    }

    public String getUsername(){
        return username;
    }

    public String setUsername(String username){
        this.username = username;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getRole(){
        return role;
    }

    public void setRole(String role){
        this.role = role;
    }

    public String getActive(){
        return active;
    }

    public void setActive(String active){
        this.active = active;
    }
}