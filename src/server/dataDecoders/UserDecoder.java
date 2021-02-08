package server.dataDecoders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import server.models.AuthInput;
import server.models.User;


/**
 * User related request data decoder
 * @author: Didier Munezero
 */
public class UserDecoder {

    String data;
    public UserDecoder(String data) {
        this.data = data;
    }
    public AuthInput LoginDecode() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode dataDecrypt = objectMapper.readTree(data);
        return new AuthInput(dataDecrypt.get("username").asText(),dataDecrypt.get("password").asText());
    }

    public int GetProfileDecode() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode dataDecrypt = objectMapper.readTree(data);
        return dataDecrypt.get("id").asInt();
    }

    public User CreateUserDecode() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode dataDecrypt = objectMapper.readTree(data);
        return new User(dataDecrypt.get("fname").asText(),dataDecrypt.get("lname").asText(),dataDecrypt.get("password").asText(),dataDecrypt.get("email").asText(),dataDecrypt.get("dob").asText(),dataDecrypt.get("username").asText(),dataDecrypt.get("gender").asText(),dataDecrypt.get("categoryID").asInt(),dataDecrypt.get("status").asText());
    }
    public User UpdateUserDecode() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode dataDecrypt = objectMapper.readTree(data);
        return new User(dataDecrypt.get("user_id").asInt(),dataDecrypt.get("fname").asText(),dataDecrypt.get("lname").asText(),dataDecrypt.get("password").asText(),dataDecrypt.get("email").asText(),dataDecrypt.get("dob").asText(),dataDecrypt.get("username").asText(),dataDecrypt.get("gender").asText(),dataDecrypt.get("categoryID").asInt(),dataDecrypt.get("status").asText());
    }
    public String GetSearchDecode() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode dataDecrypt = objectMapper.readTree(data);
        return dataDecrypt.get("search_data").asText();
    }
}
