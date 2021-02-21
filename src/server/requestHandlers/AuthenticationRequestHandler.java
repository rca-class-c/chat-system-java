package server.requestHandlers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import server.dataDecoders.AuthenticationDecoder;
import server.models.Response;
import server.services.AuthenticationService;

import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Map;

/**
 * Authentication requests handler
 *
 * @author Ntwari Clarance Liberiste
 * @since 1.0
 */
public class AuthenticationRequestHandler {

    /**
     *handling user login
     *
     * @param data data needed to initiate password reset
     * @param writer writer instance
     * @param objectMapper object mapper instance
     * @throws JsonProcessingException json proccess exception
     * @throws SQLException sql exception
     * @author Ntwari Clarance Liberiste
     */
    public void handleLogin(String data, PrintWriter writer, ObjectMapper objectMapper) throws JsonProcessingException, SQLException {
        Map<String,String> authData = new AuthenticationDecoder(data).loginDecoder();

        String authToken = new AuthenticationService().login(authData.get("username"), authData.get("password"));

        if(authToken == null){
            System.out.println("invalid credentials");
            Response response = new Response(null,false);
            String responseAsString = objectMapper.writeValueAsString(response);
            writer.println(responseAsString);
        }else{
            System.out.println("logged in successfully");
            Response response = new Response(authToken,false);
            String responseAsString = objectMapper.writeValueAsString(response);
            writer.println(responseAsString);
        }
    }
}
