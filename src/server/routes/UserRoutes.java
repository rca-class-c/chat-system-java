package server.routes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import server.ChatServer;
import server.requestHandlers.SendInvitationHandler;
import server.requestHandlers.UserRequestHandler;

import javax.mail.MessagingException;
import java.io.PrintWriter;
import java.sql.SQLException;

public class UserRoutes {
    private String data;
    private PrintWriter writer;
    private ObjectMapper objectMapper;
    private ChatServer server;
    private String request;

    public UserRoutes(String data, PrintWriter writer, ObjectMapper objectMapper, ChatServer server,String request) {
        this.data = data;
        this.writer = writer;
        this.objectMapper = objectMapper;
        this.server = server;
        this.request = request;
    }

    public UserRoutes(String data, PrintWriter writer, ObjectMapper objectMapper,String request) {
        this.data = data;
        this.writer = writer;
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public void Main() throws JsonProcessingException, SQLException, MessagingException, ClassNotFoundException {
        if(request.equals("users/login")){
            new UserRequestHandler().HandleLogin(data,writer,objectMapper,server);
        }
        else if(request.equals("users/register")){
            new UserRequestHandler().HandleRegister(data,writer,objectMapper);
        }
        else if(request.equals("users/")){
            new UserRequestHandler().HandleUsersList(data,writer,objectMapper);
        }
        else if(request.equals("users/profile")){
            new UserRequestHandler().HandleGetProfile(data,writer,objectMapper);
        }
        else if(request.equals("users/update")){
            new UserRequestHandler().HandleProfileUpdate(data,writer,objectMapper);
        }
        else if(request.equals("users/search")){
            new UserRequestHandler().HandlerSearchUser(data,writer,objectMapper);
        }
        else if(request.equals("users/invite")){
            new SendInvitationHandler().HandleSendInvitation(data,writer,objectMapper);
        }
        else if(request.equals("users/verify")){
            new SendInvitationHandler().HandleVerifyCode(data,writer,objectMapper);
        }
    }
}
