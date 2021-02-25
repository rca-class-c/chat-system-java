package server.requestHandlers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import server.ChatServer;
import server.dataDecoders.GroupMemberDecoder;
import server.dataDecoders.UserDecoder;
import server.models.GroupMember;
import server.models.Response;
import server.models.User;
import server.services.GroupMemberService;
import server.services.GroupServices;
import server.services.UserService;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
/**
 * @AUTHOR: Gahamanyi YVette
 * */
public class GroupMemberRequestHandler {
    public void handlerCreateGroupMembers(String data, PrintWriter writer, ObjectMapper objectMapper) throws JsonProcessingException, SQLException {
        GroupMember returned = new GroupMemberService().saveGroupMember(new GroupMemberDecoder(data).createGroupMemberDecode());
        if (returned == null){
            Response response= new Response(null,false);
            String ResponseAsString= objectMapper.writeValueAsString(response);
            writer.println(ResponseAsString);
        }
        else{
            Response response = new Response(returned,true);
            String ResponseAsString= objectMapper.writeValueAsString(response);
            writer.println(ResponseAsString);
        }
    }

    public void handlerGetGroupMembers(String data, PrintWriter writer, ObjectMapper objectMapper) throws JsonProcessingException, SQLException {
        List<User> groupMembers=new GroupMemberService().listGroupMembers(new UserDecoder(data).GetProfileDecode());
        if (groupMembers == null){
            Response response= new Response(null,false);
            String ResponseAsString = objectMapper.writeValueAsString(response);
            writer.println(ResponseAsString);
        }
        else{
            Response response = new Response(groupMembers,true);
            String ResponseAsString = objectMapper.writeValueAsString(response);
            writer.println(ResponseAsString);
        }
    }

    public void handleDeleteGroupMember(String data, PrintWriter writer, ObjectMapper objectMapper) throws JsonProcessingException, SQLException {
        boolean returned = new GroupMemberService().deleteMember(new GroupMemberDecoder(data).deleteGroupMemberDecoder());
        if(!returned){
            Response response = new Response(null,false);
            String ResponseAsString = objectMapper.writeValueAsString(response);
            writer.println(ResponseAsString);
        }
        else{
            Response response = new Response(true,true);
            String ResponseAsString = objectMapper.writeValueAsString(response);
            writer.println(ResponseAsString);
        }
    }

    public void handleCreateGroupMembers(String data, PrintWriter writer, ObjectMapper objectMapper) throws IOException, SQLException{
        int[] returned = new GroupMemberService().createMembers(new GroupMemberDecoder(data).createGroupMembersDecoder());

        if(returned == null){
            Response response = new Response(null,false);
            String ResponseAsString = objectMapper.writeValueAsString(response);
            writer.println(ResponseAsString);
        }
        else{
            Response response = new Response(returned,true);
            String ResponseAsString = objectMapper.writeValueAsString(response);
            writer.println(ResponseAsString);
        }
    }

}