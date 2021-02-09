package server.dataDecoders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import server.models.GroupMember;

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

    public int getGroupMembers() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode dataDecrypt = objectMapper.readTree(data);
        return dataDecrypt.get("group_id").asInt();
    }

    public GroupMember deleteGroupMemberDecoder() throws JsonProcessingException {
        ObjectMapper objectMapper= new ObjectMapper();
        JsonNode dataDecrypt=objectMapper.readTree(data);
        return new GroupMember(dataDecrypt.get("group_id").asInt(),dataDecrypt.get("user_id").asInt());
    }

}
