package server.routes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import server.ChatServer;
import server.requestHandlers.MessageRequestHandler;

import java.io.PrintWriter;
import java.sql.SQLException;

/**
 *@author: Didier Munezero
 *Description: This is a class that now where to direct a given request of replies to a responsive direct handler.
 */
public class RepliesRoutes {
    private String data;
    private PrintWriter writer;
    private ObjectMapper objectMapper;
    private ChatServer server;
    private String request;
    public RepliesRoutes(String data, PrintWriter writer, ObjectMapper objectMapper, ChatServer server, String request) {
        this.data = data;
        this.writer = writer;
        this.objectMapper = objectMapper;
        this.server = server;
        this.request = request;
    }

    public RepliesRoutes(String data, PrintWriter writer, ObjectMapper objectMapper,String request) {
        this.data = data;
        this.writer = writer;
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public void Main() throws JsonProcessingException, SQLException {
        if(request.equals("replies/send/direct")){
            new MessageRequestHandler().HandleReplyDirectly(data,writer,objectMapper);
        }
        else if(request.equals("replies/send/group")){
            new MessageRequestHandler().HandleReplyInGroup(data,writer,objectMapper);
        }
    }
}
