package server.requestHandlers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import server.ChatServer;
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
            System.out.println("Users list is provided");
            writer.println(ResponseAsString);
        }

    }
}
