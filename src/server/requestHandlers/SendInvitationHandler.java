package server.requestHandlers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import server.ChatServer;
import server.dataDecoders.SendInvitationDecoder;
import server.dataDecoders.UserDecoder;
import server.models.Response;
import server.models.User;
import server.services.UserService;
import server.services.sendInvitations;

import javax.mail.MessagingException;
import java.io.PrintWriter;
import java.sql.SQLException;

public class SendInvitationHandler {
    public void HandleSendInvitation(String data, PrintWriter writer, ObjectMapper objectMapper) throws JsonProcessingException, SQLException, MessagingException, ClassNotFoundException {
        boolean returned = new sendInvitations().sendingInvitations(new SendInvitationDecoder(data).retrieveEmails());
        if(!returned){
            System.out.println("Failed to send email");
            Response response = new Response(null,false);
            String ResponseAsString = objectMapper.writeValueAsString(response);
            writer.println(ResponseAsString);
        }
        else{
            Response response = new Response(returned,true);
            String ResponseAsString = objectMapper.writeValueAsString(response);
            System.out.println(response);
            System.out.println("Invitations are sent to new users to join this system");
            writer.println(ResponseAsString);
        }
    }
}
