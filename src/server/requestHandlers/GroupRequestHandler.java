package server.requestHandlers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import server.ChatServer;
import server.dataDecoders.GroupDecoder;
import server.dataDecoders.UserDecoder;
import server.models.Group;
import server.models.Response;
import server.services.GroupServices;

import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

/**
 * This class is a handler that handles and directs requests to a given service methods for groups everything
 *@author Didier Munezero
 */
public class GroupRequestHandler {

    public  void HandlerSearchGroup(String data, PrintWriter writer, ObjectMapper objectMapper) throws JsonProcessingException, SQLException {
        List<Group> messages = new GroupServices().SearchGroups(new UserDecoder(data).GetSearchDecode());

        if(messages == null){

            Response response = new Response(null,false);
            String ResponseAsString = objectMapper.writeValueAsString(response);
            writer.println(ResponseAsString);
        }
        else{
            Response response = new Response(messages,true);
            String ResponseAsString = objectMapper.writeValueAsString(response);
            writer.println(ResponseAsString);
        }

    }
    public void HandleCreateGroup(String data, PrintWriter writer, ObjectMapper objectMapper) throws JsonProcessingException, SQLException {
        Group returned = new GroupServices().createGroup(new GroupDecoder(data).CreateGroupDecode());
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

    public  void HandleGroupUpdate(String data, PrintWriter writer, ObjectMapper objectMapper) throws JsonProcessingException, SQLException {
        Group decodedOne = new GroupDecoder(data).UpdateGroupDecode();
        Group returned = new GroupServices().updateGroup(decodedOne);
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

    public void HandleGetAllGroups(String data, PrintWriter writer, ObjectMapper objectMapper) throws JsonProcessingException, SQLException {
        List<Group> messages = new GroupServices().getAllGroups();
        if(messages == null){
            Response response = new Response(null,false);
            String ResponseAsString = objectMapper.writeValueAsString(response);
            writer.println(ResponseAsString);
        }
        else{
            Response response = new Response(messages,true);
            String ResponseAsString = objectMapper.writeValueAsString(response);
            writer.println(ResponseAsString);
        }

    }
    public void HandleGetGroup(String data, PrintWriter writer, ObjectMapper objectMapper) throws JsonProcessingException,SQLException {
        Group returned = new GroupServices().getGroupById(new UserDecoder(data).GetProfileDecode());
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
    public void HandleDeleteGroup(String data, PrintWriter writer, ObjectMapper objectMapper) throws JsonProcessingException, SQLException {
        boolean returned = new GroupServices().deleteGroup(new UserDecoder(data).GetProfileDecode());
        if(!returned){
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
