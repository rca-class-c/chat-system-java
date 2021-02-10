package client.interfaces;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import server.models.User;

public class UserResponseDataDecoder {

    Object data;
    String success;

    public Object getData() {
        return data;
    }

    public String getSuccess() {
        return success;
    }

    public ResponseDataSuccessDecoder decodedResponse(String response) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode data = objectMapper.readTree(response);


        return new ResponseDataSuccessDecoder(data.get("data").toString(),data.get("success").asBoolean());
    }

    public User returnUserDecoded(String data)throws JsonProcessingException{
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode value = objectMapper.readTree(data);
        return new User(value.get("userID").asInt(),value.get("fname").asText(),value.get("lname").asText(),value.get("password").asText(),value.get("email").asText(),value.get("dob").asText(),value.get("username").asText(),value.get("gender").asText(),value.get("categoryID").asInt(),value.get("status").asText());
    }
    public User[] returnUsersListDecoded(String data)throws JsonProcessingException{
        ObjectMapper objectMapper = new ObjectMapper();

        User[] users = objectMapper.readValue(data, User[].class);
        return users;
    }
}
