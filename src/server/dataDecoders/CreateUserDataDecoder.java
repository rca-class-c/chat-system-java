package server.dataDecoders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import server.models.AuthInput;
import server.models.User;


public class CreateUserDataDecoder {
    String data;
    public CreateUserDataDecoder(String data) {
        this.data = data;
    }

    public User decode() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println(data);
        JsonNode dataDecrypt = objectMapper.readTree(data);
        return new User(dataDecrypt.get("fname").asText(),dataDecrypt.get("lname").asText(),dataDecrypt.get("password").asText(),dataDecrypt.get("email").asText(),dataDecrypt.get("dob").asText(),dataDecrypt.get("username").asText(),dataDecrypt.get("gender").asText(),dataDecrypt.get("categoryID").asInt(),dataDecrypt.get("status").asText());
    }
}
