package server.requestHandlers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import server.ChatServer;
import server.dataDecoders.GroupDecoder;
import server.dataDecoders.UserDecoder;
import server.models.Group;
import server.models.Response;
import server.models.User;
import server.services.GroupServices;
import server.services.UserService;

import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

public class GroupRequestHandler {
    public  void HandlerSearchGroup(String data, PrintWriter writer, ObjectMapper objectMapper, ChatServer server) throws JsonProcessingException, SQLException {
        List<Group> messages = new GroupServices().SearchGroups(new UserDecoder(data).GetSearchDecode());
        if(messages == null){
            System.out.println("Query failed recheck your db");
            Response response = new Response(null,false);
            String ResponseAsString = objectMapper.writeValueAsString(response);
            writer.println(ResponseAsString);
        }
        else{
            Response response = new Response(messages,true);
            String ResponseAsString = objectMapper.writeValueAsString(response);
            System.out.println(ResponseAsString);
            System.out.println("Search Group list is provided");
            writer.println(ResponseAsString);
        }

    }
    public void HandleCreateGroup(String data, PrintWriter writer, ObjectMapper objectMapper, ChatServer server) throws JsonProcessingException, SQLException {
        Group returned = new GroupServices().create(new GroupDecoder(data).CreateGroupDecode());
        if(returned == null){
            System.out.println("group not created");
            Response response = new Response(null,false);
            String ResponseAsString = objectMapper.writeValueAsString(response);
            writer.println(ResponseAsString);
        }
        else{
            Response response = new Response(returned,true);
            String ResponseAsString = objectMapper.writeValueAsString(response);
            System.out.println(returned.getName()+" is created");
            writer.println(ResponseAsString);
        }
    }
    public  void HandleGroupUpdate(String data, PrintWriter writer, ObjectMapper objectMapper, ChatServer server) throws JsonProcessingException, SQLException {
        Group decodedOne = new GroupDecoder(data).UpdateGroupDecode();
        Group returned = new GroupServices().update(decodedOne);
        if(returned == null){
            System.out.println("Account not updated");
            Response response = new Response(null,false);
            String ResponseAsString = objectMapper.writeValueAsString(response);
            writer.println(ResponseAsString);
        }
        else{
            Response response = new Response(returned,true);
            String ResponseAsString = objectMapper.writeValueAsString(response);
            System.out.println(returned.getName()+" updated ");
            writer.println(ResponseAsString);
        }
    }
    public void HandleGetAllGroups(String data, PrintWriter writer, ObjectMapper objectMapper, ChatServer server) throws JsonProcessingException, SQLException {
        List<Group> messages = new GroupServices().getAll();
        if(messages == null){
            System.out.println("Query failed recheck your db");
            Response response = new Response(null,false);
            String ResponseAsString = objectMapper.writeValueAsString(response);
            writer.println(ResponseAsString);
        }
        else{
            Response response = new Response(messages,true);
            String ResponseAsString = objectMapper.writeValueAsString(response);
            System.out.println(ResponseAsString);
            System.out.println("Group list is provided");
            writer.println(ResponseAsString);
        }

    }
    public void HandleGetGroup(String data, PrintWriter writer, ObjectMapper objectMapper, ChatServer server) throws JsonProcessingException,SQLException {
        Group returned = new GroupServices().get(new UserDecoder(data).GetProfileDecode());
        if(returned == null){
            System.out.println("Group not found");
            Response response = new Response(null,false);
            String ResponseAsString = objectMapper.writeValueAsString(response);
            writer.println(ResponseAsString);
        }
        else{
            Response response = new Response(returned,true);
            String ResponseAsString = objectMapper.writeValueAsString(response);
            System.out.println(response);
            System.out.println(returned.getName()+" is being detailed");
            writer.println(ResponseAsString);
        }
    }
}