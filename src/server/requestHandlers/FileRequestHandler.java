package server.requestHandlers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import server.ChatServer;
import server.dataDecoders.FileDecoder;
import server.dataDecoders.UserDecoder;
import server.models.File;
import server.models.Response;
import server.models.User;
import server.services.FileService;
import server.services.UserService;

import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;


/**
 * File related request handler
 * @author: Divin Irakiza
 */
public class FileRequestHandler {
    public void HandleSaveFile(String data, PrintWriter writer, ObjectMapper objectMapper) throws JsonProcessingException, SQLException {
        File returned = new FileService().saveFile(new FileDecoder(data).SaveFileDecode());
        if(returned == null){
            System.out.println("File not saved");
            Response response = new Response(null,false);
            String ResponseAsString = objectMapper.writeValueAsString(response);
            writer.println(ResponseAsString);
        }
        else{
            Response response = new Response(returned,true);
            String ResponseAsString = objectMapper.writeValueAsString(response);
            System.out.println("File Saved: " + returned.getUrl());
            writer.println(ResponseAsString);
        }
    }

}
