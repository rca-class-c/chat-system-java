package server.repositories;

import server.config.PostegresConfig;
import utils.Token;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.temporal.ChronoUnit;

/**
 * The type Authentication repository.
 *
 * @author Ntwari Clarance Liberiste
 * @since 1.0
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


    /**
     * login with user credentials
     *
     * @param username username of the user who is going to log in
     * @param password password of the user
     * @return token as String or null when credentials are not verified
     */
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


    /**
     * check if token is valid token, it may be invalid due to that it is invalid or it has expired
     *
     * @author Ntwari clarance Liberiste
     * @return true when it is valid, false when it is invalid
     */
    public boolean isAuthTokenValid(){
        return new Token().isValidToken(this.token);
    }
}
