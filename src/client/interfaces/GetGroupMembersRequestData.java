package client.interfaces;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import server.models.GroupMember;

public class GetGroupMembersRequestData {
    int group_id;

    public GetGroupMembersRequestData(int group_id) {
        this.group_id = group_id;
    }

    public int getGroup_id() {
        return group_id;
    }

    public void setGroup_id(int group_id) {
        this.group_id = group_id;
    }

    public GroupMember[] returnGroupMemberListDecoded(String data) throws JsonProcessingException {
        ObjectMapper objectMapper=new ObjectMapper();

        GroupMember[] groupMembers= objectMapper.readValue(data,GroupMember[].class);
        return groupMembers;
    }
}
