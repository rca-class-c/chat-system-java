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
    public void HandleVerifyCode(String data, PrintWriter writer, ObjectMapper objectMapper) throws JsonProcessingException {
        boolean found = new sendInvitations().CheckIfVerificationCodeExist(new UserDecoder(data).GetProfileDecode());
        if(!found){
            Response response = new Response(null,false);
            String ResponseAsString = objectMapper.writeValueAsString(response);
            writer.println(ResponseAsString);
        }
        else{
            Response response = new Response(found,true);
            String ResponseAsString = objectMapper.writeValueAsString(response);
            writer.println(ResponseAsString);
        }
    }
}
