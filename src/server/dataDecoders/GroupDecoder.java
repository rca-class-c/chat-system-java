package server.dataDecoders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import server.models.Group;

/**
 *Description: This class is a decoder of all data sent to group service
 @author Didier Munezero
 */
public class GroupDecoder {

    String data;
    public GroupDecoder(String data) {
        this.data = data;
    }

    public Group CreateGroupDecode() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode dataDecrypt = objectMapper.readTree(data);
        return new Group(dataDecrypt.get("name").asText(),dataDecrypt.get("description").asText(),dataDecrypt.get("creator").asInt());
    }

    public Group UpdateGroupDecode() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode dataDecrypt = objectMapper.readTree(data);
        return new Group(dataDecrypt.get("id").asInt(),dataDecrypt.get("name").asText(),dataDecrypt.get("description").asText(),dataDecrypt.get("creator").asInt());
    }

    public String getRecentGroups() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode dataDecrypt = objectMapper.readTree(data);
        return dataDecrypt.get("search").asText();
    }

    public int getGroupsById() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode dataDecrypt = objectMapper.readTree(data);
        return dataDecrypt.get("id").asInt();
    }

    public int deleteGroupDecoder() throws JsonProcessingException {
        ObjectMapper objectMapper= new ObjectMapper();
        JsonNode dataDecrypt=objectMapper.readTree(data);
        return dataDecrypt.get("id").asInt();
    }
}
