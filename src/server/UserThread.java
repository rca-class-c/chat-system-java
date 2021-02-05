package server;


import server.models.ActiveUser;
import server.models.User;
import server.models.AuthInput;
import server.services.UserService;

import java.io.*;
import java.net.*;
import java.sql.SQLException;
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
            do{
            BufferedReader reader = new BufferedReader(new InputStreamReader( socket.getInputStream()));
            writer = new PrintWriter(socket.getOutputStream(), true);
            String clientMessage;
            clientMessage = reader.readLine();
            System.out.println(clientMessage);
            if(clientMessage.equals("LOGIN_REQUEST")){
                ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
                AuthInput loginData = (AuthInput) objectInputStream.readObject();

                User user = new UserService().loginUser(loginData);
                PrintWriter out = null;

                try {
                    out = new PrintWriter(this.socket.getOutputStream(), true);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                if(user == null){
                    System.out.println("Login Failed");
                    out.println("false");
                }
                else {
                    out.println("true");
                    ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                    System.out.println("Login successfully");
                    ActiveUser activeUser = new ActiveUser(user.getUserID(),user.getUsername());
                    objectOutputStream.writeObject(activeUser);
                    objectOutputStream.flush();
                }
                //objectInputStream.close();
            }
            else if(clientMessage.equals("SIGNUP_REQUEST")){
//                InputStream inputStream = socket.getInputStream();
//                ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
//                User user = (User)objectInputStream.readObject();
                System.out.println("Received Signup data");
            }




//            String userName = reader.readLine();
//            server.addUserName(userName);
//            String serverMessage = "New user connected: " + userName;
//            //passing message and user to exclude who is the sender
//            server.broadcast(serverMessage, this);

//            do {
//                clientMessage = reader.readLine();
//                serverMessage = "[" + userName + "]: " + clientMessage;
//                server.broadcast(serverMessage, this);
//            } while (!clientMessage.equals("bye"));
//            server.removeUser(userName, this);
//            socket.close();
//            //passing message to remaining users that one has quitted
//            serverMessage = userName + " has quitted.";
//            server.broadcast(serverMessage, this);
            }while(true);
        } catch (IOException | ClassNotFoundException | SQLException ex) {
            System.out.println("Error in UserThread: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
    /**
     * Sends a list of online users to the newly connected user.
     */
//    void printUsers() {
//        System.out.println(server.hasUsers());
//        if (server.hasUsers()) {
//            writer.println("Connected users: " + server.getUserNames());
//        } else {
//            writer.println("No other users connected");
//        }
//    }
    /**
     * Sends a message to the client.
     */
//    void sendMessage(String message) {
//        writer.println(message);
//    }
}