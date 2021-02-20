package server.dataDecoders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import server.models.PasswordResets;

public class PasswordResetsDecoder {
    String data;
    public PasswordResetsDecoder(String data){
        this.data = data;
    };

    public PasswordResets initiatePasswordResetDecode() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode dataDecrypt = objectMapper.readTree(data);
        return new PasswordResets(dataDecrypt.get("passwordResetEmail").asText(),dataDecrypt.get("passwordResetExpiryDate").asText());
    }

}
