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

public class GroupRequestHandler {
    public void HandleGroupsList(String data, PrintWriter writer, ObjectMapper objectMapper, ChatServer server) throws JsonProcessingException, SQLException {
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
            System.out.println("Groups list is provided");
            writer.println(ResponseAsString);
        }
    }
}
