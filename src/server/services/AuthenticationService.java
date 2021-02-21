package server.services;

import server.repositories.AuthenticationRepository;

/**
 * authentication service
 *
 * @author Ntwari Clarance Liberiste
 * @since 1.0
 */
public class AuthenticationService {

    private final AuthenticationRepository authRepository  = new AuthenticationRepository();


    /**
     * Gets token.
     *
     * @return the token
     * @author Ntwari Clarance Liberiste
     */
    public String getToken() {
        return authRepository.getToken();
    }

    /**
     * Sets token.
     *
     * @param token the token
     * @author Ntwari Clarance Liberiste
     */
    public void setToken(String token) {
        authRepository.setToken(token);
    }


    /**
     * login with user credentials
     *
     * @param username username of the user who is going to log in
     * @param password password of the user
     * @return token as String or null when credentials are not verified
     * @author Ntwari Clarance Liberiste
     */
    public String login(String username, String password){
        return authRepository.login(username, password);
    }


    /**
     * check if token is valid token, it may be invalid due to that it is invalid or it has expired
     *
     * @author Ntwari clarance Liberiste
     * @return true when it is valid, false when it is invalid
     */
    public boolean isAuthTokenValid(){
        return authRepository.isAuthTokenValid();
    }
}
