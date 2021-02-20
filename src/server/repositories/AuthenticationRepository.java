package server.repositories;

import server.config.PostegresConfig;
import server.models.User;
import utils.Token;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.temporal.ChronoUnit;

/**
 * The type Authentication repository.
 */
public class AuthenticationRepository {
    private String token;

    /**
     * Gets token.
     *
     * @return the token
     */
    public String getToken() {
        return token;
    }

    /**
     * Sets token.
     *
     * @param token the token
     */
    public void setToken(String token) {
        this.token = token;
    }


    public String login(String username, String password){

        try{
            Connection connection = PostegresConfig.getConnection();
            String query = "SELECT * FROM users where username  = ? and  pass_word = ?;";

            PreparedStatement statement =  connection.prepareStatement(query);

            statement.setString(1,username);
            statement.setString(2,password);

            ResultSet userFound = statement.executeQuery();

            if(userFound.next()){
                Token token = new Token(userFound.getString("username"));
                this.setToken(token.generateToken(1, ChronoUnit.DAYS));

                return this.getToken();
            }
            else{
                System.out.println("Invalid credentials");
            }
        }
        catch ( Exception e ) {

            System.err.println( e.getClass().getName()+": "+ e.getMessage() );

            System.exit(0);

        }
        return null;
    }

//    public User currentUser()

    public static void main(String[] args){
        AuthenticationRepository au = new AuthenticationRepository();

        au.login("liberi","liberi");
    }
}
