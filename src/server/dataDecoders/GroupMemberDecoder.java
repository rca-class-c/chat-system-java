package server.dataDecoders;

import client.interfaces.AddMemberRequestData;
import client.interfaces.DeleteMemberRequestData;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import server.models.GroupMember;

import java.util.Arrays;

public class GroupMemberDecoder {
    String data;

    public GroupMemberDecoder(String data) {
        this.data = data;
    }

    public GroupMember createGroupMemberDecode() throws JsonProcessingException {
        ObjectMapper objectMapper=new ObjectMapper();
        JsonNode dataDecrypt = objectMapper.readTree(data);
        return new GroupMember(dataDecrypt.get("group_id").asInt(),dataDecrypt.get("user_id").asInt());
    }

    public AddMemberRequestData createGroupMembersDecoder() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode dataDecrypt = objectMapper.readTree(data);
        int group_id =  dataDecrypt.get("group_id").asInt();
        String values = dataDecrypt.get("users").asText();
        Integer [] userList = objectMapper.readValue(values, Integer[].class);
        return new AddMemberRequestData(group_id, Arrays.asList(userList));

    }

//    public DeleteMemberRequestData deleteGroupMemberDecoder() throws JsonProcessingException {
//        ObjectMapper objectMapper= new ObjectMapper();
//        JsonNode dataDecrypt=objectMapper.readTree(data);
//        return new DeleteMemberRequestData(dataDecrypt.get("group_id").asInt(),dataDecrypt.get("user_id").asInt());
//
//
//    }

    public GroupMember deleteGroupMemberDecoder() throws JsonProcessingException {
        ObjectMapper objectMapper= new ObjectMapper();
        JsonNode dataDecrypt=objectMapper.readTree(data);
        return new GroupMember(dataDecrypt.get("group_id").asInt(),dataDecrypt.get("user_id").asInt());
    }

}
