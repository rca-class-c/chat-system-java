package server.dataDecoders;

import client.interfaces.AddMemberRequestData;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import server.models.GroupMember;

import java.util.Arrays;
/*
* @AUTHOR: Kobusinge Shallon
* */
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
        String values = dataDecrypt.get("users").toString();
        Integer[] userList = objectMapper.readValue(values, Integer[].class);
        return new AddMemberRequestData(group_id, userList);
    }

    public GroupMember deleteGroupMemberDecoder() throws JsonProcessingException {
        ObjectMapper objectMapper= new ObjectMapper();
        JsonNode dataDecrypt=objectMapper.readTree(data);
        int group_id =  dataDecrypt.get("group_id").asInt();
        int user_id = dataDecrypt.get("user_id").asInt();
        return new GroupMember(group_id,user_id);
    }
    public int getGroupMembersDecoder() throws JsonProcessingException{
        ObjectMapper objectMapper= new ObjectMapper();
        JsonNode dataDecrypt=objectMapper.readTree(data);
        int group_id = dataDecrypt.get("group_id").asInt();
        return  group_id;
    }

}
