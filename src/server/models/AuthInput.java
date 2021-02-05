package server.models;
import java.io.Serializable;
public class AuthInput implements Serializable{
    public String username;
    public String password;

    public AuthInput(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    @Override
    public String toString(){
        return "{ \"username\" : \""+this.username+"\", \"password\" : \""+this.password+"\" }";
    }
}
