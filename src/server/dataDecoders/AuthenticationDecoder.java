package server.dataDecoders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import server.models.PasswordResets;

/**
 * Authentication Decoder
 *
 * @author Ntwari Clarance Liberiste
 * @since 1.0
 */
public class AuthenticationDecoder {
    String data;
    public AuthenticationDecoder(String data){
        this.data = data;
    };


    /**
     *login decoder
     *
     * @return PasswordResets model
     * @throws JsonProcessingException a json proccessor exception
     * @author Ntwari Clarance Liberiste
     */
    public PasswordResets loginDecoder() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode dataDecrypt = objectMapper.readTree(data);
        return new PasswordResets(dataDecrypt.get("username").asText(),dataDecrypt.get("password").asText());
    }
}
