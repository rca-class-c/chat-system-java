package client.interfaces;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import server.models.AuthInput;

public class DecodeResponse {

    Object data;
    String success;

    public Object getData() {
        return data;
    }

    public String getSuccess() {
        return success;
    }

    public ResponseDecoded decodedResponse(String response) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode data = objectMapper.readTree(response);

        return new ResponseDecoded(data.get("data").toString(),data.get("success").asBoolean());
    }
}
