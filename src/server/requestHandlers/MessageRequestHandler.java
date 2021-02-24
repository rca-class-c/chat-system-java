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
import utils.DirectMessage;
import utils.GroupMessage;

import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;

public class MessageRequestHandler {

    public void HandleMessageBetweenTwo(String data, PrintWriter writer, ObjectMapper objectMapper) throws JsonProcessingException, SQLException {
        List<DirectMessage> messagesList = new MessagesService().viewDirectMessagesBetweenTwo(new MessageDecoder(data).returnChatMembers());
        if (messagesList == null) {
            System.out.println("Query failed recheck your db");
            Response response = new Response(null, false);
            String ResponseAsString = objectMapper.writeValueAsString(response);
            writer.println(ResponseAsString);
        } else {
            Response response = new Response(messagesList, true);
            String ResponseAsString = objectMapper.writeValueAsString(response);
            System.out.println(ResponseAsString);
            System.out.println("Messages betweeen two users are provided");
            writer.println(ResponseAsString);
        }
    }
    public void HandleGroupMessages(String data, PrintWriter writer, ObjectMapper objectMapper) throws JsonProcessingException, SQLException {
        List<GroupMessage> messages= new MessagesService().viewGroupMessages(new UserDecoder(data).GetProfileDecode());
        if (messages == null) {
            System.out.println("message not saved");
            Response response = new Response(null, false);
            String ResponseAsString = objectMapper.writeValueAsString(response);
            writer.println(ResponseAsString);
        } else {
            Response response = new Response(messages, true);
            String ResponseAsString = objectMapper.writeValueAsString(response);
            System.out.println("sent a message");
            writer.println(ResponseAsString);
        }
    }
    public void HandleSaveMessageDirect(String data, PrintWriter writer, ObjectMapper objectMapper) throws JsonProcessingException, SQLException {
        Boolean returned = new MessagesService().sendDirectly(new MessageDecoder(data).returnMessageContent());
        if (!returned) {
            System.out.println("message not saved");
            Response response = new Response(null, false);
            String ResponseAsString = objectMapper.writeValueAsString(response);
            writer.println(ResponseAsString);
        } else {
            Response response = new Response(returned, true);
            String ResponseAsString = objectMapper.writeValueAsString(response);
            System.out.println("sent a message");
            writer.println(ResponseAsString);
        }
    }

    public void HandleSaveMessageInGroup(String data, PrintWriter writer, ObjectMapper objectMapper) throws JsonProcessingException, SQLException {
        boolean returned = new MessagesService().sendInGroup(new MessageDecoder(data).returnMessageContentGroup());
        if (!returned) {
            System.out.println("message not saved");
            Response response = new Response(null, false);
            String ResponseAsString = objectMapper.writeValueAsString(response);
            writer.println(ResponseAsString);
        } else {
            Response response = new Response(returned, true);
            String ResponseAsString = objectMapper.writeValueAsString(response);
            System.out.println("Message sent");
            writer.println(ResponseAsString);
        }
    }

    public void HandleReplyInGroup(String data, PrintWriter writer, ObjectMapper objectMapper) throws JsonProcessingException, SQLException {
        Messages returned = new MessagesService().ReplyInGroup(new MessageDecoder(data).returnMessageContent());
        if (returned == null) {
            System.out.println("reply not saved");
            Response response = new Response(null, false);
            String ResponseAsString = objectMapper.writeValueAsString(response);
            writer.println(ResponseAsString);
        } else {
            Response response = new Response(returned, true);
            String ResponseAsString = objectMapper.writeValueAsString(response);
            System.out.println(returned.getSender() + " added a reply");
            writer.println(ResponseAsString);
        }
    }

    public void HandleReplyDirectly(String data, PrintWriter writer, ObjectMapper objectMapper) throws JsonProcessingException, SQLException {
        Messages returned = new MessagesService().ReplyDirectly(new MessageDecoder(data).returnMessageContent());
        if (returned == null) {
            System.out.println("reply not saved");
            Response response = new Response(null, false);
            String ResponseAsString = objectMapper.writeValueAsString(response);
            writer.println(ResponseAsString);
        } else {
            Response response = new Response(returned, true);
            String ResponseAsString = objectMapper.writeValueAsString(response);
            System.out.println(returned.getSender() + " added a reply");
            writer.println(ResponseAsString);
        }
    }

    public void HandleDeleteMessages(String data, PrintWriter writer, ObjectMapper objectMapper) throws JsonProcessingException, SQLException {
        boolean returned = new MessagesService().DeleteMessage(new MessageDecoder(data).returnMessageDeleteData());
        if (!returned) {
            System.out.println("reply not saved");
            Response response = new Response(null, false);
            String ResponseAsString = objectMapper.writeValueAsString(response);
            writer.println(ResponseAsString);
// HEAD
        }
         else {
            Response response = new Response(returned, true);
            String ResponseAsString = objectMapper.writeValueAsString(response);
            System.out.println("Message is deleted");
            writer.println(ResponseAsString);
        }
    }
// HEAD

    public void HandleDeleteReplies(String data, PrintWriter writer, ObjectMapper objectMapper, ChatServer server) throws Exception {
        boolean returned = new MessagesService().DeleteReply(new UserDecoder(data).GetProfileDecode());
        if (!returned) {
            System.out.println("reply not saved");
            Response response = new Response(null, false);
            String ResponseAsString = objectMapper.writeValueAsString(response);
            writer.println(ResponseAsString);
        } else {
            Response response = new Response(true, true);
            String ResponseAsString = objectMapper.writeValueAsString(response);
            System.out.println("Reply is deleted");
            //-------------------------------------Handle Notifications request ------------------------------------------
            //author : Souvede & Chanelle
        }
    }
    public void HandleViewNotifications(String data, PrintWriter writer, ObjectMapper objectMapper) throws Exception {
        Set<ResultSet> messages = new MessagesService().viewUserNotifications(new UserDecoder(data).GetProfileDecode());
        //User returned = new UserService().getUserById(new UserDecoder(data).GetProfileDecode());
        if (messages == null) {
            System.out.println("Query failed recheck your db");
            Response response = new Response(null, false);
            String ResponseAsString = objectMapper.writeValueAsString(response);
            writer.println(ResponseAsString);
        } else {
            Response response = new Response(messages, true);
            String ResponseAsString = objectMapper.writeValueAsString(response);
            System.out.println(ResponseAsString);
            System.out.println("Notifications List");
            writer.println(ResponseAsString);
        }
    }
}
