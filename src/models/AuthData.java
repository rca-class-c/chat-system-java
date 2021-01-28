package models;

import java.util.Objects;

public class AuthData {
    public models.User user;
    public String token;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AuthData)) return false;
        AuthData authData = (AuthData) o;
        return getUser().equals(authData.getUser()) && getToken().equals(authData.getToken());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUser(), getToken());
    }
}
