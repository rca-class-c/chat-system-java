package server.dataDecoders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import server.models.AuthInput;

public class LoginDataDecoder {
    String data;

    public LoginDataDecoder(String data) {
        this.data = data;
    }

    public AuthInput decode() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode dataDecrypt = objectMapper.readTree(data);
       return new AuthInput(dataDecrypt.get("username").asText(),dataDecrypt.get("password").asText());
    }
}
