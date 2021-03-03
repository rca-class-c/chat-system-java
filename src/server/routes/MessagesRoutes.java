package server.routes;

import com.fasterxml.jackson.databind.ObjectMapper;
import server.ChatServer;
import server.requestHandlers.MessageRequestHandler;

import java.io.PrintWriter;


/**
 *Description: This is a class that now where to direct a given request of messages to a responsive direct handler.
 @author Didier Munezero
 */
public class MessagesRoutes {
    private String data;
    private PrintWriter writer;
    private ObjectMapper objectMapper;
    private ChatServer server;
    private String request;
    public MessagesRoutes(String data, PrintWriter writer, ObjectMapper objectMapper, ChatServer server, String request) {
        this.data = data;
        this.writer = writer;
        this.objectMapper = objectMapper;
        this.server = server;
        this.request = request;
    }

    public MessagesRoutes(String data, PrintWriter writer, ObjectMapper objectMapper,String request) {
        this.data = data;
        this.writer = writer;
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public void Main() throws Exception {
        if(request.equals("messages/direct")){
            new MessageRequestHandler().HandleMessageBetweenTwo(data,writer,objectMapper);
        }
        else if(request.equals("messages/group")){
            new MessageRequestHandler().HandleGroupMessages(data,writer,objectMapper);
        }
        else if(request.equals("messages/single")){
            new MessageRequestHandler().HandleMessageProfile(data,writer,objectMapper);
        }
        else if(request.equals("messages/message_replies")){
            new MessageRequestHandler().HandleMessageReplies(data,writer,objectMapper);
        }
        else if(request.equals("messages/send/direct")){
            new MessageRequestHandler().HandleSaveMessageDirect(data,writer,objectMapper);
        }
        else if(request.equals("messages/send/group")){
            new MessageRequestHandler().HandleSaveMessageInGroup(data,writer,objectMapper);
        }
        else if(request.equals("messages/delete")){
            new MessageRequestHandler().HandleDeleteMessages(data,writer,objectMapper);
        }
        else if(request.equals("messages/notifications")){
            new MessageRequestHandler().HandleGroupNotis(data,writer,objectMapper);

        }
        else if(request.equals("messages/notifi")){

            new MessageRequestHandler().HandleDirectNotis(data,writer,objectMapper);
        }
        else if(request.equals("messages/edit")){
            new MessageRequestHandler().HandleEditMessages(data, writer, objectMapper);
        }
    }
}
