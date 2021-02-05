package server;

import server.interfaces.ActiveUser;
import server.interfaces.LoginData;

import java.io.*;
import java.net.*;
import java.util.*;
/**
 * This is a thread that handles a user when he/she has connected to the server
 * @Author: Didier Munezero
 *
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
            InputStream inputStream = socket.getInputStream();
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            LoginData loginData = (LoginData) objectInputStream.readObject();
            System.out.println("Received Login data");

           // String request = reader.readLine();
//            if(request == "loginRequest"){
//                ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
//                ActiveUser requestUser = (ActiveUser) objectInputStream.readObject();
//                System.out.println(requestUser.getFirst_name());
//            }
//            else if(request == "registerRequest"){
//
//            }

            String userName = reader.readLine();
            server.addUserName(userName);
            String serverMessage = "New user connected: " + userName;
            //passing message and user to exclude who is the sender
            server.broadcast(serverMessage, this);
            String clientMessage;
            do {
                clientMessage = reader.readLine();
                serverMessage = "[" + userName + "]: " + clientMessage;
                server.broadcast(serverMessage, this);
            } while (!clientMessage.equals("bye"));
            server.removeUser(userName, this);
            socket.close();
            //passing message to remaining users that one has quitted
            serverMessage = userName + " has quitted.";
            server.broadcast(serverMessage, this);
        } catch (IOException | ClassNotFoundException ex) {
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