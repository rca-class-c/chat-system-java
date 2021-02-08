package server.requestHandlers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import server.ChatServer;
import server.dataDecoders.MessageDecoder;
import server.dataDecoders.UserDecoder;
import server.models.Messages;
import server.models.Response;
import server.models.User;
import server.services.MessagesService;
import server.services.UserService;
import utils.DirectMessage;

import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

public class MessageRequestHandler {

    public void HandleMessageBetweenTwo(String data, PrintWriter writer, ObjectMapper objectMapper, ChatServer server) throws JsonProcessingException, SQLException {
        List<DirectMessage> messagesList = new MessagesService().viewDirectMessagesBetweenTwo(new MessageDecoder(data).returnChatMembers());
        if(messagesList == null){
            System.out.println("Query failed recheck your db");
            Response response = new Response(null,false);
            String ResponseAsString = objectMapper.writeValueAsString(response);
            writer.println(ResponseAsString);
        }
        else{
            Response response = new Response(messagesList,true);
            String ResponseAsString = objectMapper.writeValueAsString(response);
            System.out.println(ResponseAsString);
            System.out.println("Messages betweeen two users are provided");
            writer.println(ResponseAsString);
        }
    }
    public void HandleSaveMessageDirect(String data, PrintWriter writer, ObjectMapper objectMapper, ChatServer server) throws JsonProcessingException, SQLException {
        Messages returned = new MessagesService().sendDirectly(new MessageDecoder(data).returnMessageContent());
        if(returned == null){
            System.out.println("message not saved");
            Response response = new Response(null,false);
            String ResponseAsString = objectMapper.writeValueAsString(response);
            writer.println(ResponseAsString);
        }
        else{
            Response response = new Response(returned,true);
            String ResponseAsString = objectMapper.writeValueAsString(response);
            System.out.println(returned.getSender()+" sent a message");
            writer.println(ResponseAsString);
        }
    }
}
