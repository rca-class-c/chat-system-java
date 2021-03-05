package server.requestHandlers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import server.dataDecoders.UserDecoder;
import server.models.Response;
import server.models.User;
import server.services.ReportsServices;
import server.services.UserService;

import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

public class StasticsRequestHandler {

    public void HandleReportsDaily(PrintWriter writer, ObjectMapper objectMapper,String option) throws JsonProcessingException {
        List<List> returned = null;
        if (option.contains("message")){
            returned = new ReportsServices().getMessageReport();
        }else if (option.contains("user")){
            returned = new ReportsServices().getUserReport();
        }else if (option.contains("groups")){
            returned = new ReportsServices().getGroupReport();
        }
        else if (option.contains("visits")){
            returned = new ReportsServices().getVisitReport();
        }

        if(returned == null){
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
    public void HandleGroupsDaily(PrintWriter writer, ObjectMapper objectMapper) throws JsonProcessingException {
        List<List> returned = new ReportsServices().getGroupReport();
        if(returned == null){
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

}
