package client.simplifiers;

import client.interfaces.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import server.models.Group;
import server.models.Messages;
import server.models.User;
import utils.ChatBetweenTwo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;


/***
 * @author Didier Munezero
 * This is a class that simplifies sending requests server from the page that the user is running
 */
public class RequestSimplifiers {
    public PrintWriter writer;
    public BufferedReader reader;

    public RequestSimplifiers(PrintWriter writer, BufferedReader reader) {
        this.writer = writer;
        this.reader = reader;
    }
    public Messages[] goGetMessageReplies(int message_id) throws IOException {
        String key = "messages/message_replies";
        Request request = new Request(new ProfileRequestData(message_id),key);
        String requestAsString = new ObjectMapper().writeValueAsString(request);
        writer.println(requestAsString);
        ResponseDataSuccessDecoder response = new UserResponseDataDecoder().decodedResponse(reader.readLine());
        if(response.isSuccess()) {
            return new MessageResponseDataDecoder().returnDecodedReplies(response.getData());
        }
        return null;
    }
    public User goGetUser(int id) throws IOException {
        String key= "users/profile";
        Request profileRequest = new Request(new ProfileRequestData(id),key);
        String requestAsString = new ObjectMapper().writeValueAsString(profileRequest);
        writer.println(requestAsString);
        ResponseDataSuccessDecoder profileResponse = new UserResponseDataDecoder().decodedResponse(reader.readLine());

        if(profileResponse.isSuccess()) {
            return new UserResponseDataDecoder().returnUserDecoded(profileResponse.getData());
        }
        return null;
    }
    public Group goGetGroup(int id) throws IOException {
        String key= "groups/profile";
        Request profileRequest = new Request(new ProfileRequestData(id),key);
        String requestAsString = new ObjectMapper().writeValueAsString(profileRequest);
        writer.println(requestAsString);
        ResponseDataSuccessDecoder profileResponse = new UserResponseDataDecoder().decodedResponse(reader.readLine());

        if(profileResponse.isSuccess()) {
            return new GroupResponseDataDecoder().returnGroupDecoded(profileResponse.getData());
        }
        return null;
    }
    public Messages goGetMessage(int id) throws IOException {
        String key= "messages/single";
        Request profileRequest = new Request(new ProfileRequestData(id),key);
        String requestAsString = new ObjectMapper().writeValueAsString(profileRequest);
        writer.println(requestAsString);
        ResponseDataSuccessDecoder profileResponse = new UserResponseDataDecoder().decodedResponse(reader.readLine());

        if(profileResponse.isSuccess()) {
            return new MessageResponseDataDecoder().returnDecodedMessage(profileResponse.getData());
        }
        return null;
    }
    public Messages[] goGetMessages(int id,int id2) throws IOException {
        String  key= "messages/direct";
        Request request = new Request(new ChatBetweenTwo(id,id2),key);
        String requestAsString = new ObjectMapper().writeValueAsString(request);
        writer.println(requestAsString);
        ResponseDataSuccessDecoder response = new UserResponseDataDecoder().decodedResponse(reader.readLine());
        if(response.isSuccess()) {
            return new MessageResponseDataDecoder().returnDecodedReplies(response.getData());
        }
        return null;
    }
    public List<List> getDiallyMessages(String key) throws IOException {
        Request reportRequest = new Request(null,key);
        String requestAsString = new ObjectMapper().writeValueAsString(reportRequest);
        writer.println(requestAsString);
        ResponseDataSuccessDecoder reportResponse = new UserResponseDataDecoder().decodedResponse(reader.readLine());

        if(reportResponse.isSuccess()) {
            List reports = new ObjectMapper().readValue(reportResponse.getData(),List.class);
            return reports;

        }
        return null;
    }
    public User[] goGetUsers(int id) throws IOException {
        String  key= "users/";
        Request request = new Request(new ProfileRequestData(id),key);
        String requestAsString = new ObjectMapper().writeValueAsString(request);
        writer.println(requestAsString);
        ResponseDataSuccessDecoder response = new UserResponseDataDecoder().decodedResponse(reader.readLine());
        if(response.isSuccess()) {
            return new UserResponseDataDecoder().returnUsersListDecoded(response.getData());
        }
        return null;
    }
    public User[] goGetInactiveUsers(int id) throws IOException {
        String  key= "users/inactive";
        Request request = new Request(new ProfileRequestData(id),key);
        String requestAsString = new ObjectMapper().writeValueAsString(request);
        writer.println(requestAsString);
        ResponseDataSuccessDecoder response = new UserResponseDataDecoder().decodedResponse(reader.readLine());
        if(response.isSuccess()) {
            return new UserResponseDataDecoder().returnUsersListDecoded(response.getData());
        }
        return null;
    }
}
