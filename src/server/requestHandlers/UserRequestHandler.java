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


/**
 * User related request handler
 * @author: Didier Munezero
 */
public class UserRequestHandler {

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
            System.out.println("Account not updated");
            Response response = new Response(null,false);
            String ResponseAsString = objectMapper.writeValueAsString(response);
            writer.println(ResponseAsString);
        }
        else{
            Response response = new Response(returned,true);
            String ResponseAsString = objectMapper.writeValueAsString(response);
            System.out.println(returned.getUsername()+" updated his account!");
            writer.println(ResponseAsString);
        }
    }
    public void HandleRegister(String data, PrintWriter writer, ObjectMapper objectMapper) throws JsonProcessingException, SQLException {
        User returned = new UserService().saveUser(new UserDecoder(data).CreateUserDecode());
        if(returned == null){
            System.out.println("Account not created");
            Response response = new Response(null,false);
            String ResponseAsString = objectMapper.writeValueAsString(response);
            writer.println(ResponseAsString);
        }
        else{
            Response response = new Response(returned,true);
            String ResponseAsString = objectMapper.writeValueAsString(response);
            System.out.println(returned.getUsername()+" created an account!");
            writer.println(ResponseAsString);
        }
    }

    public void HandleGetProfile(String data, PrintWriter writer, ObjectMapper objectMapper) throws JsonProcessingException,SQLException {
        User returned = new UserService().getUserById(new UserDecoder(data).GetProfileDecode());
        if(returned == null){
            System.out.println("Account not found");
            Response response = new Response(null,false);
            String ResponseAsString = objectMapper.writeValueAsString(response);
            writer.println(ResponseAsString);
        }
        else{
            Response response = new Response(returned,true);
            String ResponseAsString = objectMapper.writeValueAsString(response);
            System.out.println(response);
            System.out.println(returned.getUsername()+" requested profile");
            writer.println(ResponseAsString);
        }
    }
    public void HandleUsersList(String data, PrintWriter writer, ObjectMapper objectMapper) throws JsonProcessingException,SQLException {
        List<User> users = new UserService().getAllOtherUsers(new UserDecoder(data).GetProfileDecode());
        //User returned = new UserService().getUserById(new UserDecoder(data).GetProfileDecode());
        if(users == null){
            System.out.println("Query failed recheck your db");
            Response response = new Response(null,false);
            String ResponseAsString = objectMapper.writeValueAsString(response);
            writer.println(ResponseAsString);
        }
        else{
            Response response = new Response(users,true);
            String ResponseAsString = objectMapper.writeValueAsString(response);
            System.out.println(ResponseAsString);
            System.out.println("Users list is provided");
            writer.println(ResponseAsString);
        }
    }
    public  void HandlerSearchUser(String data, PrintWriter writer, ObjectMapper objectMapper) throws JsonProcessingException, SQLException {
        List<User> users = new UserService().SearchUsers(new UserDecoder(data).GetSearchDecode());
        if(users == null){
            System.out.println("Query failed recheck your db");
            Response response = new Response(null,false);
            String ResponseAsString = objectMapper.writeValueAsString(response);
            writer.println(ResponseAsString);
        }
        else{
            Response response = new Response(users,true);
            String ResponseAsString = objectMapper.writeValueAsString(response);
            System.out.println(ResponseAsString);
            System.out.println("Users list is provided");
            writer.println(ResponseAsString);
        }

    }
}
