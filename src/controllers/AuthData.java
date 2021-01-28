package controllers;

import models.User;

import java.util.Objects;

public class AuthData {
    public  String token;
    public models.User user;

    public String getToken() {
        return token;
    }

    public AuthData(String token, User user) {
        this.token = token;
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AuthData)) return false;
        AuthData authData = (AuthData) o;
        return getToken().equals(authData.getToken()) && getUser().equals(authData.getUser());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getToken(), getUser());
    }

    public void setToken(String token) {
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
