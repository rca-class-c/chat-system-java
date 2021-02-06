package server;

import java.io.*;
import java.net.*;
import java.sql.SQLException;
import java.util.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import server.dataDecoders.LoginDataDecoder;
import server.models.Response;
import server.models.User;
import server.services.UserService;

/**
 * This is a thread that allows many clients to the server as it handles one currently connected and when new one comes any
 * @Author: Didier Munezero
 */
public class UserThread extends Thread {
    private Socket socket;
    private ChatServer server;
    private PrintWriter writer;
    public UserThread(Socket socket, ChatServer server) {
        this.socket = socket;
        this.server = server;
    }
    public void run() {
        try {
            InputStream input = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
//            Scanner reader = new Scanner(System.in);
            OutputStream output = socket.getOutputStream();
            writer = new PrintWriter(output, true);


            //ok now caught after the username is flushed server sets it good now
            //printUsers();
            //String userName = reader.readLine();
            //server.addUserName(userName);
            String serverMessage;
            //passing message and user to exclude who is the sender
            //System.out.println(serverMessage);
            //server.broadcast(serverMessage, this);
            String clientMessage;
            do {
                clientMessage = reader.readLine();
                serverMessage = clientMessage;
                System.out.println(serverMessage);
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode jsonNode = objectMapper.readTree(clientMessage);
                String request_type = jsonNode.get("request_type").asText();
                String data = jsonNode.get("data").toString();

                if(request_type.equals("login")){
                    User returned = new UserService().loginUser(new LoginDataDecoder(data).decode());
                    if(returned == null){
                        System.out.println("No user found");
                    }
                    else{
                        Response response = new Response(returned,true);
                        String ResponseAsString = objectMapper.writeValueAsString(response);
                        System.out.println("User found");
                        writer.println(ResponseAsString);
                    }
                }
                //server.broadcast(serverMessage, this);
            } while (!clientMessage.equals("bye"));
            //server.removeUser(userName, this);
            socket.close();
            //passing message to remaining users that one has quitted
            //serverMessage = userName + " has quitted.";
            server.broadcast(serverMessage, this);
        } catch (IOException | SQLException ex) {
            System.out.println("Error in UserThread: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
    /**
     * Sends a list of online users to the newly connected user.
     */
    void printUsers() {
        System.out.println(server.hasUsers());
        if (server.hasUsers()) {
            writer.println("Connected users: " + server.getUserNames());
        } else {
            writer.println("No other users connected");
        }
    }
    /**
     * Sends a message to the client.
     */
    void sendMessage(String message) {
        writer.println(message);
    }
}