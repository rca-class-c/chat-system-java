package server.requestHandlers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import server.ChatServer;
import server.dataDecoders.UserDecoder;
import server.models.Response;
import server.models.User;
import server.services.UserService;

import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;


public class UserRequestHandler {
    /**
     *Author: Didier Munezero
     *Description: This class is a handler that handles and directs requests to a given service methods for logging in and users
     */
    public void HandleLogin(String data, PrintWriter writer, ObjectMapper objectMapper, ChatServer server) throws JsonProcessingException, SQLException {
        User returned = new UserService().loginUser(new UserDecoder(data).LoginDecode());
        if(returned == null){

            Response response = new Response(null,false);
            String ResponseAsString = objectMapper.writeValueAsString(response);
            writer.println(ResponseAsString);
        }
        else{
            Response response = new Response(returned,true);
            String ResponseAsString = objectMapper.writeValueAsString(response);
            server.addUserName(returned.getUserID(),returned.getUsername());

            writer.println(ResponseAsString);
        }
    }
    /**
     * @AUTHOR: Shallon Kobusinge
     * The User request handler for updating profile
     * */
    public  void HandleProfileUpdate(String data, PrintWriter writer, ObjectMapper objectMapper) throws JsonProcessingException, SQLException {
        User decodedOne = new UserDecoder(data).UpdateUserDecode();
        User returned = new UserService().updateUser(decodedOne, decodedOne.getUserID());
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
    public void HandleRegister(String data, PrintWriter writer, ObjectMapper objectMapper) throws JsonProcessingException, SQLException {
        User returned = new UserService().saveUser(new UserDecoder(data).CreateUserDecode());
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

    public void HandleGetProfile(String data, PrintWriter writer, ObjectMapper objectMapper) throws JsonProcessingException,SQLException {
        User returned = new UserService().getUserById(new UserDecoder(data).GetProfileDecode());
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
    public void HandleActivateUser(String data, PrintWriter writer, ObjectMapper objectMapper) throws JsonProcessingException,SQLException {
        boolean returned = new UserService().ActivateUser(new UserDecoder(data).GetProfileDecode());
        if(!returned){
            Response response = new Response(null,false);
            String ResponseAsString = objectMapper.writeValueAsString(response);
            writer.println(ResponseAsString);
        }
        else{
            Response response = new Response(null,true);
            String ResponseAsString = objectMapper.writeValueAsString(response);
            writer.println(ResponseAsString);
        }
    }
    public void HandleDeActivateUser(String data, PrintWriter writer, ObjectMapper objectMapper) throws JsonProcessingException,SQLException {
        boolean returned = new UserService().DeActivateUser(new UserDecoder(data).GetProfileDecode());
        if(!returned){
            Response response = new Response(null,false);
            String ResponseAsString = objectMapper.writeValueAsString(response);
            writer.println(ResponseAsString);
        }
        else{
            Response response = new Response(null,true);
            String ResponseAsString = objectMapper.writeValueAsString(response);
            writer.println(ResponseAsString);
        }
    }
    public void HandleUsersList(String data, PrintWriter writer, ObjectMapper objectMapper) throws JsonProcessingException,SQLException {
        List<User> users = new UserService().getAllOtherUsers(new UserDecoder(data).GetProfileDecode());
        if(users == null){
            Response response = new Response(null,false);
            String ResponseAsString = objectMapper.writeValueAsString(response);
            writer.println(ResponseAsString);
        }
        else{
            Response response = new Response(users,true);
            String ResponseAsString = objectMapper.writeValueAsString(response);;
            writer.println(ResponseAsString);
        }
    }
    public void HandleInactiveUsersList(String data, PrintWriter writer, ObjectMapper objectMapper) throws JsonProcessingException,SQLException {
        List<User> users = new UserService().getAllInactiveUsers();
        //User returned = new UserService().getUserById(new UserDecoder(data).GetProfileDecode());
        if(users == null){
            Response response = new Response(null,false);
            String ResponseAsString = objectMapper.writeValueAsString(response);
            writer.println(ResponseAsString);
        }
        else{
            Response response = new Response(users,true);
            String ResponseAsString = objectMapper.writeValueAsString(response);;
            writer.println(ResponseAsString);
        }
    }
    public  void HandlerSearchUser(String data, PrintWriter writer, ObjectMapper objectMapper) throws JsonProcessingException, SQLException {
        List<User> users = new UserService().SearchUsers(new UserDecoder(data).GetSearchDecode());
        if(users == null){
            Response response = new Response(null,false);
            String ResponseAsString = objectMapper.writeValueAsString(response);
            writer.println(ResponseAsString);
        }
        else{
            Response response = new Response(users,true);
            String ResponseAsString = objectMapper.writeValueAsString(response);
            writer.println(ResponseAsString);
        }

    }
}
