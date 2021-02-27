package server.routes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import server.ChatServer;
import server.requestHandlers.FileRequestHandler;

import java.io.PrintWriter;
import java.sql.SQLException;
/**
 *Author: Didier Munezero
 *Description: This is a class that now where to direct a given request of files to a responsive direct handler.
 */
public class FileRoutes {
    private String data;
    private PrintWriter writer;
    private ObjectMapper objectMapper;
    private ChatServer server;
    private String request;
    public FileRoutes(String data, PrintWriter writer, ObjectMapper objectMapper, ChatServer server, String request) {
        this.data = data;
        this.writer = writer;
        this.objectMapper = objectMapper;
        this.server = server;
        this.request = request;
    }

    public FileRoutes(String data, PrintWriter writer, ObjectMapper objectMapper,String request) {
        this.data = data;
        this.writer = writer;
        this.objectMapper = objectMapper;
        this.request = request;
    }
    public void Main() throws JsonProcessingException, SQLException {
        if(request.equals("file/send")){
            new FileRequestHandler().HandleSaveFile(data, writer, objectMapper);
        }
    }
}
