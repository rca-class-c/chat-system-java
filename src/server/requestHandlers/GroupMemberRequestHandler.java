package server.requestHandlers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import server.ChatServer;
import server.dataDecoders.GroupMemberDecoder;
import server.dataDecoders.UserDecoder;
import server.models.GroupMember;
import server.models.Response;
import server.services.GroupMemberService;
import server.services.GroupServices;

import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

public class GroupMemberRequestHandler {
    public void handlerCreateGroupMember(String data, PrintWriter writer, ObjectMapper objectMapper, ChatServer server) throws JsonProcessingException, SQLException {
        GroupMember returned = new GroupMemberService().saveGroupMember(new GroupMemberDecoder(data).createGroupMemberDecode());
        if (returned == null){
            System.out.println("Group not created");
            Response response= new Response(null,false);
            String ResponseAsString= objectMapper.writeValueAsString(response);
            writer.println(ResponseAsString);
        }
        else{
            Response response = new Response(returned,true);
            String ResponseAsString= objectMapper.writeValueAsString(response);
            System.out.println(returned.getMember_id()+" is created");
            writer.println(ResponseAsString);
        }
    }

    public void handlerGetGroupMembers(String data, PrintWriter writer, ObjectMapper objectMapper, ChatServer server) throws JsonProcessingException, SQLException {
        List<GroupMember> groupMembers=new GroupMemberService().listGroupMembers(new GroupMemberDecoder(data).getGroupMembers());
        if (groupMembers == null){
            System.out.println("query failed recheck your db");
            Response response= new Response(null,false);
            String ResponseAsString = objectMapper.writeValueAsString(response);
            writer.println(ResponseAsString);
        }
        else{
            Response response = new Response(groupMembers,true);
            String ResponseAsString = objectMapper.writeValueAsString(response);
            System.out.println(ResponseAsString);
            System.out.println("Group list is provided");
            writer.println(ResponseAsString);
        }
    }

    public void HandleDeleteGroupMember(String data, PrintWriter writer, ObjectMapper objectMapper, ChatServer server) throws JsonProcessingException, SQLException {
        boolean returned = new GroupMemberService().deleteMember(new GroupMemberDecoder(data).deleteGroupMemberDecoder());
        if(!returned){
            System.out.println("GroupMember not found");
            Response response = new Response(null,false);
            String ResponseAsString = objectMapper.writeValueAsString(response);
            writer.println(ResponseAsString);
        }
        else{
            Response response = new Response(returned,true);
            String ResponseAsString = objectMapper.writeValueAsString(response);
            System.out.println(response);
            System.out.println(" GroupMember is deleted");
            writer.println(ResponseAsString);
        }
    }

}
