package server.routes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import server.requestHandlers.StasticsRequestHandler;

import java.io.PrintWriter;

public class StatisticsRoutes {
    private String data;
    private PrintWriter writer;
    private ObjectMapper objectMapper;
    private String request;
    public StatisticsRoutes(String data, PrintWriter writer, ObjectMapper objectMapper,String request) {
        this.data = data;
        this.writer = writer;
        this.objectMapper = objectMapper;
        this.request = request;
    }
    public void Main() throws JsonProcessingException {
        if(request.equals("stats/messages/daily")){
            new StasticsRequestHandler().HandleReportsDaily(writer,objectMapper,"message");
        }
        else if(request.equals("stats/groups/daily")){
            new StasticsRequestHandler().HandleReportsDaily(writer,objectMapper,"groups");;
        }
        else if(request.equals("stats/user/daily")){
            new StasticsRequestHandler().HandleReportsDaily(writer,objectMapper,"user");;
        }
        else if(request.equals("stats/visit/daily")){
            new StasticsRequestHandler().HandleReportsDaily(writer,objectMapper,"visits");;
        }
    }

}
