package client.simplifiers;

import client.interfaces.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import server.models.Messages;
import server.models.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;


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
    public User goGetUser(int id) throws IOException {
        String key= "users/profile";
        Request profileRequest = new Request(new ProfileRequestData(id),key);
        String requestAsString = new ObjectMapper().writeValueAsString(profileRequest);
        writer.println(requestAsString);
        ResponseDataSuccessDecoder profileResponse = new UserResponseDataDecoder().decodedResponse(reader.readLine());

        if(profileResponse.isSuccess()) {
            User profile = new UserResponseDataDecoder().returnUserDecoded(profileResponse.getData());
            return profile;
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
            Messages profile = new MessageResponseDataDecoder().returnDecodedMessage(profileResponse.getData());
            return profile;
        }
        return null;
    }
    public Messages[] goGetMessages(int id) throws IOException {
        String  key= "users/";
        Request request = new Request(new ProfileRequestData(id),key);
        String requestAsString = new ObjectMapper().writeValueAsString(request);
        writer.println(requestAsString);
        ResponseDataSuccessDecoder response = new UserResponseDataDecoder().decodedResponse(reader.readLine());
        if(response.isSuccess()) {
            Messages[] messages = new MessageResponseDataDecoder().returnDecodedReplies(response.getData());
            return messages;
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
            User[] users = new UserResponseDataDecoder().returnUsersListDecoded(response.getData());
            return users;
        }
        return null;
    }
}
