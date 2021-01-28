package controllers;

import models.AuthData;

public class User {

    public models.User user;
    public models.AuthData auth_data;
    public void createUser(models.User user){
        this.user  = user;
    }
    public models.User getUsers(){
        return user;
    }
    public models.User getUser(){
        return user;
    }
    public models.User updateUser(){
        return user;
    }
    public models.User deleteUser(){
        return user;
    }
    public AuthData login(){
        return auth_data;
    }
}
